package com.issuetrack.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan("com.issuetrack")
@EntityScan(basePackages="com.issuetrack.model")
@EnableJpaRepositories(basePackages = {"com.issuetrack.repository","com.project.repository"})
@SpringBootApplication
public class IssuetrackApplication {
	public static void main(String[] args){
		SpringApplication.run(IssuetrackApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurerAdapter(){
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
