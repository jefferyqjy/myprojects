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
-- Table structure for table `pro_announce`
--

DROP TABLE IF EXISTS `pro_announce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_announce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_user_name` varchar(45) DEFAULT NULL COMMENT '教师用户名',
  `subject` varchar(45) DEFAULT NULL COMMENT '公共所属兴趣班',
  `content` varchar(45) DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_announce`
--

LOCK TABLES `pro_announce` WRITE;
/*!40000 ALTER TABLE `pro_announce` DISABLE KEYS */;
/*!40000 ALTER TABLE `pro_announce` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-01  2:01:22

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

DROP TABLE IF EXISTS `pro_student_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_student_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_bodycard` varchar(45) DEFAULT NULL COMMENT '学生身份证',
  `teacher_user_name` varchar(45) DEFAULT NULL COMMENT '评价老师用户名',
  `grade` int(1) DEFAULT NULL COMMENT '评价等级',
  `content` varchar(45) DEFAULT NULL COMMENT '评价内容',
  `create_time` datetime DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='学生评价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_student_comment`
--

LOCK TABLES `pro_student_comment` WRITE;
/*!40000 ALTER TABLE `pro_student_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `pro_student_comment` ENABLE KEYS */;
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

DROP TABLE IF EXISTS `pro_syllabus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_syllabus` (
  `id` int(11) NOT NULL,
  `morning_first` varchar(45) DEFAULT NULL COMMENT '上午第一节课',
  `morning_second` varchar(45) DEFAULT NULL COMMENT '上午第二节课',
  `aftrenoon_first` varchar(45) DEFAULT NULL COMMENT '下午第一节课',
  `afternoon_second` varchar(45) DEFAULT NULL COMMENT '下午第二节课',
  `afternoon-third` varchar(45) DEFAULT NULL COMMENT '下午第三节课',
  `syllabus_date` date DEFAULT NULL COMMENT '排课日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_syllabus`
--

LOCK TABLES `pro_syllabus` WRITE;
/*!40000 ALTER TABLE `pro_syllabus` DISABLE KEYS */;
/*!40000 ALTER TABLE `pro_syllabus` ENABLE KEYS */;
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

DROP TABLE IF EXISTS `pro_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_teacher` (
  `user_name` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `subject` int(11) NOT NULL,
  `sex` varchar(2) NOT NULL,
  PRIMARY KEY (`user_name`),
  CONSTRAINT `fk_user_name` FOREIGN KEY (`user_name`) REFERENCES `pro_user` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_teacher`
--

LOCK TABLES `pro_teacher` WRITE;
/*!40000 ALTER TABLE `pro_teacher` DISABLE KEYS */;
INSERT INTO `pro_teacher` VALUES ('wang','王老师',23,1,'女');
/*!40000 ALTER TABLE `pro_teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-01  2:01:22

DROP TABLE IF EXISTS `pro_teacher_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_teacher_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL COMMENT '评价内容',
  `teacher_user_name` varchar(45) DEFAULT NULL COMMENT '评价的老师名字',
  `create_time` datetime DEFAULT NULL COMMENT '评价时间',
  `grade` int(11) DEFAULT NULL COMMENT '教师评价评级：0：优；1：良：2：差',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='家长对老师的评价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_teacher_comment`
--

LOCK TABLES `pro_teacher_comment` WRITE;
/*!40000 ALTER TABLE `pro_teacher_comment` DISABLE KEYS */;
INSERT INTO `pro_teacher_comment` VALUES (1,'老师很好，很负责任，小孩很喜欢','wang','2016-03-31 18:32:12',0);
/*!40000 ALTER TABLE `pro_teacher_comment` ENABLE KEYS */;
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

DROP TABLE IF EXISTS `pro_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_user` (
  `user_name` varchar(15) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_user`
--

LOCK TABLES `pro_user` WRITE;
/*!40000 ALTER TABLE `pro_user` DISABLE KEYS */;
INSERT INTO `pro_user` VALUES ('admin','admin'),('family','admin'),('teacher','admin'),('wang','admin');
/*!40000 ALTER TABLE `pro_user` ENABLE KEYS */;
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

DROP TABLE IF EXISTS `pro_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pro_user_role` (
  `user_name` varchar(15) NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_user_role`
--

LOCK TABLES `pro_user_role` WRITE;
/*!40000 ALTER TABLE `pro_user_role` DISABLE KEYS */;
INSERT INTO `pro_user_role` VALUES ('admin','admin'),('family','family'),('teacher','teacher'),('wang','teacher');
/*!40000 ALTER TABLE `pro_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-01  2:01:24

DROP TABLE IF EXISTS `student_health`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_health` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `check_date` datetime NOT NULL,
  `bodycard` varchar(18) NOT NULL,
  `height` double(5,2) NOT NULL,
  `age` int(2) NOT NULL,
  `weight` double(5,2) NOT NULL,
  `left_sight` double(3,1) DEFAULT NULL,
  `right_sight` double(3,1) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_health`
--

LOCK TABLES `student_health` WRITE;
/*!40000 ALTER TABLE `student_health` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_health` ENABLE KEYS */;
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
