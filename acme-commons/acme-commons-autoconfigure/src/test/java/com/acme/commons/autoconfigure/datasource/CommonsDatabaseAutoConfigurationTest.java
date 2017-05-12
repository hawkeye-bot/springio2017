package com.acme.commons.autoconfigure.datasource;

import org.junit.Test;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class of the {@link CommonsDatabaseAutoConfigurationTest}
 *
 * @author erhoeckx
 */
public class CommonsDatabaseAutoConfigurationTest {
	/**
	 * Checks if the flyway migratie has been executed.
	 */
	@Test
	public void testDatabaseInitialized()
			throws Exception {
		// prepare
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.register(DataSourceAutoConfiguration.class, //
					FlywayAutoConfiguration.class, //
					DatabaseInitializerMigrationStrategy.class, //
					CommonsDatabaseAutoConfiguration.class);
			EnvironmentTestUtils.addEnvironment(context, //
					//"spring.datasource.schema:nl/cjib/commons/autoconfigure/datasource/cms/schema.sql", //
					"flyway.baseline-version:0", //
					"flyway.baseline-on-migrate:true");
			context.refresh();

			// execute & verify
			assertQuery(context, "SELECT COUNT(*) FROM ACMEMESSAGES");
			assertQuery(context, "SELECT COUNT(*) FROM ACMEDUMMY");
		}

	}

	private void assertQuery(AnnotationConfigApplicationContext context, String query)
			throws SQLException {
		DataSource ds = context.getBean(DataSource.class);
		ResultSet result = ds.getConnection().createStatement().executeQuery(query);
		result.next();
		int count = result.getInt(1);

		// verify
		assertThat(count, is(0));
	}
}