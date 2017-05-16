package com.acme.warehouse.messaging;

import com.acme.commons.entities.AcmeMessage;
import com.acme.commons.entities.repository.AcmeMessageRepository;
import com.acme.warehouse.messaging.messagehandler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * MessageListener for the Warehouse application
 *
 * @author ajorritsma
 */
@Component
// TODO this specific messagelistener is obsolete
public class WarehouseMessageListener {
	/**
	 * Repository to store messages
	 */
	@Autowired
	private AcmeMessageRepository acmeMessageRepository;
	/**
	 * Map with messagehandlers
	 */
	@Resource(name = "messageHandlersMap")
	private Map<String, MessageHandler> messageHandlerMap;

	/**
	 * Store the AcmeMessage. Get and call the MessageHandler for the configured JMStype.
	 * @param message The consumed JMS message
	 */
	@JmsListener(destination = "ACME.WAREHOUSE")
	public void sendInvoice(String message, @Headers MessageHeaders messageHeaders) {
		AcmeMessage acmeMessage = new AcmeMessage();
		acmeMessage.setType(messageHeaders.get("jms_type", String.class));
		acmeMessage.setMessageHeaderId(messageHeaders.getId().toString());
		acmeMessage.setPayload(message);

		acmeMessageRepository.save(acmeMessage);

		MessageHandler messageHandler = messageHandlerMap.get(acmeMessage.getType());
		messageHandler.handleMessage(acmeMessage.getPayload());

	}
}
