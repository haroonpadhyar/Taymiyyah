package com.maktashaf.taymiyyah.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.QuranField;
import com.maktashaf.taymiyyah.model.Quran;
import com.maktashaf.taymiyyah.repository.jdbc.factory.ConnectionFactory;
import org.apache.log4j.Logger;

/**
 * @author: Haroon
 */
public class QuranJDBCRepoImpl implements QuranJDBCRepo{
  private static Logger logger = Logger.getLogger(QuranJDBCRepoImpl.class);
  private QueryBuilder queryBuilder = new QueryBuilderImpl();

  @Override
  public List<Quran> findAll(LocaleEnum localeEnum){
    List<Quran> quranList = new ArrayList<Quran>();
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    try {
      String query = queryBuilder.getFindAllQuery(localeEnum);
      if(logger.isDebugEnabled()){
        logger.debug("getFindAllQuery: "+query);
      }
      connection = ConnectionFactory.getInstance().getConnection();
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next()){
        Quran quran = new Quran();
        quran.setAccmId(rs.getInt(QuranField.accumId.value()));
        quran.setAyahId(rs.getInt(QuranField.ayahId.value()));
        quran.setSurahId(rs.getInt(QuranField.surahId.value()));
        quran.setJuzId(rs.getInt(QuranField.juzId.value()));
        quran.setRevelationOrder(rs.getInt(QuranField.revelationOrder.value()));
        quran.setAyahText(rs.getString(QuranField.ayahText.value()));
        quran.setOrigin(rs.getString(QuranField.origin.value()));
        quran.setType(rs.getString(QuranField.type.value()));
        quran.setContext(rs.getString(QuranField.context.value()));
        quran.setSurahName(rs.getString(QuranField.surahName.value()));
        quran.setJuzName(rs.getString(QuranField.juzName.value()));

        quranList.add(quran);
      }

    } catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }finally {
      try {
        if(rs != null)
          rs.close();
        if(statement != null)
          statement.close();
        if(connection != null)
          connection.close();
      } catch(Exception ee){
        ee.printStackTrace();
      }
    }

    return quranList;
  }

  @Override
  public void fillTranslation(List<Quran> quranList, LocaleEnum localeEnum, boolean isOriginal){
    if(quranList == null || quranList.size() <=0)
      return;

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    try {

      ImmutableMap<Integer,Quran> quranMap = Maps.uniqueIndex(
          quranList, new Function<Quran, Integer>() {
        @Override
        public Integer apply(Quran quran) {
          return quran.getAccmId();
        }
      }
      );

      String query = queryBuilder.getFillTranslationQuery(quranMap.keySet(), localeEnum, isOriginal);
      if(logger.isDebugEnabled()){
        logger.debug("getFillTranslationQuery: "+query);
      }
      connection = ConnectionFactory.getInstance().getConnection();
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next()){
        if(isOriginal)   {
          if(localeEnum != LocaleEnum.Ar)//it is temporary check since no arabic translator
          quranMap.get(rs.getInt(QuranField.accumId.value())).setAyahTranslationText(rs.getString(QuranField.ayahText.value()));
        }
        else{
          if(localeEnum != LocaleEnum.Ar)
            quranMap.get(rs.getInt(QuranField.accumId.value())).setAyahText(rs.getString(QuranField.ayahText.value()));
          else {
            // This is temporary until we don't have any arabic translation. Just return the
            // Quran text. since it was searched on Quran by luceneRepo. for locale 'ar'
            Quran quran = quranMap.get(rs.getInt(QuranField.accumId.value()));
            quran.setAyahText(quran.getAyahTranslationText());
            quran.setAyahTranslationText(null);
          }
          quranMap.get(rs.getInt(QuranField.accumId.value())).setSurahName(rs.getString(QuranField.surahName.value()));
          quranMap.get(rs.getInt(QuranField.accumId.value())).setJuzName(rs.getString(QuranField.juzName.value()));
        }
      }

    } catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }finally {
      try {
        if(rs != null)
          rs.close();
        if(statement != null)
          statement.close();
        if(connection != null)
          connection.close();
      } catch(Exception ee){
        ee.printStackTrace();
      }
    }
  }

  @Override
  public Quran findByAccumId(int accumId, LocaleEnum localeEnum){
    Quran quran = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      connection = ConnectionFactory.getInstance().getConnection();
      quran = byAccumId(accumId, LocaleEnum.Ar, connection);
      if(quran != null)
        quran.setAyahTranslationText(byAccumId(quran.getAccmId(), localeEnum, connection).getAyahText());
    } catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }finally {
      try {
        if(rs != null)
          rs.close();
        if(statement != null)
          statement.close();
        if(connection != null)
          connection.close();
      } catch(Exception ee){
        ee.printStackTrace();
      }
    }
    return quran;
  }

  private Quran byAccumId(int accumId, LocaleEnum localeEnum, Connection connection){
    Quran quran = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = queryBuilder.getAccumIdQuery(localeEnum);
      if(logger.isDebugEnabled()){
        logger.debug("getAccumIdQuery: "+query);
        logger.debug(", accumId: "+accumId);
      }
      statement = connection.prepareStatement(query);
      statement.setInt(1, accumId);
      rs = statement.executeQuery();
      while (rs.next()){
        quran = new Quran();
        quran.setAccmId(rs.getInt(QuranField.accumId.value()));
        quran.setAyahId(rs.getInt(QuranField.ayahId.value()));
        quran.setSurahId(rs.getInt(QuranField.surahId.value()));
        quran.setJuzId(rs.getInt(QuranField.juzId.value()));
        quran.setRevelationOrder(rs.getInt(QuranField.revelationOrder.value()));
        quran.setAyahText(rs.getString(QuranField.ayahText.value()));
        quran.setOrigin(rs.getString(QuranField.origin.value()));
        quran.setType(rs.getString(QuranField.type.value()));
        quran.setContext(rs.getString(QuranField.context.value()));
        quran.setSurahName(rs.getString(QuranField.surahName.value()));
        quran.setJuzName(rs.getString(QuranField.juzName.value()));
      }

    } catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }finally {
      try {
        if(rs != null)
          rs.close();
        if(statement != null)
          statement.close();
      } catch(Exception ee){
        ee.printStackTrace();
      }
    }

    return quran;
  }

  @Override
  public Quran findByAyahId(int surahId, int ayahId, LocaleEnum localeEnum){
    Quran quran = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = queryBuilder.getAyahIdQuery(LocaleEnum.Ar);
      if(logger.isDebugEnabled()){
        logger.debug("getAyahIdQuery: "+query);
        logger.debug(", surahId: "+surahId);
        logger.debug(", ayahId: "+ayahId);
      }
      connection = ConnectionFactory.getInstance().getConnection();
      statement = connection.prepareStatement(query);
      statement.setInt(1, surahId);
      statement.setInt(2, ayahId);
      rs = statement.executeQuery();
      while (rs.next()){
        quran = new Quran();
        quran.setAccmId(rs.getInt(QuranField.accumId.value()));
        quran.setAyahId(rs.getInt(QuranField.ayahId.value()));
        quran.setSurahId(rs.getInt(QuranField.surahId.value()));
        quran.setJuzId(rs.getInt(QuranField.juzId.value()));
        quran.setRevelationOrder(rs.getInt(QuranField.revelationOrder.value()));
        quran.setAyahText(rs.getString(QuranField.ayahText.value()));
        quran.setOrigin(rs.getString(QuranField.origin.value()));
        quran.setType(rs.getString(QuranField.type.value()));
        quran.setContext(rs.getString(QuranField.context.value()));
        quran.setSurahName(rs.getString(QuranField.surahName.value()));
        quran.setJuzName(rs.getString(QuranField.juzName.value()));

      }

      if(quran != null){
        Quran byAccumId = this.byAccumId(quran.getAccmId(), localeEnum, connection);
        quran.setAyahTranslationText(byAccumId.getAyahText());
      }

    } catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new RuntimeException(e);
    }finally {
      try {
        if(rs != null)
          rs.close();
        if(statement != null)
          statement.close();
        if(connection != null)
          connection.close();
      } catch(Exception ee){
        ee.printStackTrace();
      }
    }

    return quran;
  }
}
