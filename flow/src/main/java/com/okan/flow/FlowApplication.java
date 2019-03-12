package com.okan.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
@EnableKafka
public class FlowApplication {

	@Bean
	public StringJsonMessageConverter jsonConverter() {
		return new StringJsonMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(FlowApplication.class, args);
	}

}
