package com.acme.warehouse.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA configuration class
 *
 * @author ajorritsma
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.acme.warehouse.domain.repository")
@EntityScan(basePackages = "com.acme.warehouse.domain")
public class JpaConfig {
}
