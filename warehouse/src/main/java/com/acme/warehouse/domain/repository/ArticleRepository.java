package com.acme.warehouse.domain.repository;

import com.acme.warehouse.domain.Article;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for articles
 *
 * @author ajorritsma
 */
@Component
public class ArticleRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Article article) {
		if (article.getId() == null) {
			entityManager.persist(article);
		} else {
			entityManager.merge(article);
		}
	}

	public List<Article> findAll() {
		TypedQuery<Article> query = entityManager.createQuery("SELECT x FROM Article x", Article.class);

		return query.getResultList();
	}

	public Article findById(Long id) {
		return entityManager.find(Article.class, id);
	}
}
