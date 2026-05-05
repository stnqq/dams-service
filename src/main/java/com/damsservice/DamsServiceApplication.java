package com.damsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
public class DamsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DamsServiceApplication.class, args);
	}
}