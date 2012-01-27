package com.maktashaf.taymiyyah.repository.generator;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.AttributeSource;
import org.apache.lucene.util.Version;

/**
 * @author Haroon Anwar padhyar.
 */
public class AnalyzerTest {

  private static final String[] examples = {
//      "The quick brown fox jumped over the lazy dogs",
//      "XY&Z Corporation - xyz@example.com"
      "قلوبنا", "علي", "على"
  };

  //"قلوبنا", "علي", "على"
  private static final Analyzer[] analyzers = new Analyzer[] {
//      new WhitespaceAnalyzer(),
//      new SimpleAnalyzer(),
//      new StopAnalyzer(Version.LUCENE_31),
//      new StandardAnalyzer(Version.LUCENE_31)
      new ArabicAnalyzer(Version.LUCENE_30)
  };
  public static void main(String[] args) throws IOException {
// Use the embedded example strings, unless
// command line arguments are specified, then use those.
    String[] strings = examples;
    if (args.length > 0) {
      strings = args;
    }
    for (int i = 0; i < strings.length; i++) {
      analyze(strings[i]);
    }
  }
  private static void analyze(String text) throws IOException {
    System.out.println("Analyzing \"" + text + "\"");
    for (int i = 0; i < analyzers.length; i++) {
      Analyzer analyzer = analyzers[i];
      String name = analyzer.getClass().getName();
      name = name.substring(name.lastIndexOf(".") + 1);
      System.out.println(" " + name + ":");
      System.out.print(" ");
      displayTokens(analyzer, text);
      System.out.println("\n");
    }
  }


  private static void displayTokens(Analyzer analyzer,
                                   String text) throws IOException {
    AttributeSource[] tokens = tokensFromAnalysis(analyzer, text);
    for (int i = 0; i < tokens.length; i++) {
      AttributeSource token = tokens[i];
//      TermAttribute term = (TermAttribute) token.addAttribute(TermAttribute.class);
//      System.out.print("[" + term.term() + "] ");
      CharTermAttribute term = (CharTermAttribute) token.addAttribute(CharTermAttribute.class);
      System.out.print("[" + term.toString() + "] "); //2
    }
  }

  private static AttributeSource[] tokensFromAnalysis(Analyzer analyzer,
                                                     String text) throws IOException {
    TokenStream stream = //1
        analyzer.tokenStream("contents", new StringReader(text)); //1
    ArrayList tokenList = new ArrayList();
    while (true) {
      if (!stream.incrementToken())
        break;
      tokenList.add(stream.cloneAttributes());
    }

    AttributeSource[] attributeSources = new AttributeSource[tokenList.size()];
    for(int i = 0; i < tokenList.size(); i++){
//      System.out.println(tokenList.get(i).getClass().getName());
      AttributeSource state = (AttributeSource)tokenList.get(i);
//      System.out.println(state.toString());
      attributeSources[i] = state;
    }
    
    return attributeSources;
  }


}
