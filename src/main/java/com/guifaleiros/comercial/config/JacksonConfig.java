package com.guifaleiros.comercial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guifaleiros.comercial.models.PaymentCard;
import com.guifaleiros.comercial.models.PaymentSlip;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentSlip.class);
				objectMapper.registerSubtypes(PaymentCard.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
