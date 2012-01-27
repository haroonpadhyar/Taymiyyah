package com.maktashaf.taymiyyah.service;

import java.util.List;

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

  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public List<Quran> doFullTextSearch(String text){
    return quranRepo.fullTextSearchByAyah(text);
  }

  @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
  public Quran findAyahByAccumId(long ayahId){
    return quranRepo.findById(ayahId);
  }
}
