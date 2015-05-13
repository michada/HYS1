-- MySQL dump 10.13  Distrib 5.5.40, for Win32 (x86)
--
-- Host: localhost    Database: hys1
-- ------------------------------------------------------
-- Server version	5.5.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO category (`id`, `name`) VALUES (1, "Book");
INSERT INTO category (`id`, `name`) VALUES (2, "Film");
INSERT INTO category (`id`, `name`) VALUES (3, "TV series");
INSERT INTO category (`id`, `name`) VALUES (4, "Sport Event");
INSERT INTO category (`id`, `name`) VALUES (5, "Theater");
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `description` text,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `visibility` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_juon0oksnanuurs0929u52ngl` (`category_id`),
  KEY `FK_gcwj9ni1erqlwoj1f0ua4ha2s` (`creator_id`),
  KEY `FK_i5kyf6e4q4rish7glpbcr9khb` (`location_id`),
  CONSTRAINT `FK_i5kyf6e4q4rish7glpbcr9khb` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_gcwj9ni1erqlwoj1f0ua4ha2s` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_juon0oksnanuurs0929u52ngl` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO event (`id`, `date`, `description`, `status`, `title`, `visibility`, `category_id`, `creator_id`, `location_id`) 
VALUES (1,'2015-01-06 16:35:07','Evento cinematográfico sobre las mejores películas de terror hechas en la historia del cine.','PROGRAMMED','Retrospectiva sobre el cine de terror','PRIVATE',2,1,1),
(2,'2015-01-06 16:35:07','Semana cultural sobre el cine fantástico y de terror de San Sebastián. Se contará con invitados de la talla de Jack Ferguson o Charlie Sheen','CANCELLED','XXVI Seman de Cine Fantástic y de Terror','PUBLIC',2,1,2),
(3,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de los Juegos del Hambre. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Juegos del Hambre','PRIVATE',2,3,3),
(4,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Magic Meeting, el gran evento temático de Harry Potter!!! Pedido y esperado por muchos fans','PROGRAMMED','Magic Meeting (Harry Potter)','PUBLIC',1,1,2),
(5,'2015-01-06 16:35:07','El Fantasma de la Ópera es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El fantasma de la Ópera','PUBLIC',5,3,2),
(6,'2015-01-06 16:35:07','El próximo miércoles quedamos en el bar Plaza para ver el partido de final de Champions Manchester - Chelsea','PROGRAMMED','Partido Manchester - Chelsea','PUBLIC',4,3,2),
(7,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Juego de Tronos en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de','PUBLIC',4,3,2),

(8,'2015-01-06 16:35:07','Semana cultural sobre el cine de ciencia ficción de San Sebastián. Se contará con invitados de la talla de Jack Ferguson o Charlie Sheen','PROGRAMMED','XXVI Seman de Cine de Ciencia Ficción','PRIVATE',2,1,2),
(9,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de la nueva película de Harry Potter. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Harry Potter','PRIVATE',2,3,3),
(10,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Magic Meeting, el gran evento temático de Harry Potter!!! Pedido y esperado por muchos fans','PROGRAMMED','Magic Meeting (Harry Potter)','PUBLIC',1,1,2),
(11,'2015-01-06 16:35:07','El Anillo de Oro es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El Anillo de Oro','PUBLIC',5,3,2),
(12,'2015-01-06 16:35:07','El próximo miércoles quedamos en el bar Fio de liño para ver el partido de final de Mundial Holanda - Francia','CANCELLED','Partido Holanda - Francia','PUBLIC',4,3,2),
(13,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Castle en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de Castle','PUBLIC',3,3,2),

(14,'2015-01-06 16:35:07','Semana cultural sobre el cine de comedia de San Sebastián. Se contará con invitados de la talla de Antonio Banderas o Nicolas Cage','COMPLETED','XXVI Seman de Cine Fantástic y de Terror','PRIVATE',2,1,2),
(15,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de Solo en Casa. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Solo en Casa','PRIVATE',2,3,3),
(16,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Chachi Meeting, el gran evento temático de Hannah Montana !!! Pedido y esperado por muchos fans','CANCELLED','Chachi Meeting (Hannah Montana)','PRIVATE',3,1,2),
(17,'2015-01-06 16:35:07','El Pequeño Enrique es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El Pequeño Enrique','PUBLIC',5,3,2),
(18,'2015-01-06 16:35:07','El próximo miércoles quedamos en el bar Juanito para ver el partido de final del Campeonato Manchester - Chelsea','PROGRAMMED','Partido Manchester - Chelsea','PUBLIC',4,3,2),
(19,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Ana y los 7 en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de Ana y los 7','PUBLIC',3,3,2),

(20,'2015-01-06 16:35:07','Semana cultural sobre el cine de acción de Ourense. Se contará con invitados de la talla de Brad Pitt','COMPLETED','XXVI Seman de Cine de Acción','PUBLIC',2,1,2),
(21,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de A todo Gas. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Two Fast Two Furious','PUBLIC',2,3,3),
(22,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Frodo Meeting, el gran evento temático del Señor de los anillos!!! Pedido y esperado por muchos fans','PROGRAMMED','Frodo Meeting (El señor de los anillos)','PUBLIC',1,1,2),
(23,'2015-01-06 16:35:07','El Cascanueces es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','El Cascanueces','PUBLIC',5,3,2),
(24,'2015-01-06 16:35:07','El próximo domingo quedamos en el bar Plaza para ver el partido de final de Copa Depor - Celta','PROGRAMMED','Partido Depor - Celta','PRIVATE',4,3,2),
(25,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie Anatomía de Grey en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de Anatomía de Grey','PUBLIC',3 ,3,4),

(26,'2015-01-06 16:35:07','Semana cultural sobre el cine de suspense de Madrid. Se contará con invitados de la talla de Richard Gere','PROGRAMMED','XXVI Seman de Cine de Suspense','PRIVATE',2,1,2),
(27,'2015-01-06 16:35:07','Quedada para comentar el nuevo trailer de Saw. Será en la cafetería de la Universidad. Por favor, vean el trailer antes de venir al evento','PROGRAMMED','Quedada para hablar sobre Saw','PRIVATE',2,3,3),
(28,'2015-01-06 16:35:07','Desde FanCon Eventos tenemos el agrado de invitarlos a Spider Meeting, el gran evento temático de Spiderman!!! Pedido y esperado por muchos fans','PROGRAMMED','Spider Meeting (Spiderman)','PRIVATE',3,1,2),
(29,'2015-01-06 16:35:07','Blancanieves es una obra teatral constantemente representada y renovada. Es un orgullo invitar a todos los usuarios de esta aplicación a ver dicha obra en el teatro Principal','COMPLETED','Blancanieves','PUBLIC',5,3,2),
(30,'2015-01-06 16:35:07','El próximo sábado quedamos en el bar Auriense para ver el partido de final de Champions Manchester - Chelsea','PROGRAMMED','Partido Manchester - Chelsea','PUBLIC',4,3,2),
(31,'2015-01-06 16:35:07','La próxima semana se realizará una representación del capítulo 5 de la serie House en la plaza de Callao. Los asistentes tienen que venir disfrazados de su personaje favorito','PROGRAMMED','Representación de capítulos de House','PRIVATE',3,3,2);/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_assistants`
--

