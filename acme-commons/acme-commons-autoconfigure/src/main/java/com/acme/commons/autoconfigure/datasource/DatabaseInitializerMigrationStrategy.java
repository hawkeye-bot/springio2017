package com.acme.commons.autoconfigure.datasource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * Database initializer for database setup
 *
 * @author erhoeckx
 */
@Configuration
@ConditionalOnClass(Flyway.class)
public class DatabaseInitializerMigrationStrategy implements FlywayMigrationStrategy {
	/**
	 * All initializers for initial database setup before the applications own installation.
	 */
	@Autowired(required = false)
	private List<DatabasePreInitializer> preInitializers = Collections.emptyList();

	@Override
	public void migrate(final Flyway flyway) {
		preInitializers.forEach(DatabasePreInitializer::initialize);

		flyway.migrate();
	}
}