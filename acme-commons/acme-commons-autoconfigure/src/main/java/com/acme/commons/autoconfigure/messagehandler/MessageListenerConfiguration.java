package com.acme.commons.autoconfigure.messagehandler;

import com.acme.commons.entities.AcmeMessage;
import com.acme.commons.entities.repository.AcmeMessageRepository;
import com.acme.commons.messaging.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Configuration of the default MessageListener
 *
 * @author ajorritsma
 */

@Configuration
public class MessageListenerConfiguration {
	@Autowired(required = false)
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private AcmeMessageRepository acmeMessageRepository;

	@Resource(name = "messageHandlersMap")
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private Map<String, MessageHandler> messageHandlersMap;

	@JmsListener(destination = "${acme.commons.jms.requestQueue}")
	public void onMessage(String message, @Headers MessageHeaders messageHeaders) {
		AcmeMessage acmeMessage = new AcmeMessage();
		acmeMessage.setType(messageHeaders.get("jms_type", String.class));
		acmeMessage.setMessageHeaderId(messageHeaders.getId().toString());
		acmeMessage.setPayload(message);

		acmeMessageRepository.save(acmeMessage);

		// TODO
		// Get the MessageHandler from the MessageHandler map to handle the message.
		// The key of the MessageHandler map is the JMStype messageheader property.
	}
}
