package com.acme.commons.autoconfigure;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation that insures Spring Boot properties to be filled with default values.
 *
 * @author ajorritsma
 */
public class DefaultPropertyLoader implements ApplicationListener<ApplicationStartingEvent> {
	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		Map<String, Object> defaultPropertyMap = new HashMap<>();
		defaultPropertyMap.put("flyway.baseline-on-migrate", "true");

		event.getSpringApplication().setDefaultProperties(defaultPropertyMap);
	}
}
