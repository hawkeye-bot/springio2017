package com.acme.commons.autoconfigure;

import com.acme.commons.entities.repository.AcmeMessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsAutoConfiguration {
    @Bean
    public AcmeMessageRepository myRepository() {
        return new AcmeMessageRepository();
    }
}
