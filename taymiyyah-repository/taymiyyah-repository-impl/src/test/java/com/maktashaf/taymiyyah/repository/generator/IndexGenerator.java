package com.maktashaf.taymiyyah.repository.generator;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-Test.xml"})
public class IndexGenerator {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

	@Test
	public void createIndexes() {
		final EntityManager entityManager =
			entityManagerFactory.createEntityManager();
		final FullTextEntityManager fullTextEntityManager =
			Search.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}

	}

}
