CREATE SCHEMA `store` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `store`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`item_id`));

INSERT INTO `store`.`items` (`name`, `price`) VALUES ('Nokia 6233', '999.99');
INSERT INTO `store`.`items` (`name`, `price`) VALUES ('Iphone 8', '24500.00');
INSERT INTO `store`.`items` (`name`, `price`) VALUES ('Xiaomi Mi 9t', '8850.50');

CREATE TABLE `store`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`));

CREATE TABLE `store`.`orders_items` (
  `orders_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`orders_items_id`),
  INDEX `orders_items_orders_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `orders_items_items_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `orders_items_orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `store`.`orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `orders_items_items_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `store`.`items` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `store`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`));

ALTER TABLE `store`.`orders`
ADD COLUMN `user_id` INT NOT NULL AFTER `order_id`,
ADD INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `store`.`orders`
ADD CONSTRAINT `orders_users_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `store`.`users` (`user_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

INSERT INTO store.users (name, surname, login, password)
VALUES ('Bob', 'Marley', 'bobby', 'admin123');
INSERT INTO store.users (name, surname, login, password)
VALUES ('John', 'Smith', 'johny', 'user123');

INSERT INTO store.orders (user_id) VALUES ('1');
INSERT INTO store.orders (user_id) VALUES ('1');
INSERT INTO store.orders (user_id) VALUES ('2');

INSERT INTO store.items (name, price) VALUES ('Huawei P20', '12299.00');
INSERT INTO store.items (name, price) VALUES ('Motorola G7', '3499.00');
INSERT INTO store.items (name, price) VALUES ('Xiaomi redmi 7a', '2599.00');
INSERT INTO store.items (name, price) VALUES ('Doogee X2', '1899.50');
INSERT INTO store.items (name, price) VALUES ('Meizu C9', '1799.00');

INSERT INTO store.orders_items (order_id, item_id) VALUES ('1', '1');
INSERT INTO store.orders_items (order_id, item_id) VALUES ('1', '6');
INSERT INTO store.orders_items (order_id, item_id) VALUES ('2', '6');
INSERT INTO store.orders_items (order_id, item_id) VALUES ('3', '8');
INSERT INTO store.orders_items (order_id, item_id) VALUES ('3', '5');

ALTER TABLE `store`.`users`
ADD COLUMN `salt` VARCHAR(255) NOT NULL;

CREATE TABLE `store`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));
CREATE TABLE `store`.`user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_role_id`),
  INDEX `user_role.user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `user_role.role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_role.user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_role.role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `store`.`role` (`role_name`) VALUES ('ADMIN');
INSERT INTO `store`.`role` (`role_name`) VALUES ('USER');

INSERT INTO `store`.`user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `store`.`user_role` (`user_id`, `role_id`) VALUES ('2', '2');

CREATE TABLE `store`.`buckets` (
  `bucket_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`bucket_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk`
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`user_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)

CREATE TABLE `store`.`buckets_items` (
  `buckets_items_id` int(11) NOT NULL AUTO_INCREMENT,
  `bucket_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`buckets_items_id`),
  KEY `bucket_id_fk_idx` (`bucket_id`),
  KEY `item_id_fk_idx` (`item_id`),
  CONSTRAINT `bucket_id_fk`
  FOREIGN KEY (`bucket_id`)
  REFERENCES `buckets` (`bucket_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT `item_id_fk`
  FOREIGN KEY (`item_id`)
   REFERENCES `items` (`item_id`)
   ON DELETE CASCADE
   ON UPDATE CASCADE
)
