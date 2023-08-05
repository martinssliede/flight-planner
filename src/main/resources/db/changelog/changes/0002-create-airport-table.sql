--liquibase formatted sql

--changeset martins:2

CREATE TABLE airport (
    airport VARCHAR(255) not null,
    city VARCHAR(255) not null,
    country VARCHAR(255) PRIMARY KEY
);