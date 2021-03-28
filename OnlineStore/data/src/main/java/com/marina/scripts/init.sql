SET GLOBAL time_zone = '+3:00';
use store;
select * from category;

INSERT INTO category (categoryName) VALUES ('Fruit'), ('Spice'), ('Vegetable');
DELETE FROM category;

select * from product;
DELETE FROM product;
TRUNCATE TABLE product;

CREATE TABLE IF NOT EXISTS `store`.`product` (
	`productId` int NOT NULL AUTO_INCREMENT,
    `name` text,
    `rating` int,
    `price` double,
    `categoryId` int,
    `quantity` int,
    PRIMARY KEY (`productId`)
);

ALTER TABLE `store`.`product` ADD FOREIGN KEY (`categoryId`) REFERENCES `store`.`category` (`categoryId`);