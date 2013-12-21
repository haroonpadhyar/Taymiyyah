package com.maktashaf.taymiyyah.repository.jdbc;

import java.util.Set;

import com.maktashaf.taymiyyah.common.LocaleEnum;

/**
 * @author: Haroon
 */
public interface QueryBuilder {
  String getFindAllQuery(LocaleEnum localeEnum);
  String getFillTranslationQuery(Set<Integer> keys, LocaleEnum localeEnum, boolean isOriginal);
  public String getAccumIdQuery(LocaleEnum localeEnum);
  public String getAyahIdQuery(LocaleEnum localeEnum);
}
