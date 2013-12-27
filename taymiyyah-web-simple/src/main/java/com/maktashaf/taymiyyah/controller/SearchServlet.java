package com.maktashaf.taymiyyah.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.Translator;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import com.maktashaf.taymiyyah.search.service.QuranSearchSearchServiceImpl;
import com.maktashaf.taymiyyah.search.service.QuranSearchService;
import com.maktashaf.taymiyyah.vo.ResultData;
import com.maktashaf.taymiyyah.vo.SearchResult;
import org.apache.log4j.Logger;

/**
 * @author: Haroon
 */
public class SearchServlet extends HttpServlet{
  private static Logger logger = Logger.getLogger(SearchServlet.class);
  private int PAGE_SIZE = 1;
  private Map<Integer, Integer> ayahCountMap = new HashMap<Integer, Integer>(114);

  QuranSearchService quranSearchService = new QuranSearchSearchServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      List<Quran> quranList = new ArrayList<Quran>();
      String locale = req.getParameter("locale");
      LocaleEnum localeEnum = null;
      if(locale != null && locale.length() > 0)
        localeEnum = LocaleEnum.languageBiMap.look(locale);

      if(localeEnum == null)
        localeEnum = LocaleEnum.Ar;

      req.getSession().setAttribute("locale", localeEnum.value());
      req.getSession().setAttribute("localeLang", localeEnum.value().getLanguage());
      req.getRequestDispatcher("/index.jsp").forward(req, resp);


    }catch(Exception e){
      //TODO send some error message
      logger.error(Throwables.getStackTraceAsString(e));
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      long startTime = System.currentTimeMillis();
      String ajax = req.getParameter("ajax");
      if(ajax != null && ajax.equals("yes"))
        handleAjax(req, resp, startTime);

    }catch(Exception e){
      //TODO send some error message
      logger.error(Throwables.getStackTraceAsString(e));
    }
  }

  @Override
  public void init() throws ServletException {
    String pageSize = getServletContext().getInitParameter("pageSize");
    PAGE_SIZE = Integer.valueOf(pageSize).intValue();
    ayahCountMap.put(1,7);
    ayahCountMap.put(2,286);
    ayahCountMap.put(3,200);
    ayahCountMap.put(4,176);
    ayahCountMap.put(5,120);
    ayahCountMap.put(6,165);
    ayahCountMap.put(7,206);
    ayahCountMap.put(8,75);
    ayahCountMap.put(9,129);
    ayahCountMap.put(10,109);
    ayahCountMap.put(11,123);
    ayahCountMap.put(12,111);
    ayahCountMap.put(13,43);
    ayahCountMap.put(14,52);
    ayahCountMap.put(15,99);
    ayahCountMap.put(16,128);
    ayahCountMap.put(17,111);
    ayahCountMap.put(18,110);
    ayahCountMap.put(19,98);
    ayahCountMap.put(20,135);
    ayahCountMap.put(21,112);
    ayahCountMap.put(22,78);
    ayahCountMap.put(23,118);
    ayahCountMap.put(24,64);
    ayahCountMap.put(25,77);
    ayahCountMap.put(26,227);
    ayahCountMap.put(27,93);
    ayahCountMap.put(28,88);
    ayahCountMap.put(29,69);
    ayahCountMap.put(30,60);
    ayahCountMap.put(31,34);
    ayahCountMap.put(32,30);
    ayahCountMap.put(33,73);
    ayahCountMap.put(34,54);
    ayahCountMap.put(35,45);
    ayahCountMap.put(36,83);
    ayahCountMap.put(37,182);
    ayahCountMap.put(38,88);
    ayahCountMap.put(39,75);
    ayahCountMap.put(40,85);
    ayahCountMap.put(41,54);
    ayahCountMap.put(42,53);
    ayahCountMap.put(43,89);
    ayahCountMap.put(44,59);
    ayahCountMap.put(45,37);
    ayahCountMap.put(46,35);
    ayahCountMap.put(47,38);
    ayahCountMap.put(48,29);
    ayahCountMap.put(49,18);
    ayahCountMap.put(50,45);
    ayahCountMap.put(51,60);
    ayahCountMap.put(52,49);
    ayahCountMap.put(53,62);
    ayahCountMap.put(54,55);
    ayahCountMap.put(55,78);
    ayahCountMap.put(56,96);
    ayahCountMap.put(57,29);
    ayahCountMap.put(58,22);
    ayahCountMap.put(59,24);
    ayahCountMap.put(60,13);
    ayahCountMap.put(61,14);
    ayahCountMap.put(62,11);
    ayahCountMap.put(63,11);
    ayahCountMap.put(64,18);
    ayahCountMap.put(65,12);
    ayahCountMap.put(66,12);
    ayahCountMap.put(67,30);
    ayahCountMap.put(68,52);
    ayahCountMap.put(69,52);
    ayahCountMap.put(70,44);
    ayahCountMap.put(71,28);
    ayahCountMap.put(72,28);
    ayahCountMap.put(73,20);
    ayahCountMap.put(74,56);
    ayahCountMap.put(75,40);
    ayahCountMap.put(76,31);
    ayahCountMap.put(77,50);
    ayahCountMap.put(78,40);
    ayahCountMap.put(79,46);
    ayahCountMap.put(80,42);
    ayahCountMap.put(81,29);
    ayahCountMap.put(82,19);
    ayahCountMap.put(83,36);
    ayahCountMap.put(84,25);
    ayahCountMap.put(85,22);
    ayahCountMap.put(86,17);
    ayahCountMap.put(87,19);
    ayahCountMap.put(88,26);
    ayahCountMap.put(89,30);
    ayahCountMap.put(90,20);
    ayahCountMap.put(91,15);
    ayahCountMap.put(92,21);
    ayahCountMap.put(93,11);
    ayahCountMap.put(94,8);
    ayahCountMap.put(95,8);
    ayahCountMap.put(96,19);
    ayahCountMap.put(97,5);
    ayahCountMap.put(98,8);
    ayahCountMap.put(99,8);
    ayahCountMap.put(100,11);
    ayahCountMap.put(101,11);
    ayahCountMap.put(102,8);
    ayahCountMap.put(103,3);
    ayahCountMap.put(104,9);
    ayahCountMap.put(105,5);
    ayahCountMap.put(106,4);
    ayahCountMap.put(107,7);
    ayahCountMap.put(108,3);
    ayahCountMap.put(109,6);
    ayahCountMap.put(110,3);
    ayahCountMap.put(111,5);
    ayahCountMap.put(112,4);
    ayahCountMap.put(113,5);
    ayahCountMap.put(114,6);

  }

  private SearchResult process(String term, int pageNo, LocaleEnum localeEnum, boolean original){
    StringBuilder realPath = new StringBuilder();
    realPath.append(getServletContext().getRealPath(""));
    realPath.append(File.separator);
    realPath.append("WEB-INF");
    realPath.append(File.separator);
    realPath.append("index");

    // For the time being translator are hard coded. later when we give the facility to
    // select translator from UI, this value will be collected from request.
    Translator translator = Translator.None;
    if(localeEnum == LocaleEnum.Ar)
      translator = Translator.None;
    if(localeEnum == LocaleEnum.Ur)
      translator = Translator.Maududi;
    if(localeEnum == LocaleEnum.En)
      translator = Translator.YousufAli;

    SearchParam searchParam = SearchParam.builder()
        .withContextPath(realPath.toString())
        .withTerm(term)
        .withLocale(localeEnum)
        .withTranslator(translator)
        .withOriginal(original)
        .withPageSize(PAGE_SIZE)
        .withPageNo(pageNo)
        .build();
    SearchResult searchResult = quranSearchService.doFullTextSearch(searchParam);

    return searchResult;
  }

  private void handleAjax(HttpServletRequest req, HttpServletResponse resp, long startTime) throws ServletException, IOException {
    String src = req.getParameter("src");
    if(src.equals("srch")){
      doIdSearch(req, resp);
      return;
    }

    String searchedTerm = req.getParameter("term");
    String termHidden = req.getParameter("termHidden");
    String term = termHidden;
    String locale = req.getParameter("locale");
    String pageNoStr = req.getParameter("currentPage");
    String totalPagesStr = req.getParameter("totalPages");
    boolean original = req.getParameter("original").equals("1");

    if(pageNoStr == null || pageNoStr.length() <=0)
      pageNoStr = String.valueOf("0");

    if(totalPagesStr == null || totalPagesStr.length() <=0)
      totalPagesStr = String.valueOf("0");

    int currentPage = Integer.valueOf(pageNoStr).intValue();
    int totalPage = Integer.valueOf(totalPagesStr).intValue();
    currentPage = Math.max(currentPage, 1); // page should be submitted at least from first page for pagination
    totalPage = Math.max(totalPage, 1);
    if(src.equals("nxt")){
      currentPage += 1;
      currentPage = Math.min(currentPage, totalPage);// shouldn't be more than total pages
    } else if(src.equals("prv")){
      currentPage -= 1;
      currentPage = Math.min(currentPage, totalPage); // shouldn't be more than total pages
      currentPage = Math.max(currentPage, 1); // should not be at 0 or -ve index
    } else if(src.equals("orgSrch")){
      // refresh search
      original = true;
      currentPage = 1;
      term = searchedTerm;
    } else if(src.equals("trnsSrch")){
      // refresh search
      original = false;
      currentPage = 1;
      term = searchedTerm;
    }

    LocaleEnum localeEnum = LocaleEnum.languageBiMap.look(locale);
    if(localeEnum == null)
      localeEnum = LocaleEnum.Ar;

    SearchResult searchResult = SearchResult.builder().withQuranList(new ArrayList<Quran>(1)).build();
    if(null != term && term.length() > 0)
        searchResult = process(term, currentPage, localeEnum, original);
    currentPage = Math.min(currentPage, searchResult.getTotalPages());
    long totalTime = System.currentTimeMillis() - startTime;
    ResultData resultData = new ResultData()
        .withCurrentPage(currentPage)
        .withTotalPages(searchResult.getTotalPages())
        .withTotalHits(searchResult.getTotalHits())
        .withOriginal(original ? 1 : 0)
        .withTerm(term)
        .withLang(localeEnum.value().getLanguage())
        .withTime(String.valueOf(totalTime / 1000f))
        .withQuranList(searchResult.getQuranList());

    Gson gson = new Gson();
    String json = gson.toJson(resultData);

    resp.setContentType("text/html");
    resp.getWriter().write(json);
  }

  private void doIdSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String radio = req.getParameter("radio");
    String surahId = req.getParameter("surahId");
    String ayahId = req.getParameter("ayahId");
    String locale = req.getParameter("locale");
    LocaleEnum localeEnum = LocaleEnum.languageBiMap.look(locale);
    if(localeEnum == null)
      localeEnum = LocaleEnum.Ar;

    int surahNo = 0;
    int ayahNo = 0;
    Quran quran = null;
    try{
      surahNo = Integer.valueOf(surahId);
      ayahNo = Integer.valueOf(ayahId);
    } catch(Exception e){
      logger.error(e.getMessage());
      surahNo = 0;
      ayahNo = 0;
    }

    if(radio.equals("idSrch")) {
      if((surahNo > 0 && surahNo <= 114)
          && (ayahNo > 0 && ayahNo <= ayahCountMap.get(surahNo)))
        quran = quranSearchService.findByAyahId(surahNo, ayahNo, localeEnum);
    }
    else if(radio.equals("srSrch")){
      if(ayahNo > 0 && ayahNo <= 6236)
        quran = quranSearchService.findByAccumId(ayahNo, localeEnum);
    }

    List<Quran> quranList = new ArrayList<Quran>();
    if(quran != null)
      quranList.add(quran);
    ResultData resultData = new ResultData()
        .withLang(localeEnum.value().getLanguage())
        .withQuranList(quranList);

    Gson gson = new Gson();
    String json = gson.toJson(resultData);

    resp.setContentType("text/html");
    resp.getWriter().write(json);
  }

}
