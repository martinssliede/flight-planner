package io.codelex.flightplanner;

import io.codelex.flightplanner.request.FlightRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    FlightRepository repository;
    @InjectMocks
    FlightService flightService;
    @Captor
    ArgumentCaptor<FlightRequest> flightCaptor;

    @Test
    void searchFlight() {
        FlightRequest request = new FlightRequest("RIX", "DXB", "2019-01-01");
        flightService.searchFlight(request);
        Mockito.verify(repository).searchFlight(flightCaptor.capture());
        FlightRequest searchedRequest = flightCaptor.getValue();
        Assertions.assertEquals(request.getTo(), searchedRequest.getTo());
        Assertions.assertEquals(request.getFrom(), searchedRequest.getFrom());
        Assertions.assertEquals(request.getDepartureDate(), searchedRequest.getDepartureDate());
    }
}