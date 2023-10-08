package com.valcon.WeatherApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "com.valcon.WeatherApp")
@EnableTransactionManagement
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "WeatherApp", version = "1.0", description = "An application for weather forecast"))
public class WeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);

	}



}
