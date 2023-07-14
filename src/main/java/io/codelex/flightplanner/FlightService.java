package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
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
                    return flightRepository.saveFlight(flight);
                }
            }
        }
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }

    public Flight fetchFlight(Long id) {
        Optional<Flight> returnFlight = flightRepository.findFlight(id);
        return returnFlight.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public synchronized void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    public Search searchFlight(FlightRequest request) {
        if (request.getFrom() != null && request.getTo() != null && request.getDepartureDate() != null) {
            if (request.getFrom().equals(request.getTo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                return flightRepository.searchFlight(request);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Airport> searchAirport(String search) {
        return flightRepository.searchAirports(search.trim().toLowerCase());
    }

    public List<Flight> listFlights() {
        return flightRepository.listFlights();
    }

    public boolean checkIfSameFlight(Flight flight) {
        return flightRepository.listFlights().stream().anyMatch(f -> f.isEqualToFlight(flight));
    }
    public boolean checkSameAirport(Flight flight) {
        return flight.getFrom().isEqualToAirport(flight.getTo());
    }
    public boolean checkIfStrangeDate(Flight flight) {
        return LocalDateTime.parse(flight.getDepartureTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                .isAfter(LocalDateTime.parse(flight.getArrivalTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))) ||
                flight.getDepartureTime().equals(flight.getArrivalTime());
    }

}
