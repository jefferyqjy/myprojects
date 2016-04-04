CREATE DATABASE  IF NOT EXISTS `db_qx` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_qx`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: db_qx
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admID` int(11) NOT NULL,
  `admname` varchar(45) DEFAULT NULL,
  `admpwd` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `admphone` varchar(45) DEFAULT NULL,
  `admadd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`admID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1115020121,'石子琦','123456','男','18249942479','广西北海市'),(1115020122,'admin','admin','男','18212345678','广西南宁市');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arrange`
--

DROP TABLE IF EXISTS `arrange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrange` (
  `aID` int(11) NOT NULL,
  `empID` int(11) DEFAULT NULL,
  `yID` int(11) DEFAULT NULL,
  `anpaitime` varchar(45) DEFAULT NULL,
  `wangongtime` varchar(45) DEFAULT NULL,
  `partID` varchar(45) DEFAULT NULL,
  `partname` varchar(45) DEFAULT NULL,
  `checknum` double(45,0) DEFAULT NULL,
  PRIMARY KEY (`aID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrange`
--

LOCK TABLES `arrange` WRITE;
/*!40000 ALTER TABLE `arrange` DISABLE KEYS */;
INSERT INTO `arrange` VALUES (1,1,1,'2015-05-05','2015-05-08','P1001','空气滤清器',1),(2,2,3,'2015-05-05','2015-05-07','P1001','空气滤清器',2),(3,6,5,'2015-05-05','2015-05-06','P1010','倒车灯',2),(4,8,2,'2015-05-05','2015-05-05','P1001','空气滤清器',3),(5,2,6,'2015-05-06','2015-05-08','P1007','挡风镜',2),(6,7,9,'2015-05-10','2015-05-15','P1015','转速器',2),(7,3,10,'2015-05-10','2015-05-10','P1004','电感束',3),(8,7,11,'2015-05-10','2015-05-14','P1007','挡风镜',2),(9,5,15,'2015-05-12','2015-05-14','P1014','车顶操纵箱',1),(10,2,21,'2015-05-14','2015-05-20','P1017','制动盘罩',1),(11,5,12,'2015-05-14','2015-05-14','P1006','车窗',1),(12,3,17,'2015-05-14','2015-05-15','P1011','车门锁',2),(13,2,16,'2015-05-14','2015-05-16','P1012','铰链',2),(14,6,8,'2015-05-14','2015-05-15','P1004','电感束',3),(15,4,22,'2015-05-15','2015-05-17','P1004','电感束',1);
/*!40000 ALTER TABLE `arrange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `kindID` int(11) NOT NULL,
  `kindname` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`kindID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'机能1','空气滤清器、空气流量计、电感束、驱动轴、自动变速装置、过滤器'),(2,'外面1','车窗、挡风镜、轮胎、雾雨刮水器、车门框、刮水器'),(3,'外面2','前支柱、中支柱、远距离控制、行李车门、倒车灯'),(4,'室内1','内开把手、车门锁、密封条、铰链、门饰板'),(5,'室内2','辅助把手、照明灯、汽缸盖衬、车顶操纵箱'),(6,'室内3','可变电阻、转速器、速度计、自动空调、变速杆、转向盘，烟灰缸'),(7,'悬架1','悬架支柱、螺旋弹簧、制动盘罩');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkouts`
--

