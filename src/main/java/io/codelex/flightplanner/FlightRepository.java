package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private Long id = 1L;

    List<Flight> listFlights = new ArrayList<>();

    public Flight saveFlight(Flight flight) {
        flight.setId(id);
        listFlights.add(flight);
        id++;
        return flight;
    }

    public void clearFlights() {
        listFlights.clear();
        id = 1L;
    }

    public Flight fetchFlight(Long id) {
        return listFlights.stream().filter(idNew -> id.equals(idNew.getId())).findAny().orElse(null);
    }

    public List<Flight> listFlights() {
        return listFlights;
    }

    public void deleteFlight(Long id) {
        listFlights.removeIf(flight -> flight.getId().equals(id));
    }

    public Search searchFlight(FlightRequest request) {
        List<Flight> items = listFlights.stream().filter(flight -> flight.getFrom().getAirport().equals(request.getFrom()) &&
                flight.getTo().getAirport().equals(request.getTo()) &&
                flight.getDepartureTime().substring(0, 10).equals(request.getDepartureDate())).toList();
        return new Search(items, 0, items.size());
    }

    public Flight findById(Long id) {
        return listFlights.stream().filter(idNew -> id.equals(idNew.getId())).findAny().orElse(null);
    }

    public List<Airport> searchAirports(String search) {
        return listFlights.stream().filter(flight -> flight.getFrom().getAirport().toLowerCase().contains(search) ||
                flight.getFrom().getCity().toLowerCase().contains(search) ||
                flight.getFrom().getCountry().toLowerCase().contains(search)).map(Flight::getFrom).toList();
    }
}
