package com.acme.warehouse.messaging.dto.transformer;

import com.acme.warehouse.domain.Article;
import com.acme.warehouse.domain.Order;
import com.acme.warehouse.domain.OrderLine;
import com.acme.warehouse.messaging.dto.ShipOrderVO;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Transformer to create a order based on a ShipOrder value object.
 *
 * @author ajorritsma
 */
@Component
public class OrderTransformer implements Transformer<ShipOrderVO, Order> {
	/**
	 * Repository to find the orderd articles
	 */
	@Autowired
	private ArticleRepository articleRepository;

	public Order transform(ShipOrderVO input) {
		Order order = new Order();
		order.setCustomerName(input.getCustomerName());

		for (Long articleId : input.getArticleIds()) {
			Article article = articleRepository.findOne(articleId);

			OrderLine orderLine = new OrderLine();
			orderLine.setOrder(order);
			orderLine.setArticle(article);

			order.getOrderLines().add(orderLine);
		}

		return order;
	}
}
