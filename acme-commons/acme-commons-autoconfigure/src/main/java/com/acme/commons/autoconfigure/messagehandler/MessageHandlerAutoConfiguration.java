package com.acme.commons.autoconfigure.messagehandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.jms.ConnectionFactory;

/**
 * Configuration for putting the handlers on the JMS messagelistener
 * 
 * @author erhoeckx
 */
@Configuration
@ConditionalOnClass(ConnectionFactory.class)
@Import({ MessageHandlerAnnotationProcessor.class, MessageListenerConfiguration.class })
public class MessageHandlerAutoConfiguration {

}