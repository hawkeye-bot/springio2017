package com.acme.warehouse.config;

import com.atomikos.jms.AtomikosConnectionFactoryBean;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.api.jms.JMSFactoryType;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import javax.jms.ConnectionFactory;
import javax.jms.XAConnectionFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Artemis configuration
 *
 * @author ajorritsma
 */
@Configuration
public class JmsConfig {
	/**
	 * The host of the Artemis server
	 */
	@Value("${spring.artemis.host}")
	private String host;

	/**
	 * The port of the Artemis server
	 */
	@Value("${spring.artemis.port}")
	private int port;

	/**
	 * The username to connect to the Artemis server
	 */
	@Value("${spring.artemis.user}")
	private String user;

	/**
	 * The password to connect to the Artemis server
	 */
	@Value("${spring.artemis.password}")
	private String password;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setTargetConnectionFactory(userCredentialsConnectionFactoryAdapter());
		cachingConnectionFactory.setSessionCacheSize(10);

		return cachingConnectionFactory;
	}

	/**
	 * @return a connectionfactory which can be used to connect to a Artemis server with a username and password
	 */

	private UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter() {
		final UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
		adapter.setTargetConnectionFactory(atomikosConnectionFactoryBean());
		adapter.setUsername(user);
		adapter.setPassword(password);

		return adapter;
	}

	/**
	 * @return a XA-aware connectionfactory to use with Atomikos
	 */
	private ConnectionFactory atomikosConnectionFactoryBean() {
		AtomikosConnectionFactoryBean atomikosConnectionFactoryBean = new AtomikosConnectionFactoryBean();
		atomikosConnectionFactoryBean.setUniqueResourceName("artemisXaJmsConnectionFactory");
		XAConnectionFactory xaConnectionFactory = ActiveMQJMSClient.createConnectionFactoryWithHA(JMSFactoryType.QUEUE_XA_CF, transportConfiguration());
		atomikosConnectionFactoryBean.setXaConnectionFactory(xaConnectionFactory);
		atomikosConnectionFactoryBean.setMaxPoolSize(10);

		return atomikosConnectionFactoryBean;
	}

	/**
	 * @return an instance of {@link TransportConfiguration} to specify the connection information of the Artemis JMS server
	 */
	private TransportConfiguration transportConfiguration() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("host", host);
		props.put("port", port);
		return new TransportConfiguration(NettyConnectorFactory.class.getName(), props);
	}
}
