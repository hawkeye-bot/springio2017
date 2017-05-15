package com.acme.warehouse.messaging.messagehandler;

/**
 * Interface intended for classes to handle a message
 *
 * @author ajorritsma
 */
// TODO this interface is now part of Acme commons core, so this specific version can be deleted.
public interface MessageHandler {
	/**
	 * Handle the message
	 * @param message The message to handle
	 */
	void handleMessage(String message);
}
