package com.acme.warehouse.domain.repository;

import com.acme.warehouse.domain.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository for orders.
 *
 * @author ajorritsma
 */
public class OrderRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Order order) {
		if (order.getId() == null) {
			entityManager.persist(order);
		} else {
			entityManager.merge(order);
		}
	}
}
