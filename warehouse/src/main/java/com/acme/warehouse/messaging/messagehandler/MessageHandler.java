package com.acme.warehouse.messaging.messagehandler;

/**
 * Interface intended for classes to handle a message
 *
 * @author ajorritsma
 */
public interface MessageHandler {
	void handleMessage(String message);
}
