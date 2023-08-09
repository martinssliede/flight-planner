package io.codelex.flightplanner.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Long id;
    @NotBlank
    @NotNull
    private String country;
    @NotBlank
    @NotNull
    private String city;
    @NotBlank
    @NotNull
    private String airport;

    public Airport() {
    }

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public boolean isEqualToAirport(Airport other) {
        return other.getAirport().toUpperCase().trim().equals(this.getAirport().toUpperCase().trim()) && this.getCity().toUpperCase().trim().equals(other.getCity().toUpperCase().trim()) && this.getCountry().toUpperCase().trim().equals(other.getCountry().toUpperCase().trim());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport1 = (Airport) o;
        return Objects.equals(country, airport1.country) && Objects.equals(city, airport1.city) && Objects.equals(airport, airport1.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, airport);
    }

    @Override
    public String toString() {
        return "Airport{" + "airport='" + airport + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + '}';
    }
}
