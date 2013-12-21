package com.maktashaf.taymiyyah.analysis.en;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;
import org.apache.solr.analysis.PhoneticFilterFactory;

/**
 * @author: Haroon
 */
public class PhoneticEnglishAnalyzer extends StopwordAnalyzerBase {
  public PhoneticEnglishAnalyzer(Version matchVersion) {
    super(matchVersion, StandardAnalyzer.STOP_WORDS_SET);
  }

  @Override
  protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
    final Tokenizer source = new StandardTokenizer(matchVersion, reader);
    TokenStream result = new StandardFilter(matchVersion, source);
    result = new EnglishPossessiveFilter(result);
    result = new LowerCaseFilter(matchVersion, result);
    result = new StopFilter(matchVersion, result, stopwords);
    result = new PorterStemFilter(result);

    PhoneticFilterFactory phoneticFilterFactory = new PhoneticFilterFactory();
    Map<String, String> map = new HashMap<String, String>();
    map.put(PhoneticFilterFactory.ENCODER, "METAPHONE");
    phoneticFilterFactory.init(map);
    result = phoneticFilterFactory.create(result);

    return new TokenStreamComponents(source, result);
  }
}
