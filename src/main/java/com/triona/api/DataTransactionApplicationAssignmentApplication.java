package com.triona.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.triona")
public class DataTransactionApplicationAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataTransactionApplicationAssignmentApplication.class, args);
	}

}
