package com.maktashaf.taymiyyah.repository.generator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class DocxUtil {

	private ContentTypes contentTypes;
	private StringBuilder fullText;
	private static final HashMap<String, String> TEXT_ATTRIBUTE_TYPES = new HashMap<String, String>();
	private static final String END_OF_LINE = System.getProperty("line.separator", "\n");
	private static final String CONTENT_TYPES_FILE = "[Content_Types].xml";
	private static final HashSet<String> TEXT_ELEMENT_TYPES = new HashSet<String>();
	
	private static final int BUFFER_SIZE = 4 * 1024 * 1024; 
	 
	static {TEXT_ELEMENT_TYPES.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");} 
	public String extract(String fpath) throws Exception{

		File in = new File(fpath);
		FileInputStream fis = new FileInputStream(in);
		InputStream stream = new BufferedInputStream(fis);
		stream.mark(BUFFER_SIZE);
		 fullText = new StringBuilder(256 * 1024);

		// create a ZipStream around the InputStream
		ZipInputStream zipStream = null;
		try {
			zipStream = new ZipInputStream(stream);
			ZipEntry entry = null;

			// loop over all entries in the package
			while ((entry = zipStream.getNextEntry()) != null) {
				String name = entry.getName();

				// see if this is the Content Types component
				if (CONTENT_TYPES_FILE.equals(name)) {
					// parse the contents of this XML document
					parseContentTypes(zipStream);
					break;
				}

				zipStream.closeEntry();
			}
		} catch (IOException e) {
			throw new Exception(e);
		}

		// some sanity checking
		if (contentTypes == null) {
			throw new Exception("missing " + CONTENT_TYPES_FILE
					+ " file");
		}

		// reset the stream
		try {
			stream.reset();
		} catch (IOException e) {
			throw new Exception("Unable to reset stream", e);
		}

		// loop over all entries in the zip file again
		try {
			zipStream = new ZipInputStream(stream);
			ZipEntry entry = null;

			// loop over all entries in the package
			while ((entry = zipStream.getNextEntry()) != null) {
				// determine the type of this entry
				String name = entry.getName();
				name = toAbsoluteName(name);
				String type = contentTypes.getType(name);

				if (type != null) {
					if (TEXT_ELEMENT_TYPES.contains(type)) {
						process(zipStream, new TextCollector());
					} 
				}

				zipStream.closeEntry();
			}
		} catch (IOException e) {
			throw new Exception(e);
		}

		// add the extracted text to the metadata
		String text = fullText.toString();
    return text;
	}


    private void process(InputStream stream, SimpleSAXListener listener) {
        // create a SimpleSaxParser
        SimpleSAXParser parser = null;
        try {
            parser = new SimpleSAXParser();
        }
        catch (Exception e) {
            throw new RuntimeException("unable to instantiate SAXParser", e);
        }

        parser.setListener(listener);
        parser.setTrimWhiteSpace(false);

        try {
            parser.parse(new NonCloseableStream(stream));
        }
        catch (Exception e) {
            System.out.println("Exception while parsing XML : " +  e.toString());
        }
    }

	private void parseContentTypes(InputStream stream)
			throws Exception {
		Document doc = getDocument(stream, false);
		contentTypes = new ContentTypes();

		// traverse the document structure and add the encountered defaults and
		// overrides to contentTypes
		Element rootElement = doc.getDocumentElement();
		NodeList children = rootElement.getChildNodes();
		int nrChildren = children.getLength();

		for (int i = 0; i < nrChildren; i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) child;
				String name = element.getTagName();
				String contentType = element.getAttribute("ContentType");

				if ("Default".equals(name)) {
					String extension = element.getAttribute("Extension");
					if (extension != null && contentType != null) {
						contentTypes.addDefault(extension, contentType);
					}
				} else if ("Override".equals(name)) {
					String partName = element.getAttribute("PartName");
					if (partName != null && contentType != null) {
						contentTypes.addOverride(partName, contentType);
					}
				}
			}
		}
	}

	private Document getDocument(InputStream stream, boolean namespaceAware)
			throws Exception {
		// create a DocumentBuilder instance
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		docBuilderFactory.setNamespaceAware(namespaceAware);
		docBuilderFactory.setValidating(false);
		docBuilderFactory.setExpandEntityReferences(false);
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// this is an internal error rather than an extraction problem,
			// hence the RuntimeException
			throw new RuntimeException("unable to instantiate DocumentBuilder",
					e);
		}

		// parse the XML in the specified stream
		try {
			// make sure the stream does not get closed or else the entire
			// ZipStream will be closed
			return docBuilder.parse(new NonCloseableStream(stream));
		} catch (SAXException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}

	}

	private String toAbsoluteName(String name) {
		
		if (name.startsWith("/")) {
			return name;
		} else {
			return "/" + name;
		}
	}

	private static class ContentTypes {

		private HashMap<String, String> defaults;

		private HashMap<String, String> overrides;

		public ContentTypes() {
			defaults = new HashMap<String, String>();
			overrides = new HashMap<String, String>();
		}

		public void addDefault(String extension, String contentType) {
			defaults.put(extension, contentType);
		}

		public void addOverride(String partName, String contentType) {
			overrides.put(partName, contentType);
		}

		public String getDefault(String extension) {
			return (String) defaults.get(extension);
		}

		public String getOverride(String partName) {
			return (String) overrides.get(partName);
		}

		public String getType(String partName) {
			String override = getOverride(partName);
			if (override == null) {
				int index = partName.lastIndexOf('.');
				if (index >= 0 && index < partName.length() - 1) {
					String extension = partName.substring(index + 1);
					return getDefault(extension);
				} else {
					return null;
				}
			} else {
				return override;
			}
		}

		public String toString() {
			return "ContentTypes[default=" + defaults + ",overrides="
					+ overrides + "]";
		}
	}
	
	
	  private class TextCollector extends SimpleSAXAdapter {

	        private int initialLength;

	        private boolean insideTabs = false;

	        public void startDocument() throws SAXException {
	            initialLength = fullText.length();
	        }

	        @SuppressWarnings("unchecked")
	        public void startTag(String tagName, Map atts, String text) throws SAXException {
	            if ("w:t".equals(tagName)) {
	                fullText.append(text);
	            }
	            else if ("t".equals(tagName) || "p:text".equals(tagName) || "a:t".equals(tagName)
	                    || "st:t".equals(tagName) || "v".equals(tagName)) {
	                fullText.append(text);
	                fullText.append(' ');
	            }
	            else if ("w:tab".equals(tagName) && !insideTabs) {
	                fullText.append('\t');
	            }
	            else if ("w:tabs".equals(tagName)) {
	                insideTabs = true;
	            }
	        }

	        public void endTag(String tagName) throws SAXException {
	            if ("w:p".equals(tagName)) {
	                fullText.append(END_OF_LINE);
	            }
	            else if ("w:tabs".equals(tagName)) {
	                insideTabs = false;
	            }
	        }

	        public void endDocument() throws SAXException {
	            if (fullText.length() > initialLength) {
	                fullText.append(END_OF_LINE);
	            }
	        }
	    }
	  
	  private static class NonCloseableStream extends FilterInputStream {

	        public NonCloseableStream(InputStream in) {
	            super(in);
	        }

	        public void close() throws IOException {
	        	
	        }
	    }
	  
}

