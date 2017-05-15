# Making the most of Spring Boot: adapting to your environment!
# Lab 1:
Welcome to lab 1. 

In this lab you will learn how to create and use a custom Spring Boot starter. 

The acme-commons-starter-logging forces Spring Boot to use Log4j2 instead of the default use of Logback as a logging framework. 

The acme-commons-starter-web forces Spring Boot to use Jetty as the servlet container instead of the default Tomcat.
  
 
## Steps in the acme-commons-starters module
1. Add a acme-commons-starter-logging with a dependency to 'org.springframework.boot:spring-boot-starter-log4j2'. In the dependency 'org.springframework.boot:spring-boot-starter-logging' logback has to be execluded. 
2. Add a acme-commons-starter-web with a dependency to 'org.springframework.boot:spring-boot-starter-jetty'. In the dependency 'org.springframework.boot:spring-boot-starter-web' spring-boot-starter-tomcat has to be execluded.
3. Build and deploy the acme-commons-framework

## Steps in the warehouse application
1. The Warehouse application is still legacy application based on Spring 3.2.8 the application has to be migrated to Spring Boot first. Take a look at the Finance application how a Spring Boot application will look like. If you find that the migration of the warehouse application to Spring Boot costs too much time, you can move forward to the next step.
2. Add the dependency acme-commons-starter-logging  
3. Add the dependency acme-commons-starter-web

## Steps in the finance application
1. Add the dependency acme-commons-starter-logging  
2. Add the dependency acme-commons-starter-web

## Test
 Test if the newly added custom Spring Boot starters are working.
1. Start Apache Artemis
2. Start the Finance and / or Warehouse application. You will see the application is started with a Jetty servlet container and Log4j2 is used as the standard logging framework
3. With a specific log4j2.xml configuration you can test if Log4j2 is used instead of Logback

## Artemis
The software in the labs runs based on an Artemis JMS server. Included in the repository is a standalone installation of Artemis 2.0.0. You can start it using the following steps:

1. Open a terminal in ${repository_home}/apache-artemis-2.0.0/springio/bin
2. Run `artemis run`
