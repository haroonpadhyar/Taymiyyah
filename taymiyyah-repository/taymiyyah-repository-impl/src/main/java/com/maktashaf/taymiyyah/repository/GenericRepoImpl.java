/**
 *
 */
package com.maktashaf.taymiyyah.repository;

import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.maktashaf.taymiyyah.domain.Quran;
import com.maktashaf.taymiyyah.domain.QuranEnYousufali;
import com.maktashaf.taymiyyah.domain.QuranUrMaududi;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author Haroon Anwar Padhyar.
 */
public abstract class GenericRepoImpl<T, ID extends Serializable> implements GenericRepo<T, ID> {

  private static Logger log = Logger.getLogger(GenericRepoImpl.class);

  @PersistenceContext
  protected EntityManager entityManager;

  private final Class<T> persistentClass;

  @SuppressWarnings("unchecked")
  public GenericRepoImpl() {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];
  }

  protected GenericRepoImpl(final Class<T> persistentClass) {
    super();
    this.persistentClass = persistentClass;
  }


  @Override
  public List<T> findAll() {
    throw new UnsupportedOperationException();
  }


  @Override
  public T findById(final ID id) {
    return entityManager.find(getEntityClass(), id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> findByNamedQueryAndNamedParams(final String name, final Map<String, ? extends Object> params) {
    Query query = entityManager.createNamedQuery(name);

    for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
      query.setParameter(param.getKey(), param.getValue());
    }

    final List<T> result = (List<T>) query.getResultList();
    return result;
  }

  @Override
  public Class<T> getEntityClass() {
    return persistentClass;
  }

  @Override
  public void delete(final T entity, final Long id) {
    T entityToBeRemoved = (T) entityManager.find(entity.getClass(), id);
    entityManager.remove(entityToBeRemoved);
  }

  @Override
  public void delete(T entity) {
    entityManager.remove(entity);
  }

  @Override
  public T save(T entity) {
    entityManager.persist(entity);
    return entity;
  }

  public T saveOrUpdate(T entity) {
    final T savedEntity = entityManager.merge(entity);
    return savedEntity;
  }

  @Override
  public List<T> fullTextSearchByAyah(String token){

    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
    QueryBuilder queryBuilder =
        fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(persistentClass).get();

    org.apache.lucene.search.Query query = queryBuilder.keyword().onField("ayahText").matching(token).createQuery();
    FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, persistentClass);
    Sort sort = new Sort(new SortField("accmId", SortField.LONG)) ;
    fullTextQuery.setSort(sort);
    List<T> list = fullTextQuery.getResultList();
//    fullTextQuery.setFirstResult()
//    fullTextQuery.setMaxResults()

    Analyzer analyzer = null;
    try{
      for (T t : list) {
        if(t instanceof Quran){
          if(analyzer == null)
            analyzer = new ArabicAnalyzer(Version.LUCENE_31);
          Quran quran = (Quran)t;
          TokenStream tokenStream = analyzer.tokenStream("ayahText",new StringReader(quran.getAyahText()));
          SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<B/>","<B>");
          Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer( query));
          String[] bestFragment = highlighter.getBestFragments(tokenStream, quran.getAyahText(), 100);
          quran.setAyahText(String.valueOf(bestFragment[0]));
        } else if(t instanceof QuranUrMaududi){
          if(analyzer == null)
            analyzer = fullTextEntityManager.getSearchFactory().getAnalyzer("uranalyzer");
          QuranUrMaududi quran = (QuranUrMaududi)t;
          TokenStream tokenStream = analyzer.tokenStream("ayahText",new StringReader(quran.getAyahText()));
          SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<B/>","<B>");
          Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer( query));
          String[] bestFragment = highlighter.getBestFragments(tokenStream, quran.getAyahText(), 100);
          quran.setAyahText(String.valueOf(bestFragment[0]));
        } else if(t instanceof QuranEnYousufali){
          if(analyzer == null)
            analyzer = fullTextEntityManager.getSearchFactory().getAnalyzer("metaphoneAnalyzer");
          QuranEnYousufali quran = (QuranEnYousufali)t;
          TokenStream tokenStream = analyzer.tokenStream("ayahText",new StringReader(quran.getAyahText()));
          SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter();
          Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer( query));
          String[] bestFragment = highlighter.getBestFragments(tokenStream, quran.getAyahText(), 100);
          quran.setAyahText(String.valueOf(bestFragment[0]));
        }
      }
    }catch(Exception e){
      e.printStackTrace();
      new RuntimeException(e.getMessage());
    }


    return list;
  }
}
