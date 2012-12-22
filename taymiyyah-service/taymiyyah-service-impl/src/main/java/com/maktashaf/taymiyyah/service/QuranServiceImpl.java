package com.maktashaf.taymiyyah.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maktashaf.taymiyyah.domain.Quran;
import com.maktashaf.taymiyyah.repository.QuranRepo;
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

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public List<Quran> doFullTextSearch(String text){
    return quranRepo.fullTextSearchByAyah(text);
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
