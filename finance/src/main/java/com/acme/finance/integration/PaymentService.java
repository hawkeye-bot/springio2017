package com.acme.finance.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void executePayment(BigDecimal amount, Integer accountNumber) {
        Map<String, String> payment = new HashMap<>();
        payment.put("amount", amount.toString());
        payment.put("accountNumber", accountNumber.toString());
        jmsTemplate.convertAndSend("outgoingQueue", payment);
    }
}
