package com.maktashaf.taymiyyah.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author Haroon Anwar padhyar.
 */
@Repository
public class IndexGeneratorRepoImpl implements IndexGeneratorRepo{

  @Autowired
  private EntityManagerFactory entityManagerFactory;

	public void createIndexes() {
		final EntityManager entityManager =
			entityManagerFactory.createEntityManager();
		final FullTextEntityManager fullTextEntityManager =
			Search.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		} finally {
      entityManager.close();
    }

	}
}
