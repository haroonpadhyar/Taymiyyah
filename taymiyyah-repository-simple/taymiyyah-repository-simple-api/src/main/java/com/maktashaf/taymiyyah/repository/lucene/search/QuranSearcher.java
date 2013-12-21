package com.maktashaf.taymiyyah.repository.lucene.search;

import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.vo.SearchResult;

/**
 * @author: Haroon
 */
public interface QuranSearcher {
  SearchResult search(SearchParam searchParam);
}
