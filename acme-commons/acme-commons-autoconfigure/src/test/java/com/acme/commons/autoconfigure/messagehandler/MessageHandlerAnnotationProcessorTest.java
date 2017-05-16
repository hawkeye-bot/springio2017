package com.acme.commons.autoconfigure.messagehandler;

import com.acme.commons.messaging.MessageHandler;
import com.acme.commons.messaging.MessageHandlerBean;
import org.junit.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


public class MessageHandlerAnnotationProcessorTest {

    @Test
    public void testHandlerWithoutAnnotationsNotProcessed() {
        // prepare + execute
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(DataSourceAutoConfiguration.class, NotAnnotatedBeanConfiguration.class, MessageHandlerAnnotationProcessor.class,
                    MessageListenerConfiguration.class);
            context.refresh();

            // verify
            MessageListenerConfiguration messageListenerConfiguration = context.getBean(MessageListenerConfiguration.class);
            Map<String, MessageHandler> handlers = (Map<String, MessageHandler>) ReflectionTestUtils.getField(messageListenerConfiguration, "messageHandlersMap");
            assertThat(handlers.keySet(), is(empty()));
        }
    }

    @Test
    public void testHandlerWithAnnotationProcessedDefault() {
        // prepare + execute
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(DataSourceAutoConfiguration.class, AnnotatedBeanConfiguration.class, MessageHandlerAnnotationProcessor.class, MessageListenerConfiguration.class);
            context.refresh();

            // verify
            Map<String, ?> messageHandlersMap = (Map<String, ?>) context.getBean("messageHandlersMap");
            assertEquals(1, messageHandlersMap.size());
            assertNotNull(messageHandlersMap.get("TYPE"));
        }
    }

    @Test
    public void testHandlerWithAnnotationOnClassProcessed() {
        // prepare + execute
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(DataSourceAutoConfiguration.class, MessageHandlerWithAnnotationOnClassConfiguration.class,
                    MessageHandlerAnnotationProcessor.class, MessageHandlerAutoConfiguration.class);
            context.refresh();

            // verify
            Map<String, ?> messageHandlersMap = (Map<String, ?>) context.getBean("messageHandlersMap");
            assertEquals(1, messageHandlersMap.size());
            assertNotNull(messageHandlersMap.get("MH_CLASS_ANN"));
        }
    }

    @Configuration
    public static class AnnotatedBeanConfiguration {
        @MessageHandlerBean(type = "TYPE")
        public MessageHandler annotatedMessageHandlerBean() {
            return mock(MessageHandler.class);
        }
    }

    @Configuration
    public static class NotAnnotatedBeanConfiguration {
        @Bean
        public MessageHandler notAnnotatedMessageHandlerBean() {
            return mock(MessageHandler.class);
        }

    }

    @Configuration
    public static class MessageHandlerWithAnnotationOnClassConfiguration {
        @MessageHandlerBean(type = "MH_CLASS_ANN")
        public static class MessageHandlerWithAnnotationOnClass implements MessageHandler {
            @Override
            public void handleMessage(String message) {

            }
        }
    }
}
