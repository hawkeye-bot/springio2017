package com.acme.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Finance {
    public static void main(String[] args) {
        SpringApplication.run(Finance.class, args);
    }
}
