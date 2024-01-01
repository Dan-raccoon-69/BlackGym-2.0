-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: gym
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socios` (
  `fol` int NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) DEFAULT NULL,
  `Eda` varchar(2) DEFAULT NULL,
  `Tel` varchar(10) DEFAULT NULL,
  `CorElec` varchar(50) DEFAULT NULL,
  `Cal` varchar(50) DEFAULT NULL,
  `Num` int DEFAULT NULL,
  `Col` varchar(50) DEFAULT NULL,
  `Cp` varchar(6) DEFAULT NULL,
  `Ent` varchar(50) DEFAULT NULL,
  `Est` varchar(50) DEFAULT NULL,
  `NumPlan` int DEFAULT NULL,
  `Inp` date DEFAULT NULL,
  `FiP` date DEFAULT NULL,
  PRIMARY KEY (`fol`),
  KEY `NumPlan` (`NumPlan`),
  CONSTRAINT `socios_ibfk_1` FOREIGN KEY (`NumPlan`) REFERENCES `planes` (`NumPlan`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,'Juan Perez Martinez','25','5517182030','MARTo12@example.com','Sanchez',123,'Col olimpica','55040','Mexico','Mexico',1,'2023-11-01','2024-11-01'),(2,'Mariana Rodriguez Guerrero','23','5512658510','MARIA23@Gmail.com','Resina',123,'Col olimpica','565047','Mexico','Mexico',1,'2023-06-18','2024-06-18'),(3,'Avril Gonzale Martinez','21','5566887744','Avg12@yahoo.com','Te',0,'Col olimpica','55041','Mexico','Mexico',2,'2023-11-26','2023-12-26'),(4,'Paola Garza Rodriguez','25','5500112244','Paol4@outlook.com','Pedregal',0,'Col alpha','014589','Mexico','Mexico',1,'2023-06-26','2024-06-26'),(5,'Carlos Chagolla Hernandez','33','5517182032','CCHO32@outloo.com',NULL,0,NULL,NULL,NULL,NULL,2,'2023-11-11','2023-12-11'),(7,'Daniel Gustavo De La Cruz Bautista','21','5512453095','gustavodlc21@gmail.com',NULL,0,NULL,NULL,NULL,NULL,2,'2023-12-12','2024-01-12'),(8,'Mariana Garcia Mendes ','25','5520432104','maria.garcia@hotmail.com',NULL,0,NULL,NULL,NULL,NULL,2,'2023-12-10','2024-01-10'),(9,'Aura Daniela Perez Heredia','21','5513482301','auradan099@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,2,'2023-12-14','2024-01-14'),(10,'Miguel Angel Anastasio Culebro','21','5510203977','miguelasba@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,1,'2023-12-14','2024-12-14'),(11,'Miguel Angel Navarro Vargas','23','5544117435','miguelon479@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,1,'2023-12-14','2024-12-14');
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-15 14:16:30
