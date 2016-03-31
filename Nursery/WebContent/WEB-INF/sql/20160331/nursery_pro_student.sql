CREATE DATABASE  IF NOT EXISTS `nursery` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `nursery`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: nursery
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `pro_student`
--

DROP TABLE IF EXISTS `pro_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_student` (
  `bodycard` varchar(18) NOT NULL,
  `name` varchar(10) NOT NULL,
  `birthday` datetime NOT NULL,
  `sex` varchar(2) NOT NULL,
  `nationality` varchar(10) DEFAULT '',
  `nativeplace` varchar(10) DEFAULT NULL,
  `school` varchar(50) NOT NULL,
  `in_date` datetime DEFAULT NULL,
  `out_date` datetime DEFAULT NULL,
  `father_name` varchar(10) DEFAULT NULL,
  `father_work_address` varchar(100) DEFAULT NULL,
  `mother_name` varchar(10) DEFAULT NULL,
  `mother_work_address` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`bodycard`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_student`
--

LOCK TABLES `pro_student` WRITE;
/*!40000 ALTER TABLE `pro_student` DISABLE KEYS */;
INSERT INTO `pro_student` VALUES ('1111111111111','学生1','1999-06-30 00:00:00','男','汉族','江苏省','ABC','2004-09-01 00:00:00','2006-07-30 00:00:00','家长1','AAA','家长2','BBB','15999999999','日常表现'),('32022222222','嘿嘿嘿','1999-06-30 00:00:00','女','汉族','江苏省','ABC','2004-09-01 00:00:00','2006-07-30 00:00:00','家长3','AAA11111111','家长4','BBB222222222','15999999999','日常表现f asdf asdf asd');
/*!40000 ALTER TABLE `pro_student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-01  2:01:23