DROP TABLE IF EXISTS `checkouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkouts` (
  `cID` int(11) NOT NULL,
  `aID` int(11) DEFAULT NULL,
  `partprice` double(45,0) DEFAULT NULL,
  `repaircost` double(45,0) DEFAULT NULL,
  `xiaofei` double(45,0) DEFAULT NULL,
  `checkoutsdate` varchar(45) DEFAULT NULL,
  `beizhu` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkouts`
--

LOCK TABLES `checkouts` WRITE;
/*!40000 ALTER TABLE `checkouts` DISABLE KEYS */;
INSERT INTO `checkouts` VALUES (1,1,300,200,500,'2015-05-06','1'),(2,9,200,300,500,'2015-05-08','0'),(3,2,500,300,800,'2015-05-14','0'),(4,12,500,200,700,'2015-05-14','0'),(5,8,400,400,800,'2015-05-14','1'),(6,7,1500,500,2000,'2015-05-15','0'),(7,13,700,300,1000,'2015-05-14','0'),(8,6,1300,400,1700,'2015-05-14','0'),(9,5,1200,500,1700,'2015-05-14','0'),(10,4,1500,500,2000,'2015-05-14','0'),(11,14,1200,300,1500,'2015-05-16','0'),(12,3,400,400,800,'2015-05-14','1');
/*!40000 ALTER TABLE `checkouts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cusID` int(11) NOT NULL,
  `cusname` varchar(45) DEFAULT NULL,
  `cuspwd` varchar(45) DEFAULT NULL,
  `cusphone` varchar(45) DEFAULT NULL,
  `question` varchar(45) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  `cusadd` varchar(45) DEFAULT NULL,
  `vip` int(11) DEFAULT '0',
  `zongxiaofei` decimal(12,2) DEFAULT '0.00',
  PRIMARY KEY (`cusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'张杰','123456','8701359','我的出生地是？','广西南宁市','广西南宁市兴宁区长岗燕子岭4号',0,2000.00),(2,'张三丰','123456','0997-8701651','我就读过的大学名称是？','广西师范学院','广西南宁市兴宁区长岗燕子岭4号',1,4000.00),(3,'陈瑞','123456','8601235','我的生日是？','19920416','广西北海市北部湾路1号',0,1600.00),(4,'李鹏','123456','8501846','我最喜欢的休闲运动是？','篮球','广西南宁市青秀区合兴路3号',0,0.00),(5,'王大雷','123456','15812345678','干元冬','变形金刚','广西南宁市青秀区合兴路4号',0,1700.00),(6,'杨方','123456','15907792120','我的出生地是？','南宁','广西南宁市青秀区青秀区滨湖路1号',0,0.00),(7,'黄子轩','123456','15207711234','我最喜欢的历史人物是？','李云龙','广西南宁市星光大道17号 ',1,3000.00),(8,'邹宏星','123456','18249487562','我最喜欢的历史人物是？','李云龙','广西南宁市兴宁区',0,0.00),(9,'万羽','123456','15785463258','我最喜欢的历史人物是？','李云龙','广西南宁市',0,0.00),(10,'朱盼晴 ','123456','15245871250','我最喜欢的历史人物是？','李云龙','广西北海市银海区',0,0.00),(11,'傅佳','123456','18259862154','我最喜欢的历史人物是？','李云龙','广西北海市铁山港区',0,0.00),(12,'龙琦','123456','18249942479','我的出生地是？','北海','广西北海市',0,700.00),(13,'奚宛菡','123456','15824562014','我的出生地是？','广东','广东省',0,0.00),(14,'庞谷梦','123456','15745625102','我就读过的大学名称是？','北京大学','北京市',0,0.00),(15,'干元冬','123456','15484526520','我最喜欢的休闲运动是？','羽毛球','广西玉林市',0,1000.00);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `empID` int(11) NOT NULL,
  `empname` varchar(45) DEFAULT NULL,
  `emppwd` varchar(45) DEFAULT NULL,
  `empadd` varchar(45) DEFAULT NULL,
  `worktime` varchar(45) DEFAULT NULL,
  `empphone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`empID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'石破天','123456','北海市银海区4号','2013-11-13','0779-8888888'),(2,'张天涯','123456','朝阳广场','2014-11-03','18249925852'),(3,'赵小黑','3','北海市银海区4号','2014-10-06','0779-0123456'),(4,'王达','1','梧州市刘堡村','2014-11-02','15212532542'),(5,'赵小海','1','南宁市青秀区8号','2012-11-05','0771-1536423'),(6,'张无忌','1','南宁市西乡塘5号','2014-04-08','156233'),(7,'陈宇','2','广西南宁市','2014-12-30','15212345678'),(8,'李元霸','2','广东省广州市','2015-01-06','13312345678');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation` (
  `eID` int(11) NOT NULL,
  `cID` int(11) DEFAULT NULL,
  `evaluatime` varchar(45) DEFAULT NULL,
  `evaluation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`eID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (1,6,'2015-05-14','好'),(2,1,'2015-05-14','很好'),(3,10,'2015-05-14','一般'),(4,11,'2015-05-14','好'),(5,3,'2015-05-14','很好'),(6,5,'2015-05-14','一般'),(7,9,'2015-05-14','差'),(8,7,'2015-05-14','极差');
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part` (
  `partID` varchar(45) NOT NULL DEFAULT '',
  `partname` varchar(45) DEFAULT NULL,
  `partstandard` varchar(45) DEFAULT NULL,
  `partpackaging` varchar(45) DEFAULT NULL,
  `kindID` int(11) DEFAULT NULL,
  PRIMARY KEY (`partID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES ('P1001','空气滤清器','1*盒','20盒/箱',1),('P1002','悬架支柱','3*个','10个/箱',7),('P1003','空气流量计','1*个','10个/盒',1),('P1004','电感束','1*个','20个/盒',1),('P1005','驱动轴','1*个','15个/箱',1),('P1006','车窗','1*扇','1扇/箱',2),('P1007','挡风镜','1*个','20个/盒',2),('P1008','刮水器','1*个','30个/箱',2),('P1009','前支柱','1*个','1个*盒',3),('P1010','倒车灯','1*个','10个/盒',3),('P1011','车门锁','1*个','50个/箱',4),('P1012','铰链','1*条','20条/盒',4),('P1013','照明灯','1*个','10个/盒',5),('P1014','车顶操纵箱','1*个','5个/箱',5),('P1015','转速器','1*个','10个/盒',6),('P1016','转向盘','1*个','1个/盒',6),('P1017','制动盘罩','1*个','1个/箱',7);
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partkc`
--

DROP TABLE IF EXISTS `partkc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partkc` (
  `partkcID` int(11) NOT NULL,
  `partID` varchar(45) DEFAULT NULL,
  `partname` varchar(45) DEFAULT NULL,
  `partstandard` varchar(45) DEFAULT NULL,
  `partpackaging` varchar(45) DEFAULT NULL,
  `partkcnum` double(45,0) DEFAULT NULL,
  PRIMARY KEY (`partkcID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partkc`
--

LOCK TABLES `partkc` WRITE;
/*!40000 ALTER TABLE `partkc` DISABLE KEYS */;
INSERT INTO `partkc` VALUES (1,'P1001','空气滤清器','1*盒','20盒/箱',193),(2,'P1003','空气流量计','1*个','10个/盒',270),(3,'P1004','电感束','1*个','20个/盒',25),(4,'P1006','车窗','1*扇','1扇/箱',19),(5,'P1007','挡风镜','1*个','20个/盒',16),(6,'P1005','驱动轴','1*个','15个/箱',150),(7,'P1009','前支柱','1*个','1个*盒',24),(8,'P1002','悬架支柱','3*个','10个/箱',93),(9,'P1017','制动盘罩','1*个','1个/箱',223),(10,'P1013','照明灯','1*个','10个/盒',0),(11,'P1015','转速器','1*个','10个/盒',10),(12,'P1016','转向盘','1*个','1个/盒',95),(13,'P1010','倒车灯','1*个','10个/盒',18),(14,'P1012','铰链','1*条','20条/盒',23),(15,'P1014','车顶操纵箱','1*个','5个/箱',189),(16,'P1011','车门锁','1*个','50个/箱',128);
/*!40000 ALTER TABLE `partkc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partrk`
--

DROP TABLE IF EXISTS `partrk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partrk` (
  `partrkID` varchar(45) NOT NULL,
  `empID` int(11) DEFAULT NULL,
  `supID` int(11) DEFAULT NULL,
  `partrktotal` double(45,0) DEFAULT NULL,
  `rkdate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`partrkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partrk`
--

LOCK TABLES `partrk` WRITE;
/*!40000 ALTER TABLE `partrk` DISABLE KEYS */;
INSERT INTO `partrk` VALUES ('RK1',1,1,20,'2015-05-01'),('RK10',1,1,10000,'2015-05-15'),('RK2',2,2,45750,'2015-05-03'),('RK3',3,7,1410,'2015-05-04'),('RK4',5,6,15120,'2015-05-07'),('RK5',6,4,25400,'2015-05-08'),('RK6',8,3,4800,'2015-05-10'),('RK7',5,5,59340,'2015-05-11'),('RK8',4,5,37500,'2015-05-13'),('RK9',5,5,16000,'2015-05-14');
/*!40000 ALTER TABLE `partrk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partrkmingxi`
--

DROP TABLE IF EXISTS `partrkmingxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partrkmingxi` (
  `partrkID` varchar(45) DEFAULT NULL,
  `partID` varchar(45) DEFAULT NULL,
  `partrknum` double(45,0) DEFAULT NULL,
  `partrkprice` double(45,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partrkmingxi`
--

LOCK TABLES `partrkmingxi` WRITE;
/*!40000 ALTER TABLE `partrkmingxi` DISABLE KEYS */;
INSERT INTO `partrkmingxi` VALUES ('RK1','P1001',20,1),('RK2','P1001',100,150),('RK2','P1003',200,100),('RK2','P1004',50,180),('RK2','P1006',35,50),('RK3','P1007',30,20),('RK3','P1005',20,15),('RK3','P1009',34,15),('RK4','P1002',123,120),('RK4','P1017',24,15),('RK5','P1015',45,120),('RK5','P1016',100,200),('RK6','P1010',30,80),('RK6','P1012',40,60),('RK7','P1014',200,150),('RK7','P1003',82,120),('RK7','P1005',130,150),('RK8','P1017',200,150),('RK8','P1011',50,150),('RK9','P1001',100,160),('RK10','P1011',100,100);
/*!40000 ALTER TABLE `partrkmingxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partss`
--

DROP TABLE IF EXISTS `partss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partss` (
  `partssID` varchar(45) NOT NULL DEFAULT '',
  `empID` int(11) DEFAULT NULL,
  `cusID` int(11) DEFAULT NULL,
  `partsstotal` double(45,0) DEFAULT NULL,
  `ssdate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`partssID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partss`
--

LOCK TABLES `partss` WRITE;
/*!40000 ALTER TABLE `partss` DISABLE KEYS */;
INSERT INTO `partss` VALUES ('SS1',1,1,100,'2015-05-01'),('SS2',1,1,5500,'2015-05-02'),('SS3',2,2,7550,'2015-05-05'),('SS4',3,11,6000,'2015-05-07'),('SS5',5,8,3200,'2015-05-08'),('SS6',4,8,6000,'2015-05-09'),('SS7',7,5,3800,'2015-05-12'),('SS8',7,7,2250,'2015-05-14');
/*!40000 ALTER TABLE `partss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partssmingxi`
--

DROP TABLE IF EXISTS `partssmingxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partssmingxi` (
  `partssID` varchar(45) DEFAULT NULL,
  `partID` varchar(45) DEFAULT NULL,
  `partssnum` double(45,0) DEFAULT NULL,
  `partssprice` double(45,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partssmingxi`
--

LOCK TABLES `partssmingxi` WRITE;
/*!40000 ALTER TABLE `partssmingxi` DISABLE KEYS */;
INSERT INTO `partssmingxi` VALUES ('SS1','P1001',1,100),('SS2','P1001',20,200),('SS2','P1009',10,150),('SS3','P1007',10,200),('SS3','P1006',5,120),('SS3','P1003',10,300),('SS3','P1004',15,130),('SS4','P1015',30,150),('SS4','P1006',10,150),('SS5','P1011',20,100),('SS5','P1012',15,80),('SS6','P1002',30,200),('SS7','P1010',10,150),('SS7','P1014',10,200),('SS7','P1015',3,100),('SS8','P1003',2,150),('SS8','P1004',3,150),('SS8','P1016',5,300);
/*!40000 ALTER TABLE `partssmingxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supID` int(11) NOT NULL,
  `supname` varchar(45) DEFAULT NULL,
  `supadd` varchar(45) DEFAULT NULL,
  `supphone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'北海零件厂','北海','18248762549'),(2,'湖南零件销售','湖南','8462531'),(3,'广州零件销售','广州','8750123'),(4,'北京零件销售','北京','8762066'),(5,'南宁零件经销商','南宁市','18294605212'),(6,'南京零件销售','南京','15492350125'),(7,'广东零件制造厂','广东省','9562102');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wage`
--

DROP TABLE IF EXISTS `wage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wage` (
  `wID` int(11) NOT NULL,
  `empID` int(11) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `basepay` float DEFAULT NULL,
  `commission` float DEFAULT NULL,
  `salary` float DEFAULT NULL,
  PRIMARY KEY (`wID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wage`
--

LOCK TABLES `wage` WRITE;
/*!40000 ALTER TABLE `wage` DISABLE KEYS */;
INSERT INTO `wage` VALUES (1,2,'2015-02',1000,100,1100),(2,1,'2015-02',2500,500,3000),(3,3,'2015-02',3000,600,3600),(4,4,'2015-04',5000,2000,7000);
/*!40000 ALTER TABLE `wage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yuyue`
--

DROP TABLE IF EXISTS `yuyue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yuyue` (
  `yID` int(11) NOT NULL,
  `cusID` int(11) DEFAULT NULL,
  `cusname` varchar(45) DEFAULT NULL,
  `cusphone` varchar(45) DEFAULT NULL,
  `carnum` varchar(45) DEFAULT NULL,
  `carmoder` varchar(45) DEFAULT NULL,
  `repairitem` varchar(45) DEFAULT NULL,
  `yuyuetime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`yID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yuyue`
--

LOCK TABLES `yuyue` WRITE;
/*!40000 ALTER TABLE `yuyue` DISABLE KEYS */;
INSERT INTO `yuyue` VALUES (1,2,'张三丰','8701651','桂A12345','奥迪TT','空气滤清器故障','2015-05-05'),(2,2,'张三丰','8701651','桂A23456','奥迪Q5','空气滤清器故障','2015-05-05'),(3,3,'陈瑞','8601235','桂E12345','锋范','空气滤清器故障','2015-05-05'),(4,1,'张杰','8701359','桂E12346','飞度','倒车灯损坏','2015-05-15'),(5,7,'黄子轩','15207711234','豫E16888','长安CX20','倒车灯损坏','2015-05-05'),(6,5,'王大雷','15812345678','粤A88888','长丰飞腾','更换挡风镜','2015-05-06'),(7,6,'杨方','15907792120','京H99999','大众CC','车门锁损坏','2015-05-07'),(8,2,'张三丰','0997-8701651','疆A11111','奔驰','电感束故障','2015-05-08'),(9,7,'黄子轩','15207711234','广C66666','奥迪','转速器故障','2015-05-10'),(10,1,'张杰','8701359','桂B66666','红旗I5','电光束损坏','2015-05-10'),(11,3,'陈瑞','8601235','桂A12345','红旗I6','挡风镜故障','2015-05-10'),(12,8,'邹宏星','18249487562','广A23456','奔驰s350','车窗','2015-05-11'),(13,3,'陈瑞','8601235','广A888888','东风日产启辰T70','驱动轴','2015-05-11'),(14,5,'王大雷','15812345678','豫C55555','东风日产启辰R50X','前支柱损坏','2015-05-12'),(15,7,'黄子轩','15207711234','桂B22222','大众速腾','车顶操纵箱损坏','2015-05-12'),(16,15,'干元冬','15484526520','辽A123456','上海大众POLO','铰链问题','2015-05-12'),(17,12,'龙琦','18249942479','沪A55555','海格H5C','车门锁','2015-05-12'),(18,9,'万羽','15785463258','陕A45678','龙威','驱动轴出故障','2015-05-14'),(19,13,'奚宛菡','15824562014','云C15625','御骏','空气流量计不正常','2015-05-14'),(20,14,'庞谷梦','15745625102','藏D25365','飞腾','车窗','2015-05-14'),(21,10,'朱盼晴 ','15245871250','黑F55555','猎豹CS10','制动盘罩故障','2015-05-14'),(22,2,'张三丰','0997-8701651','京C88888','奥迪A1','电感束故障','2015-05-15');
/*!40000 ALTER TABLE `yuyue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-15 20:19:25
