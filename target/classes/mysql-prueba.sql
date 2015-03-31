--CREATE DATABASE `daaexample`;

CREATE TABLE `daaexample`.`subject` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) DEFAULT NULL,
	`description` varchar(100) DEFAULT NULL,
	PRIMARY KEY (`id`)
);


INSERT INTO `daaexample`.`people` (`id`,`name`,`description`) VALUES (0,'Prueba1','Descripcion1');
