package org.maktashaf.taymiyyah.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent Entity maps to 'quran' database table.
 *
 * @author Haroon Anwar Padhyar.
 */
@Entity
@Table(name = "quran")
public class Quran {

  @Id
  @Column(name = "ACCM_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  private int accmId;

  @Column(name = "AYAH_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  @Basic
  private int ayahId;

  @Column(name = "AYAH_TEXT", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  private String ayahText;

  @Column(name = "ORIGIN", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  private String origin;

  @Column(name = "TYPE", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  private String type;

  @Column(name = "CONTEXT", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  private String context;

  @Column(name = "SURAH_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  @Basic
  private int surahId;

  @Column(name = "SURAH_NAME", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  private String surahName;

  @Column(name = "JUZ_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  @Basic
  private int juzId;

  @Column(name = "JUZ_NAME", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  private String juzName;

  @Column(name = "REVELATION_ORDER", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  @Basic
  private int revelationOrder;


  public int getAccmId() {
    return accmId;
  }

  public void setAccmId(int accmId) {
    this.accmId = accmId;
  }

  public int getAyahId() {
    return ayahId;
  }

  public void setAyahId(int ayahId) {
    this.ayahId = ayahId;
  }

  public String getAyahText() {
    return ayahText;
  }

  public void setAyahText(String ayahText) {
    this.ayahText = ayahText;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public int getSurahId() {
    return surahId;
  }

  public void setSurahId(int surahId) {
    this.surahId = surahId;
  }

  public String getSurahName() {
    return surahName;
  }

  public void setSurahName(String surahName) {
    this.surahName = surahName;
  }

  public int getJuzId() {
    return juzId;
  }

  public void setJuzId(int juzId) {
    this.juzId = juzId;
  }

  public String getJuzName() {
    return juzName;
  }

  public void setJuzName(String juzName) {
    this.juzName = juzName;
  }

  public int getRevelationOrder() {
    return revelationOrder;
  }

  public void setRevelationOrder(int revelationOrder) {
    this.revelationOrder = revelationOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Quran quran = (Quran) o;

    if (accmId != quran.accmId) {
      return false;
    }
    if (ayahId != quran.ayahId) {
      return false;
    }
    if (juzId != quran.juzId) {
      return false;
    }
    if (revelationOrder != quran.revelationOrder) {
      return false;
    }
    if (surahId != quran.surahId) {
      return false;
    }
    if (ayahText != null ? !ayahText.equals(quran.ayahText) : quran.ayahText != null) {
      return false;
    }
    if (context != null ? !context.equals(quran.context) : quran.context != null) {
      return false;
    }
    if (juzName != null ? !juzName.equals(quran.juzName) : quran.juzName != null) {
      return false;
    }
    if (origin != null ? !origin.equals(quran.origin) : quran.origin != null) {
      return false;
    }
    if (surahName != null ? !surahName.equals(quran.surahName) : quran.surahName != null) {
      return false;
    }
    if (type != null ? !type.equals(quran.type) : quran.type != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = accmId;
    result = 31 * result + ayahId;
    result = 31 * result + (ayahText != null ? ayahText.hashCode() : 0);
    result = 31 * result + (origin != null ? origin.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (context != null ? context.hashCode() : 0);
    result = 31 * result + surahId;
    result = 31 * result + (surahName != null ? surahName.hashCode() : 0);
    result = 31 * result + juzId;
    result = 31 * result + (juzName != null ? juzName.hashCode() : 0);
    result = 31 * result + revelationOrder;
    return result;
  }
}
