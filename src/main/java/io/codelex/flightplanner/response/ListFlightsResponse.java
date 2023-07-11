package io.codelex.flightplanner.response;

import io.codelex.flightplanner.domain.Flight;

import java.util.List;

public class ListFlightsResponse {
    private List<Flight> flights;

    public ListFlightsResponse(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
