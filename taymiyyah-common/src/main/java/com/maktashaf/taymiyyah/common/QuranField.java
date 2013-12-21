package com.maktashaf.taymiyyah.common;

/**
 * @author: Haroon
 */
public enum QuranField {
  accumId("ACCM_ID"),
  ayahId("AYAH_ID"),
  surahId("SURAH_ID"),
  juzId("JUZ_ID"),
  revelationOrder("REVELATION_ORDER"),
  ayahText("AYAH_TEXT"),
  origin("ORIGIN"),
  type("TYPE"),
  context("CONTEXT"),
  surahName("SURAH_NAME"),
  juzName("JUZ_NAME")
  ;

  private String value;
   QuranField(String value){
    this.value = value;
  }

  public String value() {
    return value;
  }
}
