package com.acme.commons.autoconfigure.database;

/**
 * Interface that can be used to initialize on the database. These initializers will be executed before, for example, the application sql scripts are executed with flyway.
 *
 * @author erhoeckx
 */
public interface DatabasePreInitializer {
	/**
	 * To run an initialization of the database
	 */
	void initialize();
}