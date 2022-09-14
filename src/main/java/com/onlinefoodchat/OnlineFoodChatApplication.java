package com.onlinefoodchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ServletComponentScan
public class OnlineFoodChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodChatApplication.class, args);
	}
	
	@Bean
	public RestTemplate  restTemplate() {
		return new RestTemplate();
	}
}
