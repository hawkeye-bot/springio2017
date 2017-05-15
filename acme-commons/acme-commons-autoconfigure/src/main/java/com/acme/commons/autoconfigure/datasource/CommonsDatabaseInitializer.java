package com.acme.commons.autoconfigure.datasource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Initializer for installation of all SQL scripts for the Acme database objects.
 *
 * @author erhoeckx
 */
@Configuration
@ConditionalOnBean(MigrationScriptLocation.class)
public class CommonsDatabaseInitializer implements DatabasePreInitializer {
	/**
	 * Logger to log events.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonsDatabaseInitializer.class);

	/**
	 * The datasource which is used to install the database objects
	 */
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private DataSource dataSource;

	/**
	 * List of all locations with migration scripts
	 */
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private List<MigrationScriptLocation> migrationScriptLocations = new ArrayList<>();

	@Override
	public void initialize() {
		LOGGER.debug("Initialize ACME-commons database-objects");
		Flyway flyway = new Flyway();

		// TODO start Flyway with correct migration script locations
	}

	/**
	 * @param locations The {@link MigrationScriptLocation}s to map.
	 * @return A array of Strings, mapped from the {@link MigrationScriptLocation#getLocation()}-methods on every
	 * {@link MigrationScriptLocation}.
	 */
	private String[] mapLocations(final List<MigrationScriptLocation> locations) {
		return migrationScriptLocations.stream()//
				.map(MigrationScriptLocation::getLocation)//
				.toArray(String[]::new);
	}
}