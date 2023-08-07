package io.codelex.flightplanner.service;

import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class FlightDatabaseService implements FlightService {

    // JAUNIZVEIDOTAIS DB SERVISS, KURĀ IZMANTO FLIGHTSERVICE IZVEIDOTĀS METODES;
    private FlightRepository flightRepository;

    public FlightDatabaseService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public synchronized Flight saveFlight(Flight flight) {
        Flight newFlight = new Flight();
        newFlight.setFrom(flight.getFrom());
        newFlight.setTo(flight.getFrom());
        newFlight.setCarrier(flight.getCarrier());
        newFlight.setDepartureTime(flight.getDepartureTime());
        newFlight.setArrivalTime(flight.getArrivalTime());
        flightRepository.save(newFlight);
        return newFlight;
    }
//    @Override
//    public synchronized Flight saveFlight(Flight flight) {
//        if (checkSameFlight(flight)) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT);
//        } else {
//            if (checkSameAirport(flight)) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//            } else {
//                airportRepository.save(flight.getFrom());
//                airportRepository.save(flight.getTo());
//                flightRepository.save(flight);
//                return flight;
//            }
//        }
//    }

    @Override
    public void clearFlights() {
        flightRepository.deleteAll();
    }

    @Override
    public Flight fetchFlight(Long id) {
        Flight returnFlight  = flightRepository.findById(id).orElse(null);
        if (returnFlight == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return returnFlight;
    }

    @Override
    public synchronized void deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
    }

    @Override
    public Search searchFlight(FlightRequest request) {
        return null;
    }


//    @Override
//    public Search searchFlight(FlightRequest request) {
//        if (request.getFrom() != null && request.getTo() != null && request.getDepartureDate() != null) {
//            if (request.getFrom().equals(request.getTo())) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//            } else {
//                List<Flight> items = flightRepository.searchFlight(request.getFrom(), request.getTo(), request.getDepartureDate());
//                return new Search(items, 0, items.size());
//            }
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }


    @Override
    public List<Airport> searchAirport(String search) {
        return null;
    }
//    @Override
//    public List<Airport> searchAirport(String search) {
//        return airportRepository.searchAirports(search.trim().toLowerCase());
//    }

    @Override
    public List<Flight> listFlights() {
        return flightRepository.findAll();
    }

    public boolean checkSameFlight(Flight flight) {
        try {
            return flightRepository.findAll().stream().anyMatch(fl ->
                    fl.getFrom().getAirport().equals(flight.getFrom().getAirport()) &&
                    fl.getFrom().getCountry().equals(flight.getFrom().getCountry()) &&
                    fl.getFrom().getCity().equals(flight.getFrom().getCity()) &&
                    fl.getTo().getAirport().equals(flight.getTo().getAirport()) &&
                    fl.getTo().getCountry().equals(flight.getTo().getCountry()) &&
                    fl.getTo().getCity().equals(flight.getTo().getCity()) &&
                    fl.getDepartureTime().equals(flight.getDepartureTime()) &&
                    fl.getArrivalTime().equals(flight.getArrivalTime()) &&
                    fl.getCarrier().equals(flight.getCarrier()));
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean checkSameAirport(Flight flight) {
        return (flight.getTo().getAirport().toUpperCase().trim().equals(flight.getFrom().getAirport().toUpperCase().trim()) &&
               flight.getFrom().getCity().toUpperCase().trim().equals(flight.getTo().getCity().toUpperCase().trim()) &&
               flight.getFrom().getCountry().toUpperCase().trim().equals(flight.getTo().getCountry().toLowerCase().trim()));
    }

    public boolean checkTime(Flight flight) {
        return (LocalDateTime.parse(flight.getDepartureTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                .isAfter(LocalDateTime.parse(flight.getArrivalTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
        || flight.getDepartureTime().equals(flight.getArrivalTime()));
    }

}