DROP TABLE IF EXISTS `event_assistants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_assistants` (
  `eventsToAttend_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `FK_ie59yco19n2ml5f0xgag4p0m9` (`user_id`),
  KEY `FK_id262kad3kjxq9p21aea3viqr` (`eventsToAttend_id`),
  CONSTRAINT `FK_id262kad3kjxq9p21aea3viqr` FOREIGN KEY (`eventsToAttend_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_ie59yco19n2ml5f0xgag4p0m9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_assistants`
--

LOCK TABLES `event_assistants` WRITE;
/*!40000 ALTER TABLE `event_assistants` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_assistants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_moderators`
--

DROP TABLE IF EXISTS `event_moderators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_moderators` (
  `eventsModerated_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `FK_a5tx73kk66fkgj50c6s1utcga` (`user_id`),
  KEY `FK_8vv6yxmnrlrawb0fd8j5w1yvl` (`eventsModerated_id`),
  CONSTRAINT `FK_8vv6yxmnrlrawb0fd8j5w1yvl` FOREIGN KEY (`eventsModerated_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_a5tx73kk66fkgj50c6s1utcga` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_moderators`
--

LOCK TABLES `event_moderators` WRITE;
/*!40000 ALTER TABLE `event_moderators` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_moderators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO location (`id`, `latitude`, `longitude`) VALUES (1, 40.712, -74.059);
INSERT INTO location (`id`, `latitude`, `longitude`) VALUES (2, 35.712, -88.059);
INSERT INTO location (`id`, `latitude`, `longitude`) VALUES (3, 26.902, -81.254);
INSERT INTO location (`id`, `latitude`, `longitude`) VALUES (4, 42.344, -7.855);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1f43ca92e76ab25c659f0b29432e2f13776e044165ace94d718a3cfb20fb1c20','Ray'),(2,'6b36f3357f1b05d7e4754c4b868d73fa4a8269dc7eb2bf317bfb9b56b2df2c3c','Derek'),(3,'7c6e1bf16286b2bcb2790eee9d42a406f0de86615436d85baf86efa3b7d9eb20','Wylie'),(4,'e8fbf44c43373ad0691e03cae826ea4e04e2117076228fe76d7318fd94a4ae66','Moses'),(5,'0b802ee61d8451c6c8a726d6f1c7e58c054d63994fd9a8c53a85ed6e3cb5ba2a','Buckminster'),(6,'6a132d36b16f91cea834d30576207719c2fc70cee15403add8bc550c04bd999e','Macon'),(7,'3aed202dc7d798311d6ec07f992ecf3dd472972fd9fee174dcb3582c7483bbe6','Edan'),(8,'61a1d3bdf6653544be9579821e40a5a376743e1f846421bad02b3ec49f8fd22a','Lyle'),(9,'cb115a7ab849366a9c7c20accdc447ade3b9194d421d82ff687cf4dcc690cabc','Kane'),(10,'52d7d8604bf71e968bf07d468c7d394bff7cb0bb142fd4da7a85dd6f8056a940','Omar');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-13 10:42:55
