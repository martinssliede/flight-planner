package io.codelex.flightplanner.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// Pieliku Valid un notnull
public class FlightRequest {
    @Valid
    @NotNull
    private String from;
    @Valid
    @NotNull
    private String to;
    private LocalDateTime departureDate;

    public FlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = LocalDateTime.parse(departureDate + "00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "CreateFlightRequest{" +
                "from=" + from  +
                ", to=" + to +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}
