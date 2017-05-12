package com.acme.finance.config;

import com.acme.finance.messaging.MessageHandler;
import com.acme.finance.messaging.ExecutePaymentHandler;
import com.acme.finance.messaging.SendInvoiceHandler;
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
    private ExecutePaymentHandler executePaymentHandler;

    @Autowired
    private SendInvoiceHandler sendInvoiceHandler;

    @Bean
    public Map<String, MessageHandler> messageHandlersMap() {
        Map<String, MessageHandler> result = new HashMap<>();
        result.put("receivePayment", executePaymentHandler);
        result.put("printLetter", sendInvoiceHandler);
        return result;
    }
}
