package com.maktashaf.taymiyyah.repository.lucene.search;

import java.io.File;

import com.maktashaf.taymiyyah.analysis.AnalyzerRegistry;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;

/**
 * @author: Haroon
 */
public class QuranTranslationSearcher extends AbstractQuranSearcher implements QuranSearcher{
  private static Logger logger = Logger.getLogger(QuranTranslationSearcher.class);

  @Override
  protected String resolveIndexPath(SearchParam searchParam) {
    StringBuilder indexPath = new StringBuilder();
    indexPath.append(searchParam.getContextPath());
    indexPath.append(File.separator);
    indexPath.append(searchParam.getLocaleEnum().value().getLanguage());
    return indexPath.toString();
  }

  @Override
  protected String getSearchedTextFromField(Quran quran) {
    return quran.getAyahTranslationText();
  }

  @Override
  protected void setSearchedTextInField(Quran quran, String text) {
    quran.setAyahTranslationText(text);
  }

  @Override
  protected Analyzer chooseAnalyzer(SearchParam searchParam){
    return AnalyzerRegistry.getAnalyzer(searchParam.getLocaleEnum(), searchParam.getTranslator());
  }
}
