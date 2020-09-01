package com.userAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserAccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccountsServiceApplication.class, args);
	}

}
