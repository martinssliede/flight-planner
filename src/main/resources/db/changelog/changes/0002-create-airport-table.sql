--liquibase formatted sql

--changeset martins:2
CREATE TABLE airport (
                         airport_id SERIAL   primary key NOT NULL,
                         country    VARCHAR(255) NOT NULL,
                         city       VARCHAR(255) NOT NULL,
                         airport    VARCHAR(255) NOT NULL
);

