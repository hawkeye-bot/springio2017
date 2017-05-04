package com.acme.warehouse.messaging;

import com.acme.warehouse.domain.AcmeMessage;
import com.acme.warehouse.domain.repository.AcmeMessageRepository;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * MessageListener for the Warehouse application
 *
 * @author ajorritsma
 */
public class WarehouseMessageListener implements MessageListener {
	private AcmeMessageTransformer acmeMessageTransformer;
	private AcmeMessageRepository acmeMessageRepository;

	public WarehouseMessageListener(AcmeMessageTransformer acmeMessageTransformer, AcmeMessageRepository acmeMessageRepository) {
		this.acmeMessageTransformer = acmeMessageTransformer;
		this.acmeMessageRepository = acmeMessageRepository;
	}

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			AcmeMessage acmeMessage = acmeMessageTransformer.transform((TextMessage) message);

			acmeMessageRepository.save(acmeMessage);
		}
	}
}
