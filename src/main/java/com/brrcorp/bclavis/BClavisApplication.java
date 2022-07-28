package com.brrcorp.bclavis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.brrcorp.bclavis.cipher.repository")
public class BClavisApplication {
	public static void main(String[] args) {
		SpringApplication.run(BClavisApplication.class, args);
	}
}
