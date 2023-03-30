package com.project.questsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class QuestsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestsiteApplication.class, args);
	}

}
