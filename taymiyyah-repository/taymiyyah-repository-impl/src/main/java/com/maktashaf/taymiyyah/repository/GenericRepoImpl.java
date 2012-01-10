/**
 *
 */
package com.maktashaf.taymiyyah.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import org.hibernate.classic.Session;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * @author Yazik K. P.
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
    List<T> list = fullTextQuery.getResultList();

    return list;
  }
}
