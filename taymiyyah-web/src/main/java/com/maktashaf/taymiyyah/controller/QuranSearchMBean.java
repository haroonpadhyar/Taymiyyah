package com.maktashaf.taymiyyah.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.maktashaf.taymiyyah.domain.Quran;
import com.maktashaf.taymiyyah.service.QuranService;

/**
 * @author Haroon Anwar padhyar.
 */
@ManagedBean(name = "QuranSearchMBean")
public class QuranSearchMBean {

  @ManagedProperty(value = "#{quranServiceImpl}")
  QuranService quranService;

  String searchTerm = "قلوبنا";
  List<Quran> ayahs;
  int surahId = 0;
  int ayahId = 0;

  @PostConstruct
  public void init() {
    searchTerm = new String();
    ayahs = new ArrayList<Quran>();
    surahId = 0;
    ayahId = 0;
  }

  public void doFulltextSearch() {

    try {
      long t1 = System.currentTimeMillis();
      ayahs = quranService.doFullTextSearch(searchTerm);
      long t2 = System.currentTimeMillis();
      System.out.println("Time: "+(t2-t1));
    }
    catch(Exception e) {
      e.printStackTrace();
      ayahs = new ArrayList<Quran>();
    }
  }

  public void doFulltextSearchUr() {

    try {
      long t1 = System.currentTimeMillis();
      ayahs = quranService.doFullTextSearchUr(searchTerm);
      long t2 = System.currentTimeMillis();
      System.out.println("Time: "+(t2-t1));
    }
    catch(Exception e) {
      e.printStackTrace();
      ayahs = new ArrayList<Quran>();
    }
  }

  public void doFulltextSearchEn() {

    try {
      long t1 = System.currentTimeMillis();
      ayahs = quranService.doFullTextSearchEn(searchTerm);
      long t2 = System.currentTimeMillis();
      System.out.println("Time: "+(t2-t1));
    }
    catch(Exception e) {
      e.printStackTrace();
      ayahs = new ArrayList<Quran>();
    }
  }

  public void searchByAyahId() {

    try {
      ayahs = quranService.searchByAyahId(surahId, ayahId);
    }
    catch(Exception e) {
      e.printStackTrace();
      ayahs = new ArrayList<Quran>();
    }
  }

  public QuranService getQuranService() {
    return quranService;
  }

  public void setQuranService(QuranService quranService) {
    this.quranService = quranService;
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public void setSearchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
  }

  public List<Quran> getAyahs() {
    return ayahs;
  }

  public void setAyahs(List<Quran> ayahs) {
    this.ayahs = ayahs;
  }

  public int getSurahId() {
    return surahId;
  }

  public void setSurahId(int surahId) {
    this.surahId = surahId;
  }

  public int getAyahId() {
    return ayahId;
  }

  public void setAyahId(int ayahId) {
    this.ayahId = ayahId;
  }

}

