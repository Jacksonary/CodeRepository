package com.hhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RbacTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacTestApplication.class, args);
	}
	
}
