package io.codelex.flightplanner.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Airport {
    @NotBlank
    @NotNull
    private String airport;
    @NotBlank
    @NotNull
    private String city;
    @NotBlank
    @NotNull
    private String country;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public boolean isEqualToAirport(Airport other) {
        return other.getAirport().toUpperCase().trim().equals(this.getAirport().toUpperCase().trim()) &&
                this.getCity().toUpperCase().trim().equals(other.getCity().toUpperCase().trim()) &&
                this.getCountry().toUpperCase().trim().equals(other.getCountry().toUpperCase().trim());
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
    public String toString() {
        return "Airport{" +
                "airport='" + airport + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
