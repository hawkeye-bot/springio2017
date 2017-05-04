package com.acme.warehouse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Entity to store orderlines.
 *
 * @author ajorritsma
 */
@Entity
public class OrderLine {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(referencedColumnName = "ARTICLE_ID")
	private Article article;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
