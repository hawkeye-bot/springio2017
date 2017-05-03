package com.acme.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity to store the JMS message
 *
 * @author ajorritsma
 */
@Entity
public class AcmeMessage {
	@Id
	private Long id;

	@Column
	private String payload;

	@Column
	private String messageId;
}
