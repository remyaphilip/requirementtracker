package com.proman.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("com")
//@EntityScan(basePackages = { "com.proman..model", "com.issuetrack.model", "com.user.model" })
//@EnableJpaRepositories(basePackages = { "com.project.repository", "com.issuetrack.repository", "com.user.repository" })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}