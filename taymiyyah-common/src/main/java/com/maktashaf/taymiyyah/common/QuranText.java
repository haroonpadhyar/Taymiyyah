package com.maktashaf.taymiyyah.common;

/**
 * @author: Haroon
 */
public enum QuranText {
  Quran("QURAN"),
  QuranUrMaududi("QURAN_UR_MAUDUDI") ,
  QuranEnYousufAli("QURAN_EN_YOUSUFALI")
  ;

  private String value;
  QuranText(String value){
    this.value = value;
  }

  public String value() {
    return value;
  }
}
