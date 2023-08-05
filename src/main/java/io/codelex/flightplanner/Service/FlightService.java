package io.codelex.flightplanner.Service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;

import java.util.List;

public interface FlightService {

    Flight saveFlight(Flight flight);

    void clearFlights();

    Flight fetchFlight(Long id);

    void deleteFlight(Long id);

    Search searchFlight(FlightRequest request);

    List<Airport> searchAirport(String search);

    List<Flight> listFlights();
}
