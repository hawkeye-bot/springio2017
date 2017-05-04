package com.acme.warehouse.messaging.dto.transformer;

import com.acme.warehouse.domain.AcmeMessage;
import org.apache.commons.collections4.Transformer;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Transformer to translate a JMS {@link TextMessage} to a {@link AcmeMessage}
 *
 * @author ajorritsma
 */
public class AcmeMessageTransformer implements Transformer<TextMessage, AcmeMessage> {

	public AcmeMessage transform(TextMessage input) {
		try {
			AcmeMessage output = new AcmeMessage();
			output.setMessageId(input.getJMSMessageID());
			output.setType(input.getJMSType());
			output.setPayload(input.getText());
			return output;
		} catch (JMSException e) {
			throw new RuntimeException("Unable to read JMS Message");
		}
	}
}
