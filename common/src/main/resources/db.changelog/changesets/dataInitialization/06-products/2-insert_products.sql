-- liquibase formatted sql
--changeset kirankhanal:2
--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM products WHERE name = 'Ilameli Ghee';
INSERT INTO products (version, name, category_id, description, price, stock_quanity, status) VALUES
        (1, 'Ilameli Ghee', 1, 'Ilameli Ghee is a pure ghee made from the milk of cows from Ilam.', 1000, 15, 1);