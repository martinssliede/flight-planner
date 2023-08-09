--liquibase formatted sql

--changeset martins:1

CREATE TABLE airport
(
    airport_id SERIAL       NOT NULL,
    country    VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    airport    VARCHAR(255) NOT NULL,
    PRIMARY KEY (airport_id)
);

CREATE TABLE flight
(
    flight_id      SERIAL       not null,
    airport_from   INTEGER      not null references airport (airport_id),
    airport_to     INTEGER      not null references airport (airport_id),
    carrier        VARCHAR(255) not null,
    departure_time timestamp    not null,
    arrival_time   timestamp    not null,
    PRIMARY KEY (flight_id)
);



