package com.acme.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity to store the JMS message
 *
 * @author ajorritsma
 */
@Entity
@Table(name = "ACMEMESSAGES")
public class AcmeMessage {
	/**
	 * The id of the message
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * The payload of the message
	 */
	@Column
	private String payload;
	/**
	 * The JMS type
	 */
	@Column(name = "MESSAGE_TYPE")
	private String type;
	/**
	 * The JMS messageId
	 */
	@Column(name = "MESSAGE_ID")
	private String messageId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
