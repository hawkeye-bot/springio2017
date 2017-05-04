package com.acme.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for shippable orders.
 *
 * @author ajorritsma
 */
@Entity
public class Order {
	/**
	 * The id of the order
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * The name of the customer who placed the order
	 */
	@Column
	private String customerName;
	/**
	 * There is a orderline for every ordered article
	 */
	@OneToMany
	@JoinColumn(name="ORDER_ID")
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}
