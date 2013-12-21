package com.maktashaf.taymiyyah.repository.jdbc;

import java.util.List;

import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.model.Quran;

/**
 * @author Haroon Anwar padhyar.
 */
public interface QuranJDBCRepo {

  List<Quran> findAll(LocaleEnum localeEnum);
  public void fillTranslation(List<Quran> quranList, LocaleEnum localeEnum, boolean isOriginal);
  Quran findByAccumId(int accumId, LocaleEnum localeEnum);
  Quran findByAyahId(int surahId, int ayahId, LocaleEnum localeEnum);
}
