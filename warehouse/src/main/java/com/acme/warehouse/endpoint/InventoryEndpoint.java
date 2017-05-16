package com.acme.warehouse.endpoint;

import com.acme.warehouse.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Endpoint to retrieve the inventory of the warehouse
 *
 * @author ajorritsma
 */
@Controller
public class InventoryEndpoint {
	/**
	 * Repository to find the articles from the inventory
	 */
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * @return Find all the articles
	 */
	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public @ResponseBody List<Article> findAll() {
		return articleRepository.findAll();
	}
}
