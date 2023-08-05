--liquibase formatted sql

--changeset martins:1

CREATE TABLE flight (
    id serial PRIMARY KEY,
    airportFrom VARCHAR(255) not null,
    airportTo VARCHAR(255) not null,
    carrier VARCHAR(255) not null,
    departureTime timestamp not null,
    arrivalTime timestamp not null
);
