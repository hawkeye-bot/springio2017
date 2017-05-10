package com.acme.warehouse.domain;

import javax.persistence.*;

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
	@JoinColumn(name = "ARTICLE_ID")
	private Article article;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")

	private Order order;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
