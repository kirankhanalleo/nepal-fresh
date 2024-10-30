-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS genders
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    version BIGINT               NOT NULL,
    gender VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_gender PRIMARY KEY (id)
);