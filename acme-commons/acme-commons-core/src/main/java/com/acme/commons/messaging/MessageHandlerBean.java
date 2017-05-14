package com.acme.commons.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Annotation to put on a messagehandler-implementation. With the annotation the specified JmsType can be specified
 *
 * @author ajorritsma
 */
@Target({ METHOD, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Bean
@Component
public @interface MessageHandlerBean {
	/**
	 * @return The JMStype for the specified MessageHandler
	 */
	String type();
}