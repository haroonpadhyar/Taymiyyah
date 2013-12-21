package com.maktashaf.taymiyyah.search.service;

import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import com.maktashaf.taymiyyah.vo.SearchResult;

/**
 * @author Haroon Anwar padhyar.
 */
public interface QuranSearchService {

  public SearchResult doFullTextSearch(SearchParam searchParam);
  public Quran findByAccumId(int accumId, LocaleEnum localeEnum);
  public Quran findByAyahId(int surahId, int ayahId, LocaleEnum localeEnum);
}
