package com.trading.matching_engine;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class MatchingEngineApplication {

	@PostConstruct
	public void init() {
		// Force the Java application to use UTC, bypassing the Windows system timezone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(MatchingEngineApplication.class, args);
	}

}
