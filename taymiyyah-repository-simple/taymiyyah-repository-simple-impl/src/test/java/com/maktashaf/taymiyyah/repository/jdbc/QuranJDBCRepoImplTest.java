package com.maktashaf.taymiyyah.repository.jdbc;

import java.util.List;

import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.model.Quran;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: Haroon
 */
public class QuranJDBCRepoImplTest {
  QuranJDBCRepo quranJDBCRepo = new QuranJDBCRepoImpl();

  @Test
  public void shouldFindAllAr(){
    List<Quran> all = quranJDBCRepo.findAll(LocaleEnum.Ar);
    System.out.println(all.size());
    assertEquals(6236, all.size());
    assertNotNull(all.get(0).getAyahText());
    System.out.println(all.get(1).getAyahText());
  }

  @Test
  public void shouldFindAllUr(){
    List<Quran> all = quranJDBCRepo.findAll(LocaleEnum.Ur);
    System.out.println(all.size());
    assertEquals(6236, all.size());
    assertNotNull(all.get(0).getAyahText());
    System.out.println(all.get(1).getAyahText());
  }

  @Test
  public void shouldFindAllEn(){
    List<Quran> all = quranJDBCRepo.findAll(LocaleEnum.En);
    System.out.println(all.size());
    assertEquals(6236, all.size());
    assertNotNull(all.get(0).getAyahText());
    System.out.println(all.get(1).getAyahText());
  }

  @Test
  public void shouldFindAccumId(){
    Quran quran  = quranJDBCRepo.findByAccumId(2, LocaleEnum.En);
    assertNotNull(quran);
    assertNotNull(quran.getAyahText());
    System.out.println(quran.getAyahText());
  }

  @Test
  public void shouldFindAyahId(){
    Quran quran  = quranJDBCRepo.findByAyahId(2, 3, LocaleEnum.En);
    assertNotNull(quran);
    assertNotNull(quran.getAyahText());
    System.out.println(quran.getAyahText());
  }

}
