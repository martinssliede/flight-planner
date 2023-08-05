package io.codelex.flightplanner.Repository;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    // ŠEIT VAJAG QUERY??
    Flight fetchFlight(Long id);

    void deleteFlightById(Long id);

    Search searchFlight(FlightRequest request);

    List<Airport> searchAirport(String search);

    List<Flight> listFlights();
}
