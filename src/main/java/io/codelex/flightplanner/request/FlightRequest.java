package io.codelex.flightplanner.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightRequest {
    @Valid
    @NotNull
    private String from;
    @Valid
    @NotNull
    private String to;
    private LocalDate departureDate;

    public FlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "CreateFlightRequest{" + "from=" + from + ", to=" + to + ", departureDate='" + departureDate + '\'' + '}';
    }
}
