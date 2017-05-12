package com.acme.commons.autoconfigure.database;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for all Acme commons database script locations
 *
 * @author erhoeckx
 */
@Configuration
@ConditionalOnClass(name = "org.hsqldb.jdbc.JDBCDataSource")
class HsqldbLocationConfiguration {

	@Bean
	@ConditionalOnClass(name = "com.acme.commons.entities.AcmeMessage")
	public MigrationScriptLocation cmsLocation() {
		return () -> "cms/db/migration/hsqldb";
	}
}