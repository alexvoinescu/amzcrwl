package org.pyro.amzcrawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AmzCrwlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmzCrwlApplication.class, args);
	}
}
