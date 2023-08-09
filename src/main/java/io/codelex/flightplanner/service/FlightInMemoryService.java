package io.codelex.flightplanner.service;

import io.codelex.flightplanner.repository.FlightInMemoryRepository;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class FlightInMemoryService implements FlightService {
    FlightInMemoryRepository flightInMemoryRepository;

    public FlightInMemoryService(FlightInMemoryRepository flightInMemoryRepository) {
        this.flightInMemoryRepository = flightInMemoryRepository;
    }

    public synchronized Flight saveFlight(Flight flight) {
        if (checkIfSameFlight(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {
            if (checkSameAirport(flight)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                if (checkIfStrangeDate(flight)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                } else {
                    return flightInMemoryRepository.saveFlight(flight);
                }
            }
        }
    }

    public void clearFlights() {
        flightInMemoryRepository.clearFlights();
    }

    public Flight fetchFlight(Long id) {
        Optional<Flight> returnFlight = flightInMemoryRepository.findFlight(id);
        return returnFlight.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public synchronized void deleteFlight(Long id) {
        flightInMemoryRepository.deleteFlight(id);
    }

    public Search searchFlight(FlightRequest request) {
        if (request.getFrom() != null && request.getTo() != null && request.getDepartureDate() != null) {
            if (request.getFrom().equals(request.getTo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                return flightInMemoryRepository.searchFlight(request);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Airport> searchAirport(String search) {
        return flightInMemoryRepository.searchAirports(search.trim().toLowerCase());
    }

    public List<Flight> listFlights() {
        return flightInMemoryRepository.listFlights();
    }

    public boolean checkIfSameFlight(Flight flight) {
        return flightInMemoryRepository.listFlights().stream().anyMatch(f -> f.isEqualToFlight(flight));
    }

    public boolean checkSameAirport(Flight flight) {
        return flight.getFrom().isEqualToAirport(flight.getTo());
    }

    public boolean checkIfStrangeDate(Flight flight) {
        return flight.getDepartureTime()
                .isAfter(flight.getArrivalTime()) ||
                flight.getDepartureTime().equals(flight.getArrivalTime());
    }

}
