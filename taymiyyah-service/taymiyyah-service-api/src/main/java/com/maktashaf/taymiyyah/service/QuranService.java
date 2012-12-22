package com.maktashaf.taymiyyah.service;

import java.util.List;

import com.maktashaf.taymiyyah.domain.Quran;

/**
 * @author Haroon Anwar padhyar.
 */
public interface QuranService {

  public List<Quran> doFullTextSearch(String text);
  public Quran findAyahByAccumId(long ayahId);
  public List<Quran> searchByAyahId(int surahId, int ayahId);
}
