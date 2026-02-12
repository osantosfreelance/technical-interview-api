package com.ing.be.tia;

import com.ing.be.tia.statemachine.service.ListingLoader;
import com.ing.be.tia.statemachine.StateMachine;
import com.ing.be.tia.statemachine.state.Listing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TechnicalInterviewApiApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TechnicalInterviewApiApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TechnicalInterviewApiApplication.class, args);

		// Load listings from a JSON file (adjust path as needed)
		List<Listing> listings = ListingLoader.loadFromJsonFile("listings/listings-1.json");

		// Configure the state machine with desired states
		StateMachine stateMachine = new StateMachine(Arrays.asList(
				new GetMoviesState(),
				new GetViaSatelliteState()
		));

		// Process listings
		List<Listing> filtered = stateMachine.process(listings);
		LOGGER.info("Filtered listings count: " + filtered.size());
		for (Listing l : filtered) {
			LOGGER.info("Listing: {}", l.getFields());
		}
	}

}
