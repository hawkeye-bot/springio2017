package com.acme.warehouse.messaging.dto.transformer;

import com.acme.warehouse.domain.Article;
import com.acme.warehouse.domain.Order;
import com.acme.warehouse.domain.OrderLine;
import com.acme.warehouse.domain.repository.ArticleRepository;
import com.acme.warehouse.messaging.dto.ShipOrderVO;
import org.apache.commons.collections4.Transformer;

/**
 * Transformer to create a order based on a ShipOrder value object.
 *
 * @author ajorritsma
 */
public class OrderTransformer implements Transformer<ShipOrderVO, Order> {
	/**
	 * Repository to find the orderd articles
	 */
	private ArticleRepository articleRepository;

	public OrderTransformer(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public Order transform(ShipOrderVO input) {
		Order order = new Order();
		order.setCustomerName(input.getCustomerName());

		for (Long articleId : input.getArticleIds()) {
			Article article = articleRepository.findById(articleId);

			OrderLine orderLine = new OrderLine();
			orderLine.setOrder(order);
			orderLine.setArticle(article);

			order.getOrderLines().add(orderLine);
		}

		return order;
	}
}
