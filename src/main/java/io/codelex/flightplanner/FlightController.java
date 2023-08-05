package io.codelex.flightplanner;

import io.codelex.flightplanner.Service.FlightService;
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

    private FlightService flightInMemoryService;

    public FlightController(FlightService flightInMemoryService) {
        this.flightInMemoryService = flightInMemoryService;
    }

    @PostMapping("/testing-api/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearFlights() {
        flightInMemoryService.clearFlights();
    }

    @PutMapping("/admin-api/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight saveFlight(@RequestBody @Valid Flight flight) {
        return flightInMemoryService.saveFlight(flight);
    }
    @GetMapping("/admin-api/flights")
    public List<Flight> getFlight() {
        return flightInMemoryService.listFlights();
    }

    @GetMapping("/admin-api/flights/{id}")
    public Flight fetchFlight(@PathVariable("id") Long id) {
        return flightInMemoryService.fetchFlight(id);
    }

    @DeleteMapping("/admin-api/flights/{id}")
    public void deleteFlight(@PathVariable("id") Long id) {
        flightInMemoryService.deleteFlight(id);
    }

    @PostMapping("api/flights/search")
    public Search searchFlight(@RequestBody FlightRequest request) {
        return flightInMemoryService.searchFlight(request);
    }

    @GetMapping("api/flights/{id}")
    public Flight findFlightById(@PathVariable("id") Long id) {
        return flightInMemoryService.fetchFlight(id);
    }

    @GetMapping("api/airports")
    public List<Airport> searchAirport(@RequestParam String search) {
        return flightInMemoryService.searchAirport(search);
    }
}
