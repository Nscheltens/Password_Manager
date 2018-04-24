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
-- Table structure for table `groups_has_applications`
--

DROP TABLE IF EXISTS `groups_has_applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_has_applications` (
  `Groups_GroupName` varchar(30) NOT NULL,
  `Applications_AppName` varchar(45) NOT NULL,
  PRIMARY KEY (`Groups_GroupName`,`Applications_AppName`),
  KEY `fk_Groups_has_Applications_Applications1_idx` (`Applications_AppName`),
  KEY `fk_Groups_has_Applications_Groups1_idx` (`Groups_GroupName`),
  CONSTRAINT `fk_Groups_has_Applications_Applications1` FOREIGN KEY (`Applications_AppName`) REFERENCES `applications` (`appname`),
  CONSTRAINT `fk_Groups_has_Applications_Groups1` FOREIGN KEY (`Groups_GroupName`) REFERENCES `groups` (`groupname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_has_applications`
--

LOCK TABLES `groups_has_applications` WRITE;
/*!40000 ALTER TABLE `groups_has_applications` DISABLE KEYS */;
INSERT INTO `groups_has_applications` VALUES ('Accounting','QuickBooks'),('Human Resource','HR HelpDesk'),('Human Resource','QuickBooks'),('IT Department','MySQL Workshop'),('IT Department','Oracle VM VirtualBox'),('IT Department','Visual Studio 2017'),('TestGroup2','HR HelpDesk'),('TestGroup2','Visual Studio 2017');
/*!40000 ALTER TABLE `groups_has_applications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24  1:19:18
