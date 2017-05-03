package com.acme.warehouse.config;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

/**
 * {@link PersistenceUnitPostProcessor} to add a XA datasource.
 *
 * @author ajorritsma
 */
public class JtaDataSourcePersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {
	private DataSource jtaDataSource;

	public JtaDataSourcePersistenceUnitPostProcessor(DataSource jtaDataSource) {
		this.jtaDataSource = jtaDataSource;
	}

	public void postProcessPersistenceUnitInfo(final MutablePersistenceUnitInfo mutablePersistenceUnitInfo) {
		mutablePersistenceUnitInfo.setJtaDataSource(jtaDataSource);
		mutablePersistenceUnitInfo.setTransactionType(PersistenceUnitTransactionType.JTA);
	}
}
