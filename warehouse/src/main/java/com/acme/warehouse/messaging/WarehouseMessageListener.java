package com.acme.warehouse.messaging;

import com.acme.warehouse.domain.AcmeMessage;
import com.acme.warehouse.domain.repository.AcmeMessageRepository;
import com.acme.warehouse.messaging.messagehandler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * MessageListener for the Warehouse application
 *
 * @author ajorritsma
 */
@Component
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
	public void sendInvoice(String message, @Header("JMSMessageID") String messageId, @Header("JMSType") String jmsType) {
		AcmeMessage acmeMessage = new AcmeMessage();
		acmeMessage.setType(jmsType);
		acmeMessage.setMessageId(messageId);
		acmeMessage.setPayload(message);

		acmeMessageRepository.save(acmeMessage);

		MessageHandler messageHandler = messageHandlerMap.get(acmeMessage.getType());
		messageHandler.handleMessage(acmeMessage.getPayload());

	}
}
