package com.maktashaf.taymiyyah.vo;

import java.util.List;

import com.maktashaf.taymiyyah.model.Quran;

/**
 * @author: Haroon
 */
public class ResultData {
  private int currentPage;
  private int totalPages;
  private int totalHits;
  private int original;
  private String term;
  private String lang;
  List<Quran> quranList;

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public ResultData withCurrentPage(int currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public ResultData withTotalPages(int totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  public int getTotalHits() {
    return totalHits;
  }

  public void setTotalHits(int totalHits) {
    this.totalHits = totalHits;
  }

  public ResultData withTotalHits(int totalHits) {
    this.totalHits = totalHits;
    return this;
  }

  public int getOriginal() {
    return original;
  }

  public void setOriginal(int original) {
    this.original = original;
  }

  public ResultData withOriginal(int original) {
    this.original = original;
    return this;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public ResultData withTerm(String term) {
    this.term = term;
    return this;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public ResultData withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public List<Quran> getQuranList() {
    return quranList;
  }

  public void setQuranList(List<Quran> quranList) {
    this.quranList = quranList;
  }

  public ResultData withQuranList(List<Quran> quranList) {
    this.quranList = quranList;
    return this;
  }
}
