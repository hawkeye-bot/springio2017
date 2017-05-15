# Making the most of Spring Boot: adapting to your environment!
# Lab 2:
Welcome to lab 2. 

In this lab you will learn how to create and use a custom Spring Boot autoconfigure module which adds a more flexible Flyway migration strategy. 

The custom Flyway migration strategy is able to run Flyway database migration scripts from different modules. Because we added the script for the AcmeMessage entity to the acme-commons-core module, this SQL script is needed to setup the table in the HSQL database. After Flyway added the AcmeMessage table, the application specific database objects or scripts need to be installed.
 
The CommonsDatabaseAutconfiguration module is a specific Flyway migration strategy. First it will start the DatabasePreInitializers. The CommonsDatabaseInitializer is a Spring bean that implements the DatabasePreInitializer. The DatabaseInitializerMigrationStrategy is a Flyway migration strategy that will start Flyway with the MigrationScriptLocation beans to indicate the correct locations for the SQL scripts. In the HsqldbLocationConfiguration configuration class the bean for the location of the acme-commons-core SQL scripts will be added. 
 
## Steps in the acme-commons-autoconfigure
1. Add the Commons Database autoconfigure module to the 'spring.factories' file in the acme-commons-autoconfigure module
2. Add the MigrationScriptLocation for the Acme Commons core scripts to the HsqldbLocationConfiguration
3. Tell Flyway in the CommonsDatabaseIntializer to get all the SQL scripts from the configured MigrationScriptLocations beans
4. Build and deploy the acme-commons-framework

## Steps in the warehouse application
1. Delete the AcmeMessage entity from the Warehouse application
2. Add the 'com.acme.commons.entities' to the @EntityScan in the JpaConfig configuration class
3. Add the 'com.acme.commons.entities.repository' to the @EnableJpaRepositories in the JpaConfig configuration class
4. Remove the 'create table' statement for the AcmeMessage table fom the V1_0_0_001__init.sql script

## Steps in the finance application
1. Add a JpaConfig configuration class similar to the Warehouse application implementation with a entity & JPA repository scan.
2. Add Flyway as a Maven dependency to the pom.xml This will trigger the DatabaseInitializerMigrationStrategy in the acme-commons-autoconfigure module.
3. Add Hsqldb as a Maven dependency to the pom.xml. This will trigger the HsqldbLocationConfiguration in the acme-commons-autoconfigure module.

## Test
 Test if the newly added custom Spring Boot autoconfigure module is working.
1. Start Apache Artemis
2. Start the Warehouse application. Two flyway migrations should be started.
3. Send the ShipOrder message with the MessageIntegrationTest.testSendShipOrderMessage unittest from the Warehouse application. The message should be handled correctly and the logmessage 'Order for customer ... shipped' should appear in the logging
4. Send the Invoice message with the MessageIntegrationTest.testSendInvoice unittest from the Finance application.

## Artemis
The software in the labs runs based on an Artemis JMS server. Included in the repository is a standalone installation of Artemis 2.0.0. You can start it using the following steps:

1. Open a terminal in ${repository_home}/apache-artemis-2.0.0/springio/bin
2. Run `artemis run`
