package com.bbs.app;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusBookingApplication {
	
	@PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));
	  }

	public static void main(String[] args) {
		SpringApplication.run(BusBookingApplication.class, args);
	}
}
