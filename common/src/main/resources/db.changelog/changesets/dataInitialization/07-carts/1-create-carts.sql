-- liquibase-formatted-sql
-- changeset kiran.khanal:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `carts`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    version INT NOT NULL DEFAULT 0,
    customer_id INT NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
