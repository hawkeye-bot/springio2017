package com.acme.warehouse.messaging.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object for shippable orders.
 *
 * @author ajorritsma
 */
public class ShipOrderVO {
	private String customerName;
	private List<Long> articleIds = new ArrayList<Long>();

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Long> getArticleIds() {
		return articleIds;
	}

	public void setArticleIds(List<Long> articleIds) {
		this.articleIds = articleIds;
	}
}
