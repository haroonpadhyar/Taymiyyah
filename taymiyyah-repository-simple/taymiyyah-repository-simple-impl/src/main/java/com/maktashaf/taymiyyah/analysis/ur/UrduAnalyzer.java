package com.maktashaf.taymiyyah.analysis.ur;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ar.ArabicNormalizationFilter;
import org.apache.lucene.analysis.ar.ArabicStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

/**
 * @author: Haroon
 */
public final class UrduAnalyzer extends Analyzer {

  @Override
  public TokenStream tokenStream(String fieldName, Reader reader) {
    final Tokenizer source = new StandardTokenizer(Version.LUCENE_30, reader);
    TokenStream result = new ArabicStemFilter(source);
    result = new ArabicNormalizationFilter(result);
    return result;
  }
}
