package com.acme.finance.messaging;

import com.acme.finance.integration.PrintService;
import com.acme.finance.messaging.dto.SendInvoiceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by hoeckxer on 05/03/2017.
 */
@Component
public class SendInvoiceHandler implements MessageHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PrintService printService;

    @Override
    public void handleMessage(String message) {
        try {
            SendInvoiceRequest request = objectMapper.reader(SendInvoiceRequest.class).readValue(message);
            printService.printLetter(request.getName(), request.getAddress(), request.getContent());
        } catch (IOException e) {
            throw new RuntimeException("Error processing message", e);
        }
    }
}