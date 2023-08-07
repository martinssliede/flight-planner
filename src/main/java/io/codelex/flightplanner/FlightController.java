package io.codelex.flightplanner;

import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.Search;
import io.codelex.flightplanner.request.FlightRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Validated
public class FlightController {
// Nomainu visus servisus uz flightSevice;
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/testing-api/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearFlights() {
        flightService.clearFlights();
    }

    @PutMapping("/admin-api/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight saveFlight(@RequestBody @Valid Flight flight) {
        return flightService.saveFlight(flight);
    }
    @GetMapping("/admin-api/flights")
    public List<Flight> getFlight() {
        return flightService.listFlights();
    }

    @GetMapping("/admin-api/flights/{id}")
    public Flight fetchFlight(@PathVariable("id") Long id) {
        return flightService.fetchFlight(id);
    }

    @DeleteMapping("/admin-api/flights/{id}")
    public void deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
    }

    @PostMapping("api/flights/search")
    public Search searchFlight(@RequestBody FlightRequest request) {
        return flightService.searchFlight(request);
    }

    @GetMapping("api/flights/{id}")
    public Flight findFlightById(@PathVariable("id") Long id) {
        return flightService.fetchFlight(id);
    }

    @GetMapping("api/airports")
    public List<Airport> searchAirport(@RequestParam String search) {
        return flightService.searchAirport(search);
    }
}
