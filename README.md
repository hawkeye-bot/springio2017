# Making the most of Spring Boot: adapting to your environment!
# Lab 3:
Welcome to lab 3. 

In this lab you will learn how to create and use a custom Spring Boot autoconfigure module in which you will add a default JMS messagelistener. 

The JMS messagelistener receives the message from a queue en saves the JMS message as a AcmeMessage in the HSQL database. The messagelistener doesn't handle the message itself, but delegates this to a messagehandler. For each message type, a specific message handler is configured, which is responsible for processing the message.
 
A messagehandler is a Spring bean annotated with the custom @MessageHandlerBean annotation. In a BeanPostProcessor all the beans annotated with the @MessageHandler bean annotation are added to the MessageHandlersMap bean (a Map with the JMStype as the key and the MessageHandler as the value), which will be autowired in the MessageListener bean. 
 
## Steps in the acme-commons-autoconfigure
1. Add the MessageHandler autoconfigure module to the 'spring.factories' file in the acme-commons-autoconfigure module
2. Complete the logic needed in the MessageHandlerAnnotationProcessor
3. Find the correct MessageHandler in the MessageListener (MessageListenerConfiguration class) and handle the ShipOrder message with the ShipOrderMessageHandler.
4. Build and deploy the acme-commons-framework

## Steps in the warehouse application
1. Delete the specific MessageHandler interface from the Warehouse application
2. Delete the specific WarehouseMessageListener messagelistener class.
3. Implement the ShipOrderMessageHandler with the MessageHandler from acme-commons-core
4. Annotate the ShipOrderMessageHandler with the @MessageHandlerBean annotation with the correct JMS type

## Steps in the finance application
1. Delete the specific MessageHandler interface from the Finance application
2. Delete the specific FinanceMessageListener messagelistener class.
3. Implement the ExecutePaymentMessageHandler with the MessageHandler from acme-commons-core
4. Annotate the ExecutePaymentMessageHandler with the @MessageHandlerBean annotation with the correct JMS type

## Test
 Test if the newly added custom Spring Boot autoconfigure module is working.
1. Start Apache Artemis
2. Start the Warehouse application
3. Send the ShipOrder message with the MessageIntegrationTest.testSendShipOrderMessage unittest from the Warehouse application. The message should be handled correctly and the logmessage 'Order for customer ... shipped' should appear in the logging
4. Send the Invoice message with the MessageIntegrationTest.testSendInvoice unittest from the Finance application.

## Artemis
The software in the labs runs based on an Artemis JMS server. Included in the repository is a standalone installation of Artemis 2.0.0. You can start it using the following steps:

1. Open a terminal in ${repository_home}/apache-artemis-2.0.0/springio/bin
2. Run `artemis run`
