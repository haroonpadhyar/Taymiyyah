package com.maktashaf.taymiyyah.repository;

import java.util.List;

import com.maktashaf.taymiyyah.domain.Quran;
import com.maktashaf.taymiyyah.domain.QuranEnYousufali;
import com.maktashaf.taymiyyah.domain.QuranUrMaududi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Haroon Anwar padhyar.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-Test.xml"})
public class QuranRepoTest {

  @Autowired
  private QuranRepo quranRepo;

  @Autowired
  private QuranUrMaududiRepo quranUrMaududiRepo;

  @Autowired
  private QuranEnYousufAliRepo quranEnYousufAliRepo;

  @Test
  public void testGetByAccumId(){

    Quran ayat = quranRepo.findById(1000L);
    System.out.println(ayat.getSurahId());
  }

  @Test
  @Transactional
  public void testFullTextSearchByAyah(){

    String text = "قلوبنا";
//    text = "على"; // this is in stop letters. stop filter will ignore it.
//    text = "علي"  ;
//    text = "على  قلوبنا";
    System.out.println(text);
    List<Quran> ayats = quranRepo.fullTextSearchByAyah(text);
    System.out.println(ayats.size());
    for(Quran quran: ayats){
      System.out.println(quran.getAyahText()
          +" : "+quran.getSurahName()
          +" : "+quran.getAyahId());
    }
  }

  @Test
  @Transactional
  public void testFullTextSearchByAyahUr(){

    String text = "محمدؐ";
    text = "محمد"  ;
//    text = "على  قلوبنا";
    System.out.println(text);
    List<QuranUrMaududi> ayats = quranUrMaududiRepo.fullTextSearchByAyah(text);
    System.out.println(ayats.size());
    for(QuranUrMaududi quran: ayats){
      System.out.println(quran.getAyahText()
          +" : "+quran.getSurahName()
          +" : "+quran.getAyahId());
    }
  }

  @Test
  @Transactional
  public void testFullTextSearchByAyahEn(){

    String text = "Muhammad";
    System.out.println(text);
    List<QuranEnYousufali> ayats = quranEnYousufAliRepo.fullTextSearchByAyah(text);
    System.out.println(ayats.size());
    for(QuranEnYousufali quran: ayats){
      System.out.println(quran.getAyahText()
          +" : "+quran.getSurahName()
          +" : "+quran.getAyahId());
    }
  }

}
