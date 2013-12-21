package com.maktashaf.taymiyyah.repository.lucene;

import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.repository.lucene.search.SearcherRegistry;
import com.maktashaf.taymiyyah.vo.SearchResult;
import org.apache.log4j.Logger;

/**
 * @author: Haroon
 */
public class QuranLuceneRepoImpl implements QuranLuceneRepo{
  private static Logger logger = Logger.getLogger(QuranLuceneRepoImpl.class);

  public SearchResult searchAyah(SearchParam searchParam){
    SearchResult searchResult = SearcherRegistry.getSearcher(searchParam.isOriginal()).search(searchParam);
    return searchResult;
  }
}
