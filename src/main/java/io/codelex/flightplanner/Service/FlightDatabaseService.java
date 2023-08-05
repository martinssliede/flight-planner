package io.codelex.flightplanner.Service;

import io.codelex.flightplanner.Repository.FlightRepository;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;

import java.util.List;


public class FlightDatabaseService implements FlightService {

    // JAUNIZVEIDOTAIS DB SERVISS, KURĀ IZMANTO FLIGHTSERVICE IZVEIDOTĀS METODES;
    private FlightRepository flightRepository;

    public FlightDatabaseService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    public Flight saveFlight(Flight flight) {
        Flight newFlight = new Flight();
        return flightRepository.save(newFlight);
    }


    public void clearFlights() {
        flightRepository.deleteAll();
    }


    public Flight fetchFlight(Long id) {
        return flightRepository.fetchFlight(id);
    }


    public void deleteFlight(Long id) {
        flightRepository.deleteFlightById(id);
    }


    public Search searchFlight(FlightRequest request) {
        return flightRepository.searchFlight(request);
    }


    public List<Airport> searchAirport(String search) {
        return flightRepository.searchAirport(search);
    }


    public List<Flight> listFlights() {
        return flightRepository.listFlights();
    }
}
