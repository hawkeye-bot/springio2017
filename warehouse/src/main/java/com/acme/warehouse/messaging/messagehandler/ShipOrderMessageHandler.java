package com.acme.warehouse.messaging.messagehandler;

import com.acme.warehouse.domain.Order;
import com.acme.warehouse.domain.repository.OrderRepository;
import com.acme.warehouse.messaging.dto.transformer.OrderTransformer;
import com.acme.warehouse.messaging.dto.ShipOrderVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * {@link MessageHandler} to handle the ShipOrder message.
 *
 * @author ajorritsma
 */
public class ShipOrderMessageHandler implements MessageHandler {
	/**
	 * Logger to log events
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ShipOrderMessageHandler.class);
	/**
	 * ObjectMapper for JSON serialization
	 */
	private ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * Transformer to translate the ShipOrderVO to a Order
	 */
	private OrderTransformer orderTransformer;
	/**
	 * Repository to store orders
	 */
	private OrderRepository orderRepository;

	public ShipOrderMessageHandler(OrderTransformer orderTransformer, OrderRepository orderRepository) {
		this.orderTransformer = orderTransformer;
		this.orderRepository = orderRepository;
	}

	/**
	 * Handle the ShipOrderVO message. The order will be stored
	 * @param message The message to handle
	 */
	public void handleMessage(String message) {
		try {
			ShipOrderVO shipOrderVO = objectMapper.reader(ShipOrderVO.class).readValue(message);

			Order order = orderTransformer.transform(shipOrderVO);

			orderRepository.save(order);

			LOGGER.info("Order for customer {} shipped", shipOrderVO.getCustomerName());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
