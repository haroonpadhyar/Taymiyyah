package com.maktashaf.taymiyyah.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    String searchedTerm = req.getParameter("term");
    String termHidden = req.getParameter("termHidden");
    String term = termHidden;
    String locale = req.getParameter("locale");
    String src = req.getParameter("src");
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

    SearchResult searchResult = process(term, currentPage, localeEnum, original);
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
}
