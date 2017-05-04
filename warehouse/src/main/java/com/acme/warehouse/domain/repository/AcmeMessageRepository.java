package com.acme.warehouse.domain.repository;

import com.acme.warehouse.domain.AcmeMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository to store messages
 *
 * @author ajorritsma
 */
public class AcmeMessageRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AcmeMessage acmeMessage) {
		if (acmeMessage.getId() == null) {
			entityManager.persist(acmeMessage);
		} else {
			entityManager.merge(acmeMessage);
		}
	}
}
