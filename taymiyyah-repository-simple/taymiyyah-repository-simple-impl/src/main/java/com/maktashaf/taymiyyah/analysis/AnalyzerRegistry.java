package com.maktashaf.taymiyyah.analysis;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.maktashaf.taymiyyah.analysis.en.PhoneticEnglishAnalyzer;
import com.maktashaf.taymiyyah.analysis.ur.UrduAnalyzer;
import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.Translator;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.util.Version;

/**
 * @author: Haroon
 */
public class AnalyzerRegistry {
  private static Table<LocaleEnum, Translator, Analyzer> analyzerTable = HashBasedTable.create();

  static {
    ArabicAnalyzer arabicAnalyzer = new ArabicAnalyzer(Version.LUCENE_31);
    analyzerTable.put(LocaleEnum.Original, Translator.None, arabicAnalyzer);
    analyzerTable.put(LocaleEnum.Ar, Translator.None, arabicAnalyzer);
    analyzerTable.put(LocaleEnum.Ur, Translator.Maududi, new UrduAnalyzer());
    analyzerTable.put(LocaleEnum.En, Translator.YousufAli, new PhoneticEnglishAnalyzer(Version.LUCENE_31));
  }

  public static Analyzer getAnalyzer(LocaleEnum localeEnum, Translator translator){
    return analyzerTable.get(localeEnum, translator);
  }

}
