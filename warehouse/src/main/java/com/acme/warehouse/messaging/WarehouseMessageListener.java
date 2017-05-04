package com.acme.warehouse.messaging;

import com.acme.warehouse.domain.AcmeMessage;
import com.acme.warehouse.domain.repository.AcmeMessageRepository;
import com.acme.warehouse.messaging.dto.transformer.AcmeMessageTransformer;
import com.acme.warehouse.messaging.messagehandler.MessageHandler;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * MessageListener for the Warehouse application
 *
 * @author ajorritsma
 */
public class WarehouseMessageListener implements MessageListener {
	/**
	 * Transformer to translate a JMS message into a AcmeMessage
	 */
	private AcmeMessageTransformer acmeMessageTransformer;
	/**
	 * Repository to store messages
	 */
	private AcmeMessageRepository acmeMessageRepository;
	/**
	 * Map with messagehandlers
	 */
	private Map<String, MessageHandler> messageHandlerMap;

	public WarehouseMessageListener(AcmeMessageTransformer acmeMessageTransformer, AcmeMessageRepository acmeMessageRepository,
			Map<String, MessageHandler> messageHandlerMap) {
		this.acmeMessageTransformer = acmeMessageTransformer;
		this.acmeMessageRepository = acmeMessageRepository;
		this.messageHandlerMap = messageHandlerMap;
	}

	/**
	 * Store the AcmeMessage. Get and call the MessageHandler for the configured JMStype.
	 * @param message The consumed JMS message
	 */
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			AcmeMessage acmeMessage = acmeMessageTransformer.transform((TextMessage) message);

			acmeMessageRepository.save(acmeMessage);

			MessageHandler messageHandler = messageHandlerMap.get(acmeMessage.getType());
			messageHandler.handleMessage(acmeMessage.getPayload());
		}
	}
}
