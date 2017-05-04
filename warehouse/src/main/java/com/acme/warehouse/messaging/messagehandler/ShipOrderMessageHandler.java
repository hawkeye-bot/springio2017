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
	private static final Logger LOGGER = LoggerFactory.getLogger(ShipOrderMessageHandler.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	private OrderTransformer orderTransformer;
	private OrderRepository orderRepository;

	public ShipOrderMessageHandler(OrderTransformer orderTransformer, OrderRepository orderRepository) {
		this.orderTransformer = orderTransformer;
		this.orderRepository = orderRepository;
	}

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
