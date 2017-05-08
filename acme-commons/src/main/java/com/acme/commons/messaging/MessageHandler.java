package com.acme.commons.messaging;

/**
 * Interface intended for classes to handle a message
 *
 * @author ajorritsma
 */
public interface MessageHandler {
    /**
     * Handle the message
     *
     * @param message The message to handle
     */
    void handleMessage(String message);
}