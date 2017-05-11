package com.acme.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Article domain class
 *
 * @author ajorritsma
 */
@Entity
@Table(name = "ARTICLES")
public class Article {
	/**
	 * The id of the article
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * The name of the article
	 */
	@Column
	private String name;
	/**
	 * The price of the article
	 */
	@Column
	private BigDecimal price;
	/**
	 * The current stock
	 */
	@Column
	private Integer stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
