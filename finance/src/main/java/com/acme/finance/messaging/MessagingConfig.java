package com.acme.finance.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoeckxer on 05/03/2017.
 */
@Configuration
public class MessagingConfig {
    @Autowired
    private ReceivePaymentHandler receivePaymentHandler;

    @Autowired
    private SendInvoiceHandler sendInvoiceHandler;

    @Bean
    public Map<String, MessageHandler> messageHandlersMap() {
        Map<String, MessageHandler> result = new HashMap<>();
        result.put("receivePayment",receivePaymentHandler);
        result.put("sendInvoice", sendInvoiceHandler);
        return result;
    }
}
