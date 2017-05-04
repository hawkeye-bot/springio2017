package com.acme.warehouse.config;

import com.acme.warehouse.domain.AcmeMessage;
import com.acme.warehouse.domain.Article;
import com.acme.warehouse.domain.repository.AcmeMessageRepository;
import com.acme.warehouse.domain.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Class to load data in the HSQLDB.
 *
 * @author ajorritsma
 */
@Component
public class DataLoader {
	@Autowired
	private AcmeMessageRepository acmeMessageRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@PostConstruct
	public void load() {
		acmeMessageRepository.save(createAcmeMessage(UUID.randomUUID().toString(), "payload1"));
		acmeMessageRepository.save(createAcmeMessage(UUID.randomUUID().toString(), "payload2"));

		articleRepository.save(createArticle("iPhone 7", BigDecimal.valueOf(899L)));
		articleRepository.save(createArticle("Samsung Galaxy S8", BigDecimal.valueOf(921L)));
		articleRepository.save(createArticle("Huawei P9", BigDecimal.valueOf(761L)));
	}

	private Article createArticle(String name, BigDecimal price) {
		Article article = new Article();
		article.setName(name);
		article.setPrice(price);

		return article;
	}

	private AcmeMessage createAcmeMessage(String messageId, String payload) {
		AcmeMessage acmeMessage = new AcmeMessage();
		acmeMessage.setMessageId(messageId);
		acmeMessage.setPayload(payload);

		return acmeMessage;
	}
}
