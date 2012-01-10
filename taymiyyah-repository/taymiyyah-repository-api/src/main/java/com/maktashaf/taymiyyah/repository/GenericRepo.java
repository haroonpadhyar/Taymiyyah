package com.maktashaf.taymiyyah.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Haroon Anwar Padhyar.
 */
public interface GenericRepo<T, ID extends Serializable> {
  //~ Methods ----------------------------------------------------------------

  /**
   * Get the Class of the entity.
   *
   * @return the class
   */
  Class<T> getEntityClass();

  /**
   * Find an entity by its primary key
   *
   * @param id the primary key
   *
   * @return the entity
   */
  public T findById(final ID id);

  /**
   * Load all entities.
   *
   * @return the list of entities
   */
  List<T> findAll();



  /**
   * Find using a named query.
   *
   * @param queryName the name of the query
   * @param params the query parameters
   *
   * @return the list of entities
   */
  List<T> findByNamedQueryAndNamedParams(
      final String queryName,
      final Map<String, ? extends Object> params
  );

  /**
   * save an entity. This can be INSERT in the database.
   *
   * @param entity the entity to save
   *
   * @return the saved entity
   */
  T save(final T entity);

  /**
   * delete an entity from the database.
   *
   * @param entity the entity to delete
   */
  void delete(final T entity);

  /**
   * delete an entity from the database.
   *
   * @param entity the entity to delete
   * @param id
   */
  void delete(final T entity, final Long id);

  /**
   * update an entity. This can be Update in the database.
   *
   * @param entity the entity to update
   *
   * @return the updated entity
   */
  T saveOrUpdate(final T entity);

  /**
   * Full text search.
   * @param token
   * @return
   */
  public List<T> fullTextSearchByAyah(String token);
}