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
import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.Translator;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import com.maktashaf.taymiyyah.search.service.QuranSearchSearchServiceImpl;
import com.maktashaf.taymiyyah.search.service.QuranSearchService;
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
      if(req.getParameter("locale") != null && req.getParameter("locale").length() > 0){
        String locale = req.getParameter("locale");
        LocaleEnum localeEnum = LocaleEnum.languageBiMap.look(locale);
        if(localeEnum != null){
          req.setAttribute("locale", localeEnum.value());
          req.setAttribute("localeLang", localeEnum.value().getLanguage());
        }
        else{
          req.setAttribute("locale", LocaleEnum.Ar.value());
          req.setAttribute("localeLang", LocaleEnum.Ar.value().getLanguage());
        }
      }

      req.setAttribute("quranList", quranList);
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
      String realPath = req.getSession().getServletContext().getRealPath("");
      realPath = realPath+ File.separator +"WEB-INF"+File.separator+"index";
      String submit = req.getParameter("original");
      String pageNoStr = req.getParameter("currentPageHidden");
      String totalPagesStr = req.getParameter("totalPagesHidden");
      if(pageNoStr == null || pageNoStr.length() <=0)
        pageNoStr = String.valueOf("0");

      if(totalPagesStr == null || totalPagesStr.length() <=0)
        totalPagesStr = String.valueOf("0");

      int pageNo = Integer.valueOf(pageNoStr).intValue();
      int totalPage = Integer.valueOf(totalPagesStr).intValue();
      boolean original = true;
      String term = "";
      if(submit.equals("nxt") || submit.equals("prv")){ // pagination button submit
        term = req.getParameter("termHidden");
        original = req.getParameter("originalHidden").equals("1");
        pageNo = Math.max(pageNo, 1); // page should be submitted at least from first page for pagination
        if(submit.equals("nxt")){
          pageNo += 1;
          pageNo = Math.min(pageNo, totalPage);// shouldn't be more than total pages
        } else if(submit.equals("prv")){
          pageNo -= 1;
          pageNo = Math.min(pageNo, totalPage); // shouldn't be more than total pages
          pageNo = Math.max(pageNo, 1); // should not be at 0 or -ve index
        }
      } else {// search term button submit
        term = req.getParameter("term");
        original = submit.equals("1");
        pageNo = 1;
      }

      String locale = req.getParameter("locale");
      LocaleEnum localeEnum = LocaleEnum.languageBiMap.look(locale);
      if(localeEnum != null){
        req.setAttribute("locale", localeEnum.value());
        req.setAttribute("localeLang", localeEnum.value().getLanguage());
      }
      else {
        req.setAttribute("locale", LocaleEnum.Ar.value());
        req.setAttribute("localeLang", LocaleEnum.Ar.value().getLanguage());
      }

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
          .withContextPath(realPath)
          .withTerm(term)
          .withLocale(localeEnum)
          .withTranslator(translator)
          .withOriginal(original)
          .withPageSize(PAGE_SIZE)
          .withPageNo(pageNo)
          .build();
      SearchResult searchResult = quranSearchService.doFullTextSearch(searchParam);

      req.setAttribute("currentPage", Integer.valueOf(pageNo));
      req.setAttribute("totalPage", Integer.valueOf(searchResult.getTotalPages()));
      req.setAttribute("totalHits", Integer.valueOf(searchResult.getTotalHits()));
      req.setAttribute("original", (original?1:0));
      req.setAttribute("term", term);
      req.setAttribute("quranList", searchResult.getQuranList());
      if(logger.isDebugEnabled()){
        logger.debug("Time Taken: "+(System.currentTimeMillis() - startTime));
      }
      req.getRequestDispatcher("/index.jsp").forward(req, resp);

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
}
