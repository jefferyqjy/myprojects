-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.10-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 friend 的数据库结构
CREATE DATABASE IF NOT EXISTS `friend` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `friend`;


-- 导出  表 friend.com_pro_member 结构
CREATE TABLE IF NOT EXISTS `com_pro_member` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `USERPASSWORD` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `GENDER` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `AGE` int(3) DEFAULT NULL COMMENT '年龄',
  `ADDRESS` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `UNIVERSITY` int(10) DEFAULT NULL COMMENT '大学',
  `STATUS` int(1) DEFAULT '0' COMMENT '状态',
  `PROVINCE` int(11) DEFAULT '0' COMMENT '省份',
  `CITY` int(11) DEFAULT '0' COMMENT '城市',
  `SUBJECT` int(11) DEFAULT '0' COMMENT '学科',
  `YEAR` int(4) DEFAULT '1900' COMMENT '入学年份',
  `STUNO` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '学号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- 正在导出表  friend.com_pro_member 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `com_pro_member` DISABLE KEYS */;
INSERT INTO `com_pro_member` (`ID`, `USERNAME`, `USERPASSWORD`, `GENDER`, `AGE`, `ADDRESS`, `UNIVERSITY`, `STATUS`, `PROVINCE`, `CITY`, `SUBJECT`, `YEAR`, `STUNO`) VALUES
	(1, 'seaboy', 'haibing', '男', 20, '南京', 1, 1, 0, 0, 0, 1900, ''),
	(2, 'tommy', 'haibing', '男', 29, '南通', 1, 1, 0, 0, 0, 1900, ''),
	(3, 'ann', 'haibing', '女', 29, '南通', 1, 1, 0, 0, 0, 1900, ''),
	(4, 'aaa', 'aaa', '男', 20, '北京大学', 3, 1, 0, 0, 0, 1900, ''),
	(5, 'bbb', 'bbb', '男', 333, '佳木斯', 1, 1, 0, 0, 0, 1900, ''),
	(6, '2001', '2001', '', 0, '', 1, 1, 0, 0, 0, 1900, '');
/*!40000 ALTER TABLE `com_pro_member` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
