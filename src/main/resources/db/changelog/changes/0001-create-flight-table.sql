--liquibase formatted sql

--changeset martins:1

CREATE TABLE airport (
    airport_id SERIAL PRIMARY KEY NOT NULL,
    country    VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    airport    VARCHAR(255) NOT NULL
);

CREATE TABLE flight (
    flight_id SERIAL PRIMARY KEY not null,
    airport_from INTEGER not null references airport(airport_id),
    airport_to INTEGER not null references airport(airport_id),
    carrier VARCHAR(255) not null,
    departureTime timestamp not null,
    arrivalTime timestamp not null
);

