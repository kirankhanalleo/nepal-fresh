-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `customers`
(
    id                              BIGINT AUTO_INCREMENT NOT NULL,
    version                         BIGINT                NOT NULL,
    full_name                       VARCHAR(255)          NOT NULL,
    password                        VARCHAR(255)          NULL,
    username                        VARCHAR(255)          NULL,
    is_active                       BIT(1)                NULL,
    email                           VARCHAR(255)          NOT NULL,
    mobile_number                   VARCHAR(255)          NULL,
    date_of_birth                   datetime              NULL,
    gender                          BIGINT                NULL,
    address                         VARCHAR(255)          NULL,
    status                          BIGINT                NOT NULL,
    registered_date                 datetime              NULL,
    password_changed_date           datetime              NULL,
    last_logged_in_time             datetime              NULL,
    wrong_password_attempt_count    INT                   NULL,
    profile_picture                 VARCHAR(255)          NULL,
    otp_auth_secret                 VARCHAR(255)          NULL,
    two_factor_enabled              BIT(1)                NULL,
    wrong_oto_auth_attempt_count    INT                   NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
    );


--changeset kirankhanal:2
--precondition-on-fail:MARK_RAN
--preconditions
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'customers' AND constraint_name = 'uc_customer_email'
ALTER TABLE customers
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

--changeset kirankhanal:3
--precondition-on-fail:MARK_RAN
--preconditions
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'customers' AND constraint_name = 'uc_customer_mobile_number'
ALTER TABLE customers
    ADD CONSTRAINT uc_customer_mobile_number UNIQUE (mobile_number);

--changeset kirankhanal:4
--precondition-on-fail:MARK_RAN
--preconditions
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'customers' AND constraint_name = 'FK_CUSTOMER_ON_STATUS'
ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMER_ON_STATUS FOREIGN KEY (status) REFERENCES status (id);

--changeset kirankhanal:5
--precondition-on-fail:MARK_RAN
--preconditions
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'customers' AND constraint_name = 'FK_CUSTOMER_ON_GENDER'
ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMER_ON_GENDER FOREIGN KEY (gender) REFERENCES genders (id);





