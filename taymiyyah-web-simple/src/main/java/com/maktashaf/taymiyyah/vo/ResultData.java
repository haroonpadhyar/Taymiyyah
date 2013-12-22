package com.maktashaf.taymiyyah.vo;

import java.util.List;

import com.maktashaf.taymiyyah.model.Quran;

/**
 * @author: Haroon
 */
public class ResultData {
  private int pageNo;
  List<Quran> quranList;

  public ResultData() {

  }
  public ResultData(int pageNo, List<Quran> quranList) {
    this.pageNo = pageNo;
    this.quranList = quranList;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public List<Quran> getQuranList() {
    return quranList;
  }

  public void setQuranList(List<Quran> quranList) {
    this.quranList = quranList;
  }
}