interface SimpleSAXListener {

	public void startDocument() throws SAXException;

	public void endDocument() throws SAXException;

	public void startTag(String tagName, Map atts, String text)
			throws SAXException;

	public void endTag(String tagName) throws SAXException;
}

class SimpleSAXAdapter implements SimpleSAXListener {

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
	}

	public void startTag(String tagName, Map atts, String text)
			throws SAXException {
	}

	public void endTag(String tagName) throws SAXException {
	}
}

class SimpleSAXParser {

	private SAXParser saxParser;

	private SimpleSAXListener listener;

	private boolean trimWhitespace = true;

	public SimpleSAXParser(SAXParser saxParser) {
		this.saxParser = saxParser;
	}

	public SimpleSAXParser() throws ParserConfigurationException, SAXException {
		this(SAXParserFactory.newInstance().newSAXParser());
	}

	public void setListener(SimpleSAXListener listener) {
		this.listener = listener;
	}

	public SimpleSAXListener getListener() {
		return listener;
	}

	public void setTrimWhiteSpace(boolean trimWhitespace) {
		this.trimWhitespace = trimWhitespace;
	}

	public boolean getTrimWhitespace() {
		return trimWhitespace;
	}

	public synchronized void parse(InputStream in) throws SAXException,
			IOException {
		saxParser.parse(in, new SimpleSAXDefaultHandler());
	}

	public synchronized void parse(InputStream in, String systemId)
			throws SAXException, IOException {
		saxParser.parse(in, new SimpleSAXDefaultHandler(), systemId);
	}

	public synchronized void parse(File file) throws SAXException, IOException {
		saxParser.parse(file, new SimpleSAXDefaultHandler());
	}

	private class SimpleSAXDefaultHandler extends DefaultHandler {

		private StringBuilder charBuf = new StringBuilder(512);
		private String deferredStartTag = null;

		private Map deferredAttributes = null;

		public SimpleSAXDefaultHandler() {
			super();
		}

		public void startDocument() throws SAXException {
			listener.startDocument();
		}

		public void endDocument() throws SAXException {
			listener.endDocument();
		}

		public void characters(char[] ch, int start, int length)
				throws SAXException {
			charBuf.append(ch, start, length);
		}

		public void startElement(String namespaceURI, String localName,
				String qName, Attributes attributes) throws SAXException {
			// Report any deferred start tag
			if (deferredStartTag != null) {
				reportDeferredStartElement();
			}

			// Make current tag new deferred start tag
			deferredStartTag = qName;

			// Copy attributes to _deferredAttributes
			int attCount = attributes.getLength();
			if (attCount == 0) {
				deferredAttributes = Collections.EMPTY_MAP;
			} else {
				deferredAttributes = new HashMap(attCount * 2);

				for (int i = 0; i < attCount; i++) {
					deferredAttributes.put(attributes.getQName(i),
							attributes.getValue(i));
				}
			}

			// Clear character buffer
			charBuf.setLength(0);
		}

		private void reportDeferredStartElement() throws SAXException {
			listener.startTag(deferredStartTag, deferredAttributes, "");
			deferredStartTag = null;
			deferredAttributes = null;
		}

		public void endElement(String namespaceURI, String localName,
				String qName) throws SAXException {
			if (deferredStartTag != null) {
				// Check if any character data has been collected in the
				// _charBuf
				String text = charBuf.toString();

				if (trimWhitespace) {
					text = text.trim();
				}

				// Report deferred start tag
				listener.startTag(deferredStartTag, deferredAttributes, text);
				deferredStartTag = null;
				deferredAttributes = null;
			}

			// Report the end tag
			listener.endTag(qName);

			// Clear character buffer
			charBuf.setLength(0);
		}
	}
	}


