package com.acme.finance.messaging;

import com.acme.commons.entities.AcmeMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

/**
 * Created by hoeckxer on 05/03/2017.
 */
@Component
public class FinanceMessageListener {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource(name = "messageHandlersMap")
    private Map<String, MessageHandler> handlers;

    @JmsListener(destination = "incomingQueue")
    public void handleMessage(String message, @Header("messageId") String messageId, @Header("JmsType") String jmsType) {
        AcmeMessage msg = new AcmeMessage();
        msg.setMessageHeaderId(messageId);
        msg.setPayload(message);
        entityManager.persist(msg);

        handlers.get(jmsType).handleMessage(message);
    }
}
