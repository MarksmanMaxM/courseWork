package com.example.courseWork;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition
@EnableCaching
public class CourseWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseWorkApplication.class, args);
	}

}
