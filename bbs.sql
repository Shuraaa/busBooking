DROP SCHEMA `bus_booking`;

CREATE SCHEMA IF NOT EXISTS `bus_booking`;

USE `bus_booking`;

-- -----------------------------------------------------------------

SELECT * FROM `role`;
SELECT * FROM `users`;
SELECT * FROM `routes`;
SELECT * FROM `buses`;
SELECT * FROM `seats`;
SELECT * FROM `tickets`;

-- -----------------------------------------------------------------

SELECT * FROM `v_user_role`;

-- Check if Can not add Foreign Key --------------------------------

SHOW ENGINE INNODB STATUS;

-- -----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `roles` (
`id`			INT unsigned NOT NULL,
`role_name`		VARCHAR(30) NOT NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `users` (
`id`			INT unsigned NOT NULL AUTO_INCREMENT,
`uname`			VARCHAR(100) NOT NULL,
`full_name`		VARCHAR(100) NOT NULL,
`upassword`		VARCHAR(50) NOT NULL,
`email`			VARCHAR(70),
`phone`			VARCHAR(20) NOT NULL,
`role_id`		INT unsigned NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `seats` (
`id`			INT unsigned NOT NULL AUTO_INCREMENT,
`name`		VARCHAR(20) NOT NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `buses` (
`id`			INT unsigned NOT NULL AUTO_INCREMENT,
`driver`		VARCHAR(50) NOT NULL,
`license`		TEXT,
`number_of_seat` INT NOT NULL DEFAULT 45,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `routes` (
`id`			INT unsigned NOT NULL AUTO_INCREMENT,
`route`			TEXT NOT NULL,
`start_point`	VARCHAR(100) NOT NULL,
`end_point`		VARCHAR(100) NOT NULL,
`start_date`	DATE NOT NULL,
`start_time`	TIME NOT NULL,
`price`			DOUBLE NOT NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tickets` (
`id`			INT unsigned NOT NULL,
`user_id`		INT unsigned NOT NULL,
`route_id`		INT unsigned NOT NULL,
`bus_id`		INT unsigned NOT NULL,
`seat_id`		INT unsigned NOT NULL,
`booking_date`	DATE,
`booking_status`	VARCHAR(50) DEFAULT 'NONE',
PRIMARY KEY (`id`, `seat_id`, `bus_id`, `route_id`),
CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) 	REFERENCES `users` 	(`id`) 	ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_seat_id` FOREIGN KEY (`seat_id`) 	REFERENCES `seats` 	(`id`) 	ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_bus_id` 	FOREIGN KEY (`bus_id`) 		REFERENCES `buses` 	(`id`) 	ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `fk_route_id` FOREIGN KEY (`route_id`) 	REFERENCES `routes` (`id`) 	ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE VIEW `v_user_role` AS
SELECT u.`id`, u.`uname`, u.`full_name`, u.`upassword`, r.`role_name`, u.`phone`, u.`email`
FROM `users` u JOIN `roles` r ON u.role_id=r.id;

-- -----------------------------------------------------------------

INSERT INTO `roles` VALUES(0, 'ADMIN'),(1, 'TICKET_SELLER'),(2, 'CUSTOMER');

INSERT INTO `users` (`uname`, `full_name`, `upassword`, `phone`, `email`, `role_id`) VALUES 
('ATV123', 'Tran Van A', '123456', '0123456789', 'mail@mail.com', 0), 
('BTV123', 'Tran Van B', '123456', '0123456789', 'mail1@mail.com', 0),
('CTV123', 'Tran Van C', '123456', '0123456789', 'mail2@mail.com', 1), 
('DTV123', 'Tran Van D', '123456', '0123456789', 'mail3@mail.com', 1),
('ETV123', 'Tran Van E', '123456', '0123456789', 'mail4@mail.com', 2), 
('FTV123', 'Tran Van F', '123456', '0123456789', 'mail5@mail.com', 2),
('GTV123', 'Tran Van G', '123456', '0123456789', 'mail6@mail.com', 2), 
('HTV123', 'Tran Van H', '123456', '0123456789', 'mail7@mail.com', 2),
('ITV123', 'Tran Van I', '123456', '0123456789', 'mail8@mail.com', 2), 
('JTV123', 'Tran Van J', '123456', '0123456789', 'mail9@mail.com', 2),
('KTV123', 'Tran Van K', '123456', '0123456789', 'mail10@mail.com', 2), 
('LTV123', 'Tran Van L', '123456', '0123456789', 'mail11@mail.com', 2),
('MTV123', 'Tran Van M', '123456', '0123456789', 'mail9@mail.com', 2), 
('NTV123', 'Tran Van N', '123456', '0123456789', 'mail10@mail.com', 2),
('OTV123', 'Tran Van O', '123456', '0123456789', 'mail11@mail.com', 2), 
('PTV123', 'Tran Van P', '123456', '0123456789', 'mail9@mail.com', 2),
('QTV123', 'Tran Van Q', '123456', '0123456789', 'mail10@mail.com', 2), 
('RTV123', 'Tran Van R', '123456', '0123456789', 'mail11@mail.com', 2),
('STV123', 'Tran Van S', '123456', '0123456789', 'mail9@mail.com', 2), 
('YTV123', 'Tran Van Y', '123456', '0123456789', 'mail10@mail.com', 2),
('UTV123', 'Tran Van U', '123456', '0123456789', 'mail11@mail.com', 2), 
('WTV123', 'Tran Van W', '123456', '0123456789', 'mail12@mail.com', 1);

-- -----------------------------------------------------------------

INSERT INTO `routes` (`route`, `start_point`, `end_point`, `price`,`start_date`, `start_time`) VALUES
('Linh Xuan - FPT Software - Suoi Tien', 'Linh Xuan', 'Suoi Tien', 10000, '2018-12-01', '02:20:50'),
('Linh Xuan - FPT Software - Suoi Tien', 'Linh Xuan', 'Suoi Tien', 10000, '2018-12-12', '02:20:50'),
('Di An - Linh Xuan - Cho Thu Duc - Coop Mart Thu Duc','Di An', 'Coop Mart Thu Duc', 15000, '2018-05-20', '02:20:50'), 
('Big C Di An - Linh Xuan - Cau vuot Binh Phuoc','Big C Di An','Cau vuot Binh Phuoc', 20000, '2018-06-13', '02:20:50'),
('Cho Thu Duc - Nga tu Binh Thai - Dai Lo 3','Cho Thu Duc', 'Dai Lo 3', 7000, '2018-02-01', '02:20:50'), 
('Dai hoc Nong Lam - Khu Cong Nghe Cao - Suoi Tien','Dai hoc Nong Lam','Suoi Tien', 12000, '2018-12-10', '02:20:50');

-- -----------------------------------------------------------------

INSERT INTO `buses` (`driver`, `license`) VALUES 
('Vo Thanh Phuc A', ''), ('Vo Thanh Phuc', ''), ('Tran Quang Dang', ''), ('Le Thanh Nghi', ''), 
('Pham Viet Dao', ''), ('Vo Thanh Phuong', ''), ('Tran Ba Van', ''), ('Le Minh Man', ''), ('Le Thanh Minh', ''), 
('Ngo Tri Dung', ''), ('Le Minh Quoc', ''), ('Le Huy Hoang', ''), ('Nguyen Van Quang', ''), ('Hoang Van Khoa', ''), 
('Hoang Van Dang', ''), ('Nguyen Van Toan', ''), ('Le Minh Toan', ''), ('Nguyen Thanh Tu', ''), ('Vo Hoang Trung','');

-- INSERT SEATS DATA FOR BUS 1 -------------------------------------

INSERT INTO `seats` (`name`) VALUES ('A001'),('A002'),('A003'),('A004'),('A005'),('A006'),('A007'),('A008'),
('A009'),('A010'),('A011'),('A012'),('A013'),('A014'),('A015'),('A016'),('A017'),('A018'),('A019'),('A020'),
('A021'),('A022'),('A023'),('A024'),('A025'),('A026'),('A027'),('A028'),('A029'),('A030'),('A031'),('A032'),
('A033'),('A034'),('A035'),('A036'),('A037'),('A038'),('A039'),('A040'),('A041'),('A042'),('A043'),('A044'),('A045');

-- -----------------------------------------------------------------

INSERT INTO `tickets` (`id`, `user_id`, `booking_date`, `seat_id`, `bus_id`, `route_id`) VALUES 
(101, 1, '2008-11-11', 1, 1, 1), (102, 2, '2008-11-11', 1, 1, 3), (103, 3, '2008-11-11', 1, 1, 2), 
(104, 1, '2008-11-11', 1, 1, 4), (105, 1, '2008-11-11', 1, 1, 2), (106, 1, '2008-11-11', 1, 1, 2),
(107, 1, '2008-11-11', 1, 1, 4), (108, 1, '2008-11-11', 1, 1, 2), (109, 1, '2008-11-11', 1, 1, 2),
(110, 1, '2008-11-11', 1, 1, 4), (111, 1, '2008-11-11', 1, 1, 2), (112, 1, '2008-11-11', 1, 1, 2),
(113, 1, '2008-11-11', 1, 1, 4), (114, 1, '2008-11-11', 1, 1, 2), (115, 1, '2008-11-11', 1, 1, 2),
(116, 1, '2008-11-11', 1, 1, 4), (117, 1, '2008-11-11', 1, 1, 2), (118, 1, '2008-11-11', 1, 1, 2),
(119, 1, '2008-11-11', 1, 1, 4), (120, 1, '2008-11-11', 1, 1, 2), (121, 1, '2008-11-11', 1, 1, 2),
(122, 1, '2008-11-11', 1, 1, 4), (123, 1, '2008-11-11', 1, 1, 2), (124, 1, '2008-11-11', 1, 1, 2),
(125, 1, '2008-11-11', 1, 1, 4), (126, 1, '2008-11-11', 1, 1, 2), (127, 1, '2008-11-11', 1, 1, 2),
(128, 1, '2008-11-11', 1, 1, 4), (129, 1, '2008-11-11', 1, 1, 2), (130, 1, '2008-11-11', 1, 1, 2),
(131, 1, '2008-11-11', 1, 1, 4), (132, 1, '2008-11-11', 1, 1, 2), (133, 1, '2008-11-11', 1, 1, 2),
(134, 1, '2008-11-11', 1, 1, 4), (135, 1, '2008-11-11', 1, 1, 2), (136, 1, '2008-11-11', 1, 1, 2),
(137, 1, '2008-11-11', 1, 1, 4), (138, 1, '2008-11-11', 1, 1, 2), (139, 1, '2008-11-11', 1, 1, 2),
(140, 1, '2008-11-11', 1, 1, 4), (142, 1, '2008-11-11', 1, 1, 2), (143, 1, '2008-11-11', 1, 1, 2);

-- -----------------------------------------------------------------
