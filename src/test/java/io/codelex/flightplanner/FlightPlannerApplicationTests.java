package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.request.CreateFlightRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightPlannerApplicationTests {

	@Autowired
	FlightController flightController;
	@Autowired
	FlightService flightService;
	@Autowired
	FlightRepository flightRepository;

	@Test
	void addFlightTest() {
		Flight flight = new Flight(1L, new Airport("Latvia", "Riga", "RIX"), new Airport("Russia", "Moscow", "DME"), "Ryanair", "2019-01-01 00:00", "2019-01-01 01:00");
		flightController.saveFlight(flight);
		Flight savedFlight = flightRepository.listFlights.get(0);
		Assertions.assertEquals(flight, savedFlight);
	}

	@Test
	void clearFlights() {
		flightController.clearFlights();
		Assertions.assertTrue(flightRepository.listFlights.isEmpty());
	}

}
