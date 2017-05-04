package com.acme.warehouse;

import com.acme.warehouse.messaging.dto.ShipOrderVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.api.jms.JMSFactoryType;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessagingIntegrationTest.TestConfig.class)
public class MessagingIntegrationTest {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testSendShipOrderMessage() throws Exception{
		// prepare
		ObjectMapper objectMapper = new ObjectMapper();
		ShipOrderVO shipOrderVO = new ShipOrderVO();
		shipOrderVO.setCustomerName("CustomerName");
		shipOrderVO.getArticleIds().add(1L);
		shipOrderVO.getArticleIds().add(2L);
		final String payload = objectMapper.writeValueAsString(shipOrderVO);

		// execute
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session)
					throws JMSException {
				TextMessage textMessage = session.createTextMessage(payload);
				textMessage.setJMSType("ShipOrder");

				return textMessage;
			}
		});

		// verify
	}

	@Configuration
	public static class TestConfig {

		@Bean
		public JmsTemplate jmsTemplate() {
			JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
			jmsTemplate.setDefaultDestinationName("ACME.WAREHOUSE");

			return jmsTemplate;
		}

		private ConnectionFactory connectionFactory() {
			Map<String, Object> props = new HashMap<String, Object>();
			props.put("host", "localhost");
			props.put("port", 61616);
			TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), props);

			ConnectionFactory connectionFactory = ActiveMQJMSClient
					.createConnectionFactoryWithHA(JMSFactoryType.QUEUE_CF, transportConfiguration);

			UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
			adapter.setTargetConnectionFactory(connectionFactory);
			adapter.setUsername("springio");
			adapter.setPassword("springio");

			return adapter;
		}

	}
}
