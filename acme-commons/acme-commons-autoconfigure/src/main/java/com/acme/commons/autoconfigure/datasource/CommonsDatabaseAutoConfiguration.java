package com.acme.commons.autoconfigure.datasource;

import com.acme.commons.entities.AcmeMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Autoconfigure class for database setup with ACME common objects.
 *
 * @author ajorritsma
 */
@Configuration
@ConditionalOnClass(AcmeMessage.class)
@ConditionalOnProperty(value = "acme.commons.persistence.sql.enabled", matchIfMissing = true)
@Import({ HsqldbLocationConfiguration.class, CommonsDatabaseInitializer.class })
public class CommonsDatabaseAutoConfiguration {
}
