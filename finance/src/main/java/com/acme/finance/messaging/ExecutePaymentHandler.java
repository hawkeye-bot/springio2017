package com.acme.finance.messaging;

import com.acme.finance.integration.PaymentService;
import com.acme.finance.messaging.dto.ExecutePaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by hoeckxer on 05/03/2017.
 */
@Component
public class ExecutePaymentHandler implements MessageHandler {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleMessage(String message) {
        try {
            ExecutePaymentRequest request = objectMapper.reader(ExecutePaymentRequest.class).readValue(message);
            paymentService.executePayment(request.getAmount(), request.getAccountNumber());
        } catch (IOException e) {
            throw new RuntimeException("Error processing message", e);
        }
    }
}