-- DROP DATABASE IF EXISTS fwrp;

-- CREATE DATABASE fwrp;

-- USE fwrp;

create table UserType
(
    id int NOT NULL AUTO_INCREMENT PRIMARY key,
    name varchar(255) NOT NULL
);

drop table user;
create table User
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(30) NOT null UNIQUE,
    password varchar(30) NOT NULL,
    email varchar(30) NOT null UNIQUE,
    typeId int NOT NULL,
    balance decimal(10, 2) NOT NULL,
    isSubscribe int,
    createUserId int,
    createDate DATETIME NOT NULL,
    modUserId int,
    modDate DATETIME,
    FOREIGN KEY (typeId) REFERENCES UserType(id)

);

drop table Item;
create table Item
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT null,
    description varchar(255),
    quantity int NOT NULL,
    expirationDate DATETIME NOT NULL,
    isDonate int,
    price decimal(10, 2) NOT NULL,
    discount int,
    isSurplus int,
    createUserId int NOT NULL,
    createDate DATETIME NOT NULL,
    modUserId int,
    modDate DATETIME

);

create table OrderStatus
(
    id int NOT NULL AUTO_INCREMENT PRIMARY key,
    name varchar(255) NOT NULL
);

drop table Orders;
create table Orders
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    itemId int NOT NULL,
    quantity int NOT NULL,
    address varchar(255) NOT null,
    statusId int not null,
    createUserId int NOT NULL,
    createDate DATETIME NOT NULL,
    modUserId int,
    modDate DATETIME,
    FOREIGN KEY (itemId) REFERENCES Item(id),
    FOREIGN KEY (statusId) REFERENCES OrderStatus(id)
);

INSERT INTO UserType (id, name) values (1, 'Retailer'),
                                       (2, 'Charitable Organization'),
                                       (3, 'Consumer');
-- select * from UserType;

INSERT INTO User (username, password, email, typeId, balance, isSubscribe, createUserId, createDate)
values('retailer1', 'retailer1', 'retailer1@gmail.com', 1, 1000, 0, -1, now()),
      ('charity1', 'charity1', 'charity1@gmail.com', 2, 1000, 1, -1, now()),
      ('consumer1', 'consumer1', 'consumer1@gmail.com', 3, 1000, 1, -1, now());
-- select * from User;

INSERT INTO Item (title, description, quantity, expirationDate, isDonate, price, discount, isSurplus, createUserId, createDate)
values('bread', 'bread', 100, DATE_ADD(NOW(), INTERVAL 4 istSurplusServlet.java:39DAY), null, 5.5, 4, 0, 1, now()),
      ('peach', 'peach', 100, DATE_ADD(NOW(), INTERVAL 3 DAY), null, 4.5, 4, 0, 1, now()),
      ('sausage', 'sausage', 100, DATE_ADD(NOW(), INTERVAL 100 DAY), null, 5.5, 4, 0, 1, now()),
      ('apple', 'apple', 100, DATE_ADD(NOW(), INTERVAL -3 DAY), 0, 3.18, 2, 0, 1, now());

--  select * from Item;
--  SELECT * FROM Item where (isDonate != 1 or isDonate is NULL) and expirationDate < now();
-- SELECT * FROM Item where createUserId = 1 and expirationDate between DATE_ADD(NOW(), INTERVAL -7 DAY) and now();
-- SELECT * FROM Item where (isDonate != 1 or isDonate is NULL) and expirationDate < now() and disCount > 0;
-- SELECT * FROM Item where createUserId = 1 and id = 4;

INSERT INTO OrderStatus (id, name) values (1, 'Pending Approve'),
                                          (2, 'Approved');

INSERT INTO orders
(itemId, quantity, address, statusId, createUserId, createDate)
VALUES(1, 10,  'xxx', 2, 3, now()),
      (2, 10,  'xxx', 2, 2, now()),
      (2, 10,  'xxx', 2, 3, now());

-- select * from orders;
