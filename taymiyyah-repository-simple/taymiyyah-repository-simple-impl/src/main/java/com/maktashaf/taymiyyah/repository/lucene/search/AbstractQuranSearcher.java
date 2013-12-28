package com.maktashaf.taymiyyah.repository.lucene.search;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.maktashaf.taymiyyah.analysis.ur.UrduAnalyzer;
import com.maktashaf.taymiyyah.common.QuranField;
import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.model.Quran;
import com.maktashaf.taymiyyah.vo.SearchResult;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.NullFragmenter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author: Haroon
 */
public abstract class AbstractQuranSearcher  implements QuranSearcher{
  private static Logger logger = Logger.getLogger(AbstractQuranSearcher.class);
  public static int MAX_HITS = 500; // ideally 20 result per page so total 25 pages.

  @Override
  public SearchResult search(SearchParam searchParam){
    SearchResult searchResult = SearchResult.builder().build();
    Directory dir = null;
    IndexReader reader = null;
    IndexSearcher searcher = null;
    try {
      List<Quran> quranList = new ArrayList<Quran>();
      int totalHits = 0;
      int totalPages = 0;

      dir = FSDirectory.open(
          new File(resolveIndexPath(searchParam))
      );

      Analyzer analyzer = chooseAnalyzer(searchParam);
      if(analyzer == null)
        throw new RuntimeException("No Analyzer found for: "
            +searchParam.getLocaleEnum().value() + ": "+searchParam.getTranslator());

      reader = IndexReader.open(dir);
      searcher = new IndexSearcher(reader);

      Version version = Version.LUCENE_31;
      if(analyzer instanceof UrduAnalyzer)
        version = Version.LUCENE_30;

      QueryParser parser = new QueryParser(version, QuranField.ayahText.value(), analyzer);
      Query query = parser.parse(searchParam.getTerm());

      TopScoreDocCollector collector = TopScoreDocCollector.create(MAX_HITS, true);
      searcher.search(query, collector);

      int pageNo = Math.max(1, searchParam.getPageNo()); // if PageNo is zero then get 1.
      int pageSize = Math.max(1, searchParam.getPageSize());// if PageSize is zero then get 1.
      TopDocs topDocs = collector.topDocs((pageNo - 1)*pageSize, pageSize);

      totalHits = topDocs.totalHits;
      totalPages = (int)Math.ceil((double) Math.min(MAX_HITS, totalHits)/pageSize);
      ScoreDoc[] scoreDocs = topDocs.scoreDocs;
      for (ScoreDoc scoreDoc : scoreDocs) {
        Document doc = searcher.doc(scoreDoc.doc);
        Quran quran = new Quran();
        quran.setAccmId(Integer.valueOf(doc.get(QuranField.accumId.value())).intValue());
        quran.setAyahId(Integer.valueOf(doc.get(QuranField.ayahId.value())).intValue());
        quran.setSurahId(Integer.valueOf(doc.get(QuranField.surahId.value())).intValue());
        quran.setJuzId(Integer.valueOf(doc.get(QuranField.juzId.value())).intValue());
        quran.setSurahName(doc.get(QuranField.surahName.value()));
        quran.setJuzName(doc.get(QuranField.juzName.value()));
        setSearchedTextInField(quran, doc.get(QuranField.ayahText.value()));

        highlight(quran, query, analyzer);
        quranList.add(quran);
      }

      //prepare result
      searchResult = SearchResult.builder()
          .withTotalHits(totalHits)
          .withTotalPages(totalPages)
          .withQuranList(quranList)
          .build();
    }
    catch(Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    } finally {
      try {
        if(dir != null)
          dir.close();
        if(reader != null)
          reader.close();
        if(searcher != null)
          searcher.close();
      } catch(Exception e){
        e.printStackTrace();
        logger.error(e.getMessage());
        throw new RuntimeException(e);
      }
    }
    return searchResult;
  }

  private void highlight(Quran quran, Query query, Analyzer analyzer){
    try {
      String text = getSearchedTextFromField(quran);
      StringReader stringReader = new StringReader(text);
      TokenStream tokenStream = analyzer.tokenStream(QuranField.ayahText.value(),stringReader);
      SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span class=\"highlight\">","</span>");
      Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer( query));
      highlighter.setTextFragmenter(new NullFragmenter());
      String bestFragment = highlighter.getBestFragment(tokenStream, text);

      setSearchedTextInField(quran, bestFragment);
    }
    catch(Exception e) {
      e.printStackTrace();
      logger.debug(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  protected abstract String resolveIndexPath(SearchParam searchParam);
  protected abstract String getSearchedTextFromField(Quran quran);
  protected abstract void setSearchedTextInField(Quran quran, String text);
  protected abstract Analyzer chooseAnalyzer(SearchParam searchParam);
}
