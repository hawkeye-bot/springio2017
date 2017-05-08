package com.acme.commons.messaging;

import com.acme.commons.entities.AcmeMessage;
import com.acme.commons.entities.repository.AcmeMessageRepository;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * MessageListener for the Warehouse application
 *
 * @author ajorritsma
 */
public class DefaultMessageListener implements MessageListener {
    /**
     * Transformer to translate a JMS message into a AcmeMessage
     */
    private AcmeMessageTransformer acmeMessageTransformer = new AcmeMessageTransformer();
    /**
     * Repository to store messages
     */
    private AcmeMessageRepository acmeMessageRepository;
    /**
     * Map with messagehandlers
     */
    private Map<String, MessageHandler> messageHandlerMap;

    public DefaultMessageListener(AcmeMessageRepository acmeMessageRepository, Map<String, MessageHandler> messageHandlerMap) {
        this.acmeMessageRepository = acmeMessageRepository;
        this.messageHandlerMap = messageHandlerMap;
    }

    /**
     * Store the AcmeMessage. Get and call the MessageHandler for the configured JMStype.
     *
     * @param message The consumed JMS message
     */
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            AcmeMessage acmeMessage = acmeMessageTransformer.transform((TextMessage) message);

            acmeMessageRepository.save(acmeMessage);

            MessageHandler messageHandler = messageHandlerMap.get(acmeMessage.getType());
            messageHandler.handleMessage(acmeMessage.getPayload());
        }
    }
}