package com.acme.commons.autoconfigure.messagehandler;

import com.acme.commons.messaging.MessageHandler;
import com.acme.commons.messaging.MessageHandlerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for putting the handlers on the JMS-messagelistener
 * 
 * @author erhoeckx
 */
@Configuration
@ConditionalOnClass(MessageHandlerBean.class)
public class MessageHandlerAnnotationProcessor implements BeanPostProcessor {
	/**
	 * Logger to log events.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerAnnotationProcessor.class);

	private Map<MessageHandlerBean, MessageHandler> annotatedMessageHandlers = new HashMap<>();

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName)
			throws BeansException {
		return bean;
	}

	@Bean("messageHandlersMap")
	public Map<String, MessageHandler> defaultMessageHandlerMap() {
		Map<String, MessageHandler> result = new HashMap<>();
		for (Map.Entry<MessageHandlerBean, MessageHandler> entry : annotatedMessageHandlers.entrySet()) {
			addMessageHandler(entry.getValue(), entry.getKey(), result);
		}
		return result;
	}

	/**
	 * Adds a message handler with the correct JMSType. If the required map does not exist, it will be created.
	 * @param messageHandler The handler that needs to be added to messagehandler for a particular action.
	 * @param metadata The metadata that specifies the JMSType for which the handler applies.
	 * @param messageHandlerMap The map where the handlers should be added.
	 */
	private void addMessageHandler(final MessageHandler messageHandler, final MessageHandlerBean metadata,
			final Map<String, MessageHandler> messageHandlerMap) {
		Assert.notNull(metadata.type(), "Action is null");
		Assert.notNull(messageHandlerMap, "messageHandlerMap is null");
		Assert.notNull(messageHandler, "messageHandler is null");

		messageHandlerMap.put(metadata.type(), messageHandler);
		LOGGER.debug("Handler added for JMSType {}", metadata.type());
	}

	/**
	 * Find and add all {@link MessageHandler}.
	 * @param bean     The bean that can provide a {@link MessageHandler}.
	 * @param beanName The name of the bean.
	 * @return The bean will be returned.
	 */
	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName)
			throws BeansException {

		Class beanClass = bean.getClass();
		if (isMessageHandler(beanClass) && beanClass.isAnnotationPresent(MessageHandlerBean.class)) {
			LOGGER.info("Class with annotation for MessageHandlerBean detected on bean {}", beanClass.getName());

			// TODO add the MessageHandler with the metadata to the annotatedMessageHandlers map
		}

		return bean;
	}

	/**
	 * Is the class a {@link MessageHandler}?
	 *
	 * @param beanClass Class to determine if it is a {@link MessageHandler}.
	 * @return Boolean that indicates whether the indicated class is a MessageHandler.
	 */
	private boolean isMessageHandler(Class beanClass) {
		return MessageHandler.class.isAssignableFrom(beanClass);
	}
}