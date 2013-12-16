package com.maktashaf.taymiyyah.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.maktashaf.taymiyyah.domain.Quran;
import com.maktashaf.taymiyyah.domain.QuranEnYousufali;
import com.maktashaf.taymiyyah.domain.QuranUrMaududi;
import com.maktashaf.taymiyyah.repository.QuranEnYousufAliRepo;
import com.maktashaf.taymiyyah.repository.QuranRepo;
import com.maktashaf.taymiyyah.repository.QuranUrMaududiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Haroon Anwar padhyar.
 */
@Service
@Transactional(readOnly = true)
public class QuranServiceImpl implements QuranService{

  @Autowired
  QuranRepo quranRepo;

  @Autowired
  QuranUrMaududiRepo quranUrMaududiRepo;

  @Autowired
  QuranEnYousufAliRepo quranEnYousufAliRepo;

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public List<Quran> doFullTextSearch(String text){
    return quranRepo.fullTextSearchByAyah(text);
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public List<Quran> doFullTextSearchUr(String text){
    List<QuranUrMaududi> quranUrMaududis = quranUrMaududiRepo.fullTextSearchByAyah(text);

    Iterable<Quran> qurans = Iterables.transform(
        quranUrMaududis, new Function<QuranUrMaududi, Quran>() {
      @Nullable
      @Override
      public Quran apply(@Nullable QuranUrMaududi quranUrMaududi) {
        return quranUrMaududi.getQuran();
      }
    }
    );

    return Lists.newArrayList(qurans);
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public List<Quran> doFullTextSearchEn(String text){
    List<QuranEnYousufali> quranEnYousufalis = quranEnYousufAliRepo.fullTextSearchByAyah(text);

    Iterable<Quran> qurans = Iterables.transform(
        quranEnYousufalis, new Function<QuranEnYousufali, Quran>() {
      @Nullable
      @Override
      public Quran apply(@Nullable QuranEnYousufali quranUrMaududi) {
        return quranUrMaududi.getQuran();
      }
    }
    );

    return Lists.newArrayList(qurans);
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public Quran findAyahByAccumId(long ayahId){
    return quranRepo.findById(ayahId);
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public List<Quran> searchByAyahId(int surahId, int ayahId){
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("surahId", surahId);
    params.put("ayahId", ayahId);
    return quranRepo.findByNamedQueryAndNamedParams("searchByAyahId", params);
  }


}
