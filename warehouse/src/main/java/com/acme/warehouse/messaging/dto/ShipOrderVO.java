package com.acme.warehouse.messaging.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object for shippable orders.
 *
 * @author ajorritsma
 */
public class ShipOrderVO {
	/**
	 * The name of the customer
	 */
	private String customerName;
	/**
	 * A list of ordered articles
	 */
	private List<Long> articleIds = new ArrayList<Long>();

	/**
	 * @return The name of the customer
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Change the name of the customer.
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return A list of ordered articles
	 */
	public List<Long> getArticleIds() {
		return articleIds;
	}

	/**
	 * Set the list of ordered articles
	 * @param articleIds
	 */
	public void setArticleIds(List<Long> articleIds) {
		this.articleIds = articleIds;
	}
}
