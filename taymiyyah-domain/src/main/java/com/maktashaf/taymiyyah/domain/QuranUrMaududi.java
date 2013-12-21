package com.maktashaf.taymiyyah.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.solr.analysis.ArabicNormalizationFilterFactory;
import org.apache.solr.analysis.ArabicStemFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;

import org.hibernate.annotations.Fetch;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 * Persistent Entity maps to 'quran_ur_maududi' database table.
 *
 * @author Haroon Anwar Padhyar.
 */
@Entity
@Table(name = "quran_ur_maududi")
@Indexed
@AnalyzerDef(
    name="uranalyzer",
    tokenizer =@TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory=ArabicStemFilterFactory.class),
			@TokenFilterDef(factory=ArabicNormalizationFilterFactory.class)
    } )
public class QuranUrMaududi {

  @Id
  @Column(name = "ACCM_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  private long accmId;

  @Column(name = "AYAH_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
  @Basic
  private int ayahId;

  @Column(name = "AYAH_TEXT", nullable = false, insertable = false, updatable = false, length = 65535, precision = 0)
  @Basic
  @Field(index= Index.TOKENIZED, store= Store.YES, analyzer=@Analyzer(impl = ArabicAnalyzer.class))
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

  @OneToOne(fetch = FetchType.EAGER)
  @PrimaryKeyJoinColumn
  @Fetch(org.hibernate.annotations.FetchMode.JOIN)
  Quran quran;

  public long getAccmId() {
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

  public Quran getQuran() {
    return quran;
  }

  public void setQuran(Quran quran) {
    this.quran = quran;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    QuranUrMaududi that = (QuranUrMaududi) o;

    if (accmId != that.accmId) {
      return false;
    }
    if (ayahId != that.ayahId) {
      return false;
    }
    if (juzId != that.juzId) {
      return false;
    }
    if (revelationOrder != that.revelationOrder) {
      return false;
    }
    if (surahId != that.surahId) {
      return false;
    }
    if (ayahText != null ? !ayahText.equals(that.ayahText) : that.ayahText != null) {
      return false;
    }
    if (context != null ? !context.equals(that.context) : that.context != null) {
      return false;
    }
    if (juzName != null ? !juzName.equals(that.juzName) : that.juzName != null) {
      return false;
    }
    if (origin != null ? !origin.equals(that.origin) : that.origin != null) {
      return false;
    }
    if (surahName != null ? !surahName.equals(that.surahName) : that.surahName != null) {
      return false;
    }
    if (type != null ? !type.equals(that.type) : that.type != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
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
