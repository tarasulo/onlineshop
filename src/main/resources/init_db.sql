CREATE SCHEMA `store` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `store`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`item_id`));

INSERT INTO `store`.`items` (`name`, `price`) VALUES ('Nokia 6233', '999.99');
INSERT INTO `store`.`items` (`name`, `price`) VALUES ('Iphone 8', '24500.00');
INSERT INTO `store`.`items` (`name`, `price`) VALUES ('Xiaomi Mi 9t', '8850.50');
