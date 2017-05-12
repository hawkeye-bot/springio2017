package com.acme.commons.autoconfigure.database;

/**
 * Interface that can be used to specify a folder in which database migration scripts can be found during the
 * migration of the database.
 *
 * @author erhoeckx
 */
public interface MigrationScriptLocation {
	String getLocation();
}