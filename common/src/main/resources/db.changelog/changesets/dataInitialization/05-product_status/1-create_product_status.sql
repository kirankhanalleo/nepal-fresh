-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS product_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    version INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);