package com.NextJobs.NextJobsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class NextJobsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextJobsApiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
