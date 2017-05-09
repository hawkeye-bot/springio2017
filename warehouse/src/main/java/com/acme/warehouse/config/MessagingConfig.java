package com.acme.warehouse.config;

import com.acme.warehouse.messaging.messagehandler.MessageHandler;
import com.acme.warehouse.messaging.messagehandler.ShipOrderMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Messaging configuration
 *
 * @author ajorritsma
 */
@Configuration
@EnableJms
public class MessagingConfig {
	/**
	 * The MessageHandler to handle a ShipOrderVO message
	 */
	@Autowired
	private ShipOrderMessageHandler shipOrderMessageHandler;

	/**
	 * @return Bean to configure all known messagehandlers
	 */
	@Bean
	public Map<String, MessageHandler> messageHandlersMap() {
		Map<String, MessageHandler> result = new HashMap<>();
		result.put("ShipOrder", shipOrderMessageHandler);
		return result;
	}
}
