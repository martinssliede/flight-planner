--liquibase formatted sql

--changeset martins:2

CREATE TABLE airport (
    id serial PRIMARY KEY,
    name VARCHAR(255) not null
);