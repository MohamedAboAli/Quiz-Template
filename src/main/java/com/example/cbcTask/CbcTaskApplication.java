package com.example.cbcTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@EnableJpaRepositories(basePackages = "repository")  
@ComponentScan(basePackages= {"controllers","services"}) 
@EntityScan(value = "model")   
@SpringBootApplication
public class CbcTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbcTaskApplication.class, args);
	}
}
