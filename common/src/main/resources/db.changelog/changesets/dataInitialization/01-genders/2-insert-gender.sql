-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM genders
INSERT INTO genders (gender,version)
VALUES
    ('MALE',0),
    ('FEMALE',0),
    ('OTHER',0);
