package com.maktashaf.taymiyyah.repository.generator;

import java.io.File;
import java.util.List;

import com.maktashaf.taymiyyah.analysis.en.PhoneticEnglishAnalyzer;
import com.maktashaf.taymiyyah.analysis.ur.UrduAnalyzer;
import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.QuranField;
import com.maktashaf.taymiyyah.common.Translator;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import com.maktashaf.taymiyyah.repository.jdbc.QuranJDBCRepo;
import com.maktashaf.taymiyyah.repository.jdbc.QuranJDBCRepoImpl;
import com.maktashaf.taymiyyah.repository.lucene.search.QuranSearcher;
import com.maktashaf.taymiyyah.repository.lucene.search.QuranTextSearcher;
import com.maktashaf.taymiyyah.vo.SearchResult;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

/**
 * @author: Haroon
 */
public class IndexGenerator {
  QuranJDBCRepo quranJDBCRepo = new QuranJDBCRepoImpl();
  final String contextPath = "./index";

  @Test
  public void createIndexAr(){
    try {
      Directory dir = FSDirectory.open(new File(contextPath + File.separator + LocaleEnum.Ar.value().getLanguage()));
      ArabicAnalyzer arabicAnalyzer = new ArabicAnalyzer(Version.LUCENE_31);
      IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31,
          arabicAnalyzer);
      iwc.setOpenMode(OpenMode.CREATE);
      IndexWriter writer = new IndexWriter(dir, iwc);

      // loading data from database.
      List<Quran> qurans = quranJDBCRepo.findAll(LocaleEnum.Ar);
      for (Quran quran : qurans) {
        System.out.println("Indexing... "+quran.getAccmId()+" : "+quran.getAyahText());
        Document doc = new Document();
        doc.add(new Field(QuranField.accumId.value(), String.valueOf(quran.getAccmId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.ayahId.value(), String.valueOf(quran.getAyahId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.surahId.value(), String.valueOf(quran.getSurahId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.juzId.value(), String.valueOf(quran.getJuzId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.surahName.value(), String.valueOf(quran.getSurahName()), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field(QuranField.juzName.value(), String.valueOf(quran.getJuzName()), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field(QuranField.ayahText.value(), String.valueOf(quran.getAyahText()), Store.YES, Index.ANALYZED));
        writer.addDocument(doc);
      }

      writer.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void createIndexUr(){
    try {
      Directory dir = FSDirectory.open(new File(contextPath + File.separator + LocaleEnum.Ur.value().getLanguage()));
      Analyzer analyzer = new UrduAnalyzer();

      IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_30,
          analyzer);
      iwc.setOpenMode(OpenMode.CREATE);
      IndexWriter writer = new IndexWriter(dir, iwc);

      // loading data from database.
      List<Quran> qurans = quranJDBCRepo.findAll(LocaleEnum.Ur);
      for (Quran quran : qurans) {
        System.out.println("Indexing... "+quran.getAccmId()+" : "+quran.getAyahText());
        Document doc = new Document();
        doc.add(new Field(QuranField.accumId.value(), String.valueOf(quran.getAccmId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.ayahId.value(), String.valueOf(quran.getAyahId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.surahId.value(), String.valueOf(quran.getSurahId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.juzId.value(), String.valueOf(quran.getJuzId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.surahName.value(), String.valueOf(quran.getSurahName()), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field(QuranField.juzName.value(), String.valueOf(quran.getJuzName()), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field(QuranField.ayahText.value(), String.valueOf(quran.getAyahText()), Store.YES, Index.ANALYZED));
        writer.addDocument(doc);
      }

      writer.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void createIndexEn(){
    try {
      Directory dir = FSDirectory.open(new File(contextPath + File.separator + LocaleEnum.En.value().getLanguage()));
      Analyzer analyzer = new PhoneticEnglishAnalyzer(Version.LUCENE_31);

      IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31,
          analyzer);
      iwc.setOpenMode(OpenMode.CREATE);
      IndexWriter writer = new IndexWriter(dir, iwc);

      // loading data from database.
      List<Quran> qurans = quranJDBCRepo.findAll(LocaleEnum.En);
      for (Quran quran : qurans) {
        System.out.println("Indexing... "+quran.getAccmId()+" : "+quran.getAyahText());
        Document doc = new Document();
        doc.add(new Field(QuranField.accumId.value(), String.valueOf(quran.getAccmId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.ayahId.value(), String.valueOf(quran.getAyahId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.surahId.value(), String.valueOf(quran.getSurahId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.juzId.value(), String.valueOf(quran.getJuzId()), Store.YES, Index.NO));
        doc.add(new Field(QuranField.surahName.value(), String.valueOf(quran.getSurahName()), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field(QuranField.juzName.value(), String.valueOf(quran.getJuzName()), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field(QuranField.ayahText.value(), String.valueOf(quran.getAyahText()), Store.YES, Index.ANALYZED));
        writer.addDocument(doc);
      }

      writer.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void searchIndexAr(){
    try {
      String term = "قلوبنا";
//      term = "محمد";
//      term = "علي";
//      term = "على";

      SearchParam searchParam = SearchParam.builder()
          .withContextPath(contextPath)
          .withTerm(term)
          .withLocale(LocaleEnum.Ar)
          .withTranslator(Translator.None)
          .withOriginal(true)
          .withPageNo(1)
          .withPageSize(2)
          .build();
      QuranSearcher quranSearcher = new QuranTextSearcher();
      SearchResult searchResult = quranSearcher.search(searchParam);

      for (Quran quran : searchResult.getQuranList()) {
        System.out.println(quran.getAyahText());
        System.out.println("-------------------");
      }
//      assertEquals(6, search.size());
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void searchIndexUr(){
    try {
      String term = "قلوبنا";
      term = "محمد";
//      term = "علي";
//      term = "على";

      SearchParam searchParam = SearchParam.builder()
          .withContextPath(contextPath)
          .withTerm(term)
          .withLocale(LocaleEnum.Ur)
          .withTranslator(Translator.Maududi)
          .withOriginal(false)
          .withPageNo(3)
          .withPageSize(2)
          .build();
      QuranSearcher quranSearcher = new QuranTextSearcher();
      SearchResult searchResult = quranSearcher.search(searchParam);

      System.out.println("Total: " + searchResult.getQuranList().size());
      for (Quran quran : searchResult.getQuranList()) {
        System.out.println(quran.getAyahTranslationText());
        System.out.println("-------------------");
      }
//      assertEquals(100, search.size());
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void searchIndexEn(){
    try {
      String term = "Mohamad";
      SearchParam searchParam = SearchParam.builder()
          .withContextPath(contextPath)
          .withTerm(term)
          .withLocale(LocaleEnum.En)
          .withTranslator(Translator.YousufAli)
          .withOriginal(false)
          .withPageNo(2)
          .withPageSize(2)
          .build();
      QuranSearcher quranSearcher = new QuranTextSearcher();
      SearchResult searchResult = quranSearcher.search(searchParam);

      for (Quran quran : searchResult.getQuranList()) {
        System.out.println(quran.getAyahTranslationText());
        System.out.println("-------------------");
      }
//      assertEquals(4, search.size());

    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void genericTest(){
    int a = 15;
    int b = 9;
    System.out.println(15/9);
    System.out.println(Math.ceil(15/9));
    System.out.println(Math.ceil((double)a/b));
  }
}
