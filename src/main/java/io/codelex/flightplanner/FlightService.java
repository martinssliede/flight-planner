package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.CreateFlightRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        Flight returnFlight = flightRepository.fetchFlight(id);
        if (returnFlight == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return returnFlight;
    }

    public synchronized void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    public Search searchFlight(CreateFlightRequest request) {
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

    public Flight findFlightById(Long id) {
        Flight returnFlight = flightRepository.findById(id);
        if (returnFlight == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return returnFlight;
        }
    }

    public List<Airport> searchAirport(String search) {
        return flightRepository.searchAirports(search.trim().toLowerCase());
    }

    public List<Flight> listFlights() {
        return flightRepository.listFlights();
    }

    public boolean checkIfSameFlight(Flight flight) {
        return flightRepository.listFlights().stream().anyMatch(f ->
                f.getFrom().getAirport().equals(flight.getFrom().getAirport()) &&
                f.getFrom().getCountry().equals(flight.getFrom().getCountry()) &&
                f.getFrom().getCity().equals(flight.getFrom().getCity()) &&
                f.getTo().getAirport().equals(flight.getTo().getAirport()) &&
                f.getTo().getCountry().equals(flight.getTo().getCountry()) &&
                f.getTo().getCity().equals(flight.getTo().getCity()) &&
                f.getDepartureTime().equals(flight.getDepartureTime()) &&
                f.getArrivalTime().equals(flight.getArrivalTime()) &&
                f.getCarrier().equals(flight.getCarrier()));
    }
    public boolean checkSameAirport(Flight flight) {
        return (flight.getTo().getAirport().toUpperCase().trim().equals(flight.getFrom().getAirport().toUpperCase().trim()) &&       flight.getFrom().getCity().toUpperCase().trim().equals(flight.getTo().getCity().toUpperCase().trim()) &&             flight.getFrom().getCountry().toUpperCase().trim().equals(flight.getTo().getCountry().toUpperCase().trim()));
    }
    public boolean checkIfStrangeDate(Flight flight) {
        return (LocalDateTime.parse(flight.getDepartureTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                .isAfter(LocalDateTime.parse(flight.getArrivalTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))) ||
                flight.getDepartureTime().equals(flight.getArrivalTime()));
    }

}
