-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM product_categories WHERE name = 'Dairy'
INSERT INTO product_categories (version, name, description) VALUES
(0, 'Dairy', 'Dairy Products');
