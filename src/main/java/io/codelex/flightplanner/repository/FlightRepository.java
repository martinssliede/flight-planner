package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f " +
            "WHERE f.from.airport = :from " +
            "AND f.to.airport = :to " +
            "AND DATE(f.departureTime) = :departureTime ")
    List<Flight> searchFlight(String from, String to, LocalDate departureTime);
}
