package io.codelex.flightplanner.request;

import io.codelex.flightplanner.domain.Airport;

import java.time.LocalDateTime;

public class CreateFlightRequest {
    private String from;
    private String to;
    private String departureDate;

    public CreateFlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
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
