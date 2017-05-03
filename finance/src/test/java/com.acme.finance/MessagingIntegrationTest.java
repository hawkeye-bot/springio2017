package com.acme.finance;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
public class MessagingIntegrationTest {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void testSendInvoice() {
        //prepare

        //execute
        jmsTemplate.convertAndSend();

        //verify
    }
}
