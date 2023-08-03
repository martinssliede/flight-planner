package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Flight fetchFlight(Long id);

    void deleteFlightById(Long id);
}
