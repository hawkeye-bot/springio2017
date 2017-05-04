package com.acme.finance.messaging;

import org.springframework.stereotype.Component;

/**
 * Created by hoeckxer on 05/03/2017.
 */
@Component
public class ReceivePaymentHandler implements MessageHandler {
    @Override
    public void handleMessage(String message) {

    }
}
