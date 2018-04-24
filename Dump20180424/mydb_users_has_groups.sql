-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `users_has_groups`
--

DROP TABLE IF EXISTS `users_has_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_groups` (
  `Users_idUsers` int(11) NOT NULL,
  `Groups_GroupName` varchar(30) NOT NULL,
  PRIMARY KEY (`Users_idUsers`,`Groups_GroupName`),
  KEY `fk_Users_has_Groups_Groups1_idx` (`Groups_GroupName`),
  KEY `fk_Users_has_Groups_Users1_idx` (`Users_idUsers`),
  CONSTRAINT `fk_Users_has_Groups_Groups1` FOREIGN KEY (`Groups_GroupName`) REFERENCES `groups` (`groupname`),
  CONSTRAINT `fk_Users_has_Groups_Users1` FOREIGN KEY (`Users_idUsers`) REFERENCES `users` (`idusers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_groups`
--

LOCK TABLES `users_has_groups` WRITE;
/*!40000 ALTER TABLE `users_has_groups` DISABLE KEYS */;
INSERT INTO `users_has_groups` VALUES (1,'IT Department'),(9,'TestGroup2'),(13,'TestGroup2'),(32,'Accounting'),(32,'Human Resource'),(32,'IT Department'),(32,'TestGroup2'),(43,'Accounting'),(53,'Accounting'),(53,'Human Resource'),(53,'IT Department'),(53,'TestGroup2'),(54,'TestGroup2'),(72,'Human Resource'),(81,'Accounting'),(83,'Accounting');
/*!40000 ALTER TABLE `users_has_groups` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24  1:19:19
