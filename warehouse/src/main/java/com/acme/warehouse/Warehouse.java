package com.acme.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.acme")
public class Warehouse {
	public static void main(String[] args) {
		SpringApplication.run(Warehouse.class, args);
	}
}
