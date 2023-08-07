package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// KAUT KĀDAS ANOTĀCIJAS PIETRŪKST? Pieliku JSon format
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;
    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "airport_from")
    private Airport from;
    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "airport_to")
    private Airport to;
    @NotBlank
    @NotNull
    @Column(name = "carrier")
    private String carrier;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "departureTime")
    private LocalDateTime departureTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "arrivalTime")
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(Long id, Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.arrivalTime = LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public boolean isEqualToFlight(Flight other) {
        return  getFrom().getAirport().equals(other.getFrom().getAirport()) &&
                getFrom().getCountry().equals(other.getFrom().getCountry()) &&
                getFrom().getCity().equals(other.getFrom().getCity()) &&
                getTo().getAirport().equals(other.getTo().getAirport()) &&
                getTo().getCountry().equals(other.getTo().getCountry()) &&
                getTo().getCity().equals(other.getTo().getCity()) &&
                getDepartureTime().equals(other.getDepartureTime()) &&
                getArrivalTime().equals(other.getArrivalTime()) &&
                getCarrier().equals(other.getCarrier());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDepartureTime() {
        return departureTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getArrivalTime() {
        return arrivalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(from, flight.from) && Objects.equals(to, flight.to) && Objects.equals(carrier, flight.carrier) && Objects.equals(departureTime, flight.departureTime) && Objects.equals(arrivalTime, flight.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, carrier, departureTime, arrivalTime);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", carrier='" + carrier + '\'' +
                ", departureTime='" + departureTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + '\'' +
                ", arrivalTime='" + arrivalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + '\'' +
                '}';
    }
}
