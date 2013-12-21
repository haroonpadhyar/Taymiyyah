package com.maktashaf.taymiyyah.repository.lucene;

import com.maktashaf.taymiyyah.common.vo.SearchParam;
import com.maktashaf.taymiyyah.vo.SearchResult;

/**
 * @author Haroon Anwar padhyar.
 */
public interface QuranLuceneRepo {
  SearchResult searchAyah(SearchParam searchParam);
}
