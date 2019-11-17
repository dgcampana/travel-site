package com.kmp.site.travelweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TravelWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelWebApplication.class, args);
	}

}
