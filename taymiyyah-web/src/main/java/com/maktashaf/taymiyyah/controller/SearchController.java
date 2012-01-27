package com.maktashaf.taymiyyah.controller;

import java.util.ArrayList;
import java.util.List;

import com.maktashaf.taymiyyah.domain.Quran;
import com.maktashaf.taymiyyah.repository.IndexGeneratorRepo;
import com.maktashaf.taymiyyah.service.QuranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Haroon Anwar padhyar.
 */
@Controller
public class SearchController {

  @Autowired
  QuranService quranService;

  @Autowired
  IndexGeneratorRepo generatorRepo;

  @RequestMapping("/search")
  public ModelAndView doFulltextSearch() {

    List<Quran> ayahs = new ArrayList<Quran>();
    String str = "قلوبنا";
    ayahs = quranService.doFullTextSearch(str);
//    Quran quran = quranService.findAyahByAccumId(100L);
//    ayahs.add(quran);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("search");
    mav.addObject("ayahs", ayahs);
    return mav;
  }

  @RequestMapping("/gen")
  public ModelAndView doIndex() {

    generatorRepo.createIndexes();
    List<Quran> ayahs = new ArrayList<Quran>();
    ModelAndView mav = new ModelAndView();
    mav.setViewName("search");
    mav.addObject("ayahs", ayahs);
    return mav;
  }
}
