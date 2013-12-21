package com.maktashaf.taymiyyah.repository.jdbc;

import java.util.Set;

import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.QuranField;
import com.maktashaf.taymiyyah.common.QuranText;

/**
 * @author: Haroon
 */
public class QueryBuilderImpl implements QueryBuilder {

  @Override
  public String getFindAllQuery(LocaleEnum localeEnum){
    StringBuilder query = new StringBuilder();
    query.append("SELECT * FROM ");
    if(localeEnum == LocaleEnum.Ar)
      query.append(QuranText.Quran.value());
    else if(localeEnum == LocaleEnum.Ur)
      query.append(QuranText.QuranUrMaududi.value());
    else if(localeEnum == LocaleEnum.En)
      query.append(QuranText.QuranEnYousufAli.value());
    else
      query.append(QuranText.Quran.value());
    return query.toString();
  }

  @Override
  public String getFillTranslationQuery(Set<Integer> keys, LocaleEnum localeEnum, boolean isOriginal){
    StringBuilder query = new StringBuilder();
    query.append("SELECT ");
    query.append(QuranField.accumId.value());
    query.append(" , ");
    query.append(QuranField.ayahText.value());
    query.append(" , ");
    query.append(QuranField.surahName.value());
    query.append(" , ");
    query.append(QuranField.juzName.value());
    query.append(" FROM ");
    if(isOriginal){
      if(localeEnum == LocaleEnum.Ur)
        query.append(QuranText.QuranUrMaududi.value());
      else if(localeEnum == LocaleEnum.En)
        query.append(QuranText.QuranEnYousufAli.value());
      else if(localeEnum == LocaleEnum.Ar)
        query.append(QuranText.Quran.value());
    } else
      query.append(QuranText.Quran.value());

    query.append(" Where ");
    query.append(QuranField.accumId.value());
    query.append(" IN ( ");
    int i = 0;
    for (Integer integer : keys) {
      if(i == 0)
        query.append(integer.intValue());
      else
        query.append(" , "+integer.intValue());

      i +=1;
    }
    query.append(" ) " );

    return query.toString();
  }

  @Override
  public String getAccumIdQuery(LocaleEnum localeEnum){
    StringBuilder query = new StringBuilder();
    query.append("SELECT * FROM ");
    if(localeEnum == LocaleEnum.Ar)
      query.append(QuranText.Quran.value());
    else if(localeEnum == LocaleEnum.Ur)
      query.append(QuranText.QuranUrMaududi.value());
    else if(localeEnum == LocaleEnum.En)
      query.append(QuranText.QuranEnYousufAli.value());
    else
      query.append(QuranText.Quran.value());

    query.append(" Where ");
    query.append(QuranField.accumId.value());
    query.append(" = ?");

    return query.toString();
  }

  @Override
  public String getAyahIdQuery(LocaleEnum localeEnum){
    StringBuilder query = new StringBuilder();
    query.append("SELECT * FROM ");
    if(localeEnum == LocaleEnum.Ar)
      query.append(QuranText.Quran.value());
    else if(localeEnum == LocaleEnum.Ur)
      query.append(QuranText.QuranUrMaududi.value());
    else if(localeEnum == LocaleEnum.En)
      query.append(QuranText.QuranEnYousufAli.value());
    else
      query.append(QuranText.Quran.value());

    query.append(" Where ");
    query.append(QuranField.surahId.value());
    query.append(" = ? and ");
    query.append(QuranField.ayahId.value());
    query.append(" = ? ");

    return query.toString();
  }

}
