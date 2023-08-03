--liquibase formatted sql

--changeset martins:1

CREATE TABLE flight (
    id serial PRIMARY KEY,
    name VARCHAR(255) not null
);
