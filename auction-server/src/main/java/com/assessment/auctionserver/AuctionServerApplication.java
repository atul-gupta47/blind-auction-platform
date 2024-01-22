package com.assessment.auctionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assessment")
public class AuctionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionServerApplication.class, args);
	}

}
