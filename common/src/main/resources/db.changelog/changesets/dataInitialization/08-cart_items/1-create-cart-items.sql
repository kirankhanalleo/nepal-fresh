-- liquibase-formatted-sql
-- changesets kiran.khanal:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `cart_items`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    version INT NOT NULL,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
