package com.acme.warehouse.domain.repository;

import com.acme.warehouse.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to store articles
 *
 * @author ajorritsma
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
