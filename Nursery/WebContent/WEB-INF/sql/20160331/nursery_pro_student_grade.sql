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
-- Table structure for table `pro_student_grade`
--

DROP TABLE IF EXISTS `pro_student_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_student_grade` (
  `id` int(11) NOT NULL,
  `student_bodycard` varchar(45) DEFAULT NULL COMMENT '学生身份证',
  `subject` varchar(45) DEFAULT NULL COMMENT '科目',
  `grade` varchar(45) DEFAULT NULL COMMENT '成绩',
  `year` int(4) DEFAULT NULL COMMENT '年份',
  `term` int(1) DEFAULT NULL COMMENT '学期：0：上半学年；1：下半学年',
  `student_name` varchar(45) DEFAULT NULL COMMENT '学生姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='学生成绩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_student_grade`
--

LOCK TABLES `pro_student_grade` WRITE;
/*!40000 ALTER TABLE `pro_student_grade` DISABLE KEYS */;
INSERT INTO `pro_student_grade` VALUES (1,'1111111111111','1','1',2016,1,'学生1');
/*!40000 ALTER TABLE `pro_student_grade` ENABLE KEYS */;
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
