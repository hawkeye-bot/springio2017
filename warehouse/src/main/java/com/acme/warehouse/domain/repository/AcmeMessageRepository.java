package com.acme.warehouse.domain.repository;

import com.acme.warehouse.domain.AcmeMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository to persist the {@link com.acme.warehouse.domain.AcmeMessage}
 *
 * @author ajorritsma
 */
public class AcmeMessageRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(AcmeMessage acmeMessage) {
		if (acmeMessage.getId() == null) {
			entityManager.persist(acmeMessage);
		} else {
			entityManager.merge(acmeMessage);
		}
	}
}
