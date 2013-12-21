package com.maktashaf.taymiyyah.repository.lucene.search;

import java.io.File;

import com.maktashaf.taymiyyah.analysis.AnalyzerRegistry;
import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.Translator;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;

/**
 * @author: Haroon
 */
public class QuranTextSearcher extends AbstractQuranSearcher implements QuranSearcher{
  private static Logger logger = Logger.getLogger(QuranTextSearcher.class);

  @Override
  protected String resolveIndexPath(SearchParam searchParam) {
    StringBuilder indexPath = new StringBuilder();
    indexPath.append(searchParam.getContextPath());
    indexPath.append(File.separator);
    indexPath.append(LocaleEnum.Ar.value().getLanguage());
    return indexPath.toString();
  }

  @Override
  protected String getSearchedTextFromField(Quran quran) {
    return quran.getAyahText();
  }

  @Override
  protected void setSearchedTextInField(Quran quran, String text) {
    quran.setAyahText(text);
  }

  @Override
  protected Analyzer chooseAnalyzer(SearchParam searchParam){
    return AnalyzerRegistry.getAnalyzer(LocaleEnum.Original, Translator.None);
  }
}
