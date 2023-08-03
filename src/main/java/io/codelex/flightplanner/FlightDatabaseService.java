package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;

import java.util.List;

// VAI ŠEIT IR KAUT KAS UZ PAREIZO PUSI?
// KĀ IZVEIDOT ATBILSTOŠU TABULU PRIEKŠ FLIGHT UN AIRPORT? 

public class FlightDatabaseService implements FlightService{

    private FlightRepository flightRepository;

    public FlightDatabaseService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight saveFlight(Flight flight) {
        Flight newFlight = new Flight();
        return flightRepository.save(newFlight);
    }

    @Override
    public void clearFlights() {
        flightRepository.deleteAll();
    }

    @Override
    public Flight fetchFlight(Long id) {
        return flightRepository.fetchFlight(id);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteFlightById(id);
    }

    @Override
    public Search searchFlight(FlightRequest request) {

        return null;
    }

    @Override
    public List<Airport> searchAirport(String search) {
        return null;
    }

    @Override
    public List<Flight> listFlights() {
        return null;
    }
}
