package com.spring.test.test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	String home() {
		return "hello world";
	}
	
	@RequestMapping("/first")
	String first() {
		return "this is a spring boot demo";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
