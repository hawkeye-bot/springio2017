package com.acme.finance.messaging;

/**
 * Created by hoeckxer on 05/03/2017.
 */
public interface MessageHandler {
    void handleMessage(String message);
}
