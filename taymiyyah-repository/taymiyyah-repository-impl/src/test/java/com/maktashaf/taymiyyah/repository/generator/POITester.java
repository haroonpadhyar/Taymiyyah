package com.maktashaf.taymiyyah.repository.generator;

import java.io.FileInputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


/**
 * @author Haroon Anwar padhyar.
 */
public class POITester {

//  public static DocxUtil docxUtil = new DocxUtil();

  public static void main(String[] args) {
//    String file = "c:\\New.doc";
    String file = "c:\\New1.docx";
//    String data = readFileDoc(file);

    String data = readFileDocx(file);
    // from file
    List<String> result =  getAngram(data);
    System.out.println("Total anagram(s) are: "+result);

    result =  getAngram("He listen silent");
    System.out.println("Total anagram(s) are: "+result);

  }

  private static List<String> getAngram (String str) {
    String [] strArray = str.split(" ");
    List<String> anagramWordList = new ArrayList<String>();

    for (int i = 0 ; i < strArray.length; i++){

      for (int j = i + 1 ; j < strArray.length; j++){
        if(!"".equals(strArray [i])){
          if (isAnagram(strArray [i], strArray[j])){
            System.out.println("Angram......."+strArray [i]);
            anagramWordList.add(strArray [i]);
          }
        }
      }
    }
    return anagramWordList;
  }

  static boolean isAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    char [] a1 = s1.toCharArray();
    char [] a2 = s2.toCharArray();
    for (int i = a1.length - 1; i >= 0; --i) {
      int j;
      for (j = a2.length - 1; j >= 0; --j) {
        if (a1[i] == a2[j]) break;
      }
      if (j < 0) return false;
    }
    return true;
  }

  /*public static String readFileDoc(String filepath) {
    StringBuilder data = new StringBuilder();
    File file = null;
    WordExtractor extractor = null ;
    try {

      file = new File(filepath);
      FileInputStream fis=new FileInputStream(file.getAbsolutePath());
      HWPFDocument document=new HWPFDocument(fis);
      extractor = new WordExtractor(document);

      String [] fileData = extractor.getParagraphText();
      for(int i=0;i<fileData.length;i++){
        if(fileData[i] != null){
          data.append(" ");
          data.append(fileData[i].trim());
          System.out.println(fileData[i]);
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }

    return data.toString();
  }
*/

  /*public static String readFileDocx (String file) {
//    DocxUtil util = new DocxUtil();
    String data = "";
    try{
       data= docxUtil.extract(file);
      System.getProperty("line.separator");
      data = data.replaceAll(System.getProperty("line.separator"), " ");

    }catch(Exception ex){
      ex.printStackTrace();
    }
    return data;
  }*/

  public static String readFileDocx(String filePath) {

    StringBuilder text = new StringBuilder();
    try{

      FileInputStream fileInputStream = new FileInputStream(new File(filePath));
      XWPFDocument doc = new XWPFDocument(fileInputStream);

      XWPFParagraph[] paragraphs = doc.getParagraphs();
      for (XWPFParagraph paragraph : paragraphs) {
        text.append(" ");
        text.append(paragraph.getText());
      }

    } catch(Exception e){
      e.printStackTrace();
    }

    return text.toString();
  }

}
