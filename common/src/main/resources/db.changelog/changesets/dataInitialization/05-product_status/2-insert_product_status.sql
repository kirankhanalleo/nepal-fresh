-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM product_status WHERE name = 'In Stock';
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM product_status WHERE name = 'Out of Stock';
INSERT INTO product_status (version, name, description) VALUES
        (1, 'In Stock', 'Product is in stock.'),
        (1, 'Out of Stock', 'Product is out of stock.');