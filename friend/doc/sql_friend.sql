-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.32 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2014-03-26 00:10:03
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for friend
DROP DATABASE IF EXISTS `friend`;
CREATE DATABASE IF NOT EXISTS `friend` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `friend`;


-- Dumping structure for table friend.com_pro_boke
DROP TABLE IF EXISTS `com_pro_boke`;
CREATE TABLE IF NOT EXISTS `com_pro_boke` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT` text COLLATE utf8_bin,
  `USER_ID` int(11) NOT NULL,
  `CRE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_boke: ~3 rows (approximately)
/*!40000 ALTER TABLE `com_pro_boke` DISABLE KEYS */;
INSERT INTO `com_pro_boke` (`ID`, `TITLE`, `CONTENT`, `USER_ID`, `CRE_DATE`) VALUES
	(1, '新的开始', '<font face="Arial, Verdana" size="2">新的开始</font>', 1, '2014-03-25 09:08:55'),
	(2, '新的开始2', '<font face="Arial, Verdana" size="2">新的开始</font>', 1, '2014-03-25 09:09:13'),
	(3, '我爱你', '<font face="Arial, Verdana" size="2">我爱你</font>', 1, '2014-03-25 09:21:35'),
	(4, '新的开始', '<font face="Arial, Verdana" size="2" color="#cc9933"><b>新的开始新的开始 &nbsp;</b></font>', 5, '2014-03-25 23:44:02');
/*!40000 ALTER TABLE `com_pro_boke` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_boke_message
DROP TABLE IF EXISTS `com_pro_boke_message`;
CREATE TABLE IF NOT EXISTS `com_pro_boke_message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOKE_ID` int(11) NOT NULL,
  `MESSAGE` text COLLATE utf8_bin,
  `USERID` int(11) DEFAULT NULL,
  `USERNAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CRE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_boke_message: ~5 rows (approximately)
/*!40000 ALTER TABLE `com_pro_boke_message` DISABLE KEYS */;
INSERT INTO `com_pro_boke_message` (`ID`, `BOKE_ID`, `MESSAGE`, `USERID`, `USERNAME`, `CRE_DATE`) VALUES
	(1, 2, 'g&nbsp;', 1, 'seaboy', '2014-03-25 09:15:53'),
	(2, 2, 'a撒旦发生地方', 1, 'seaboy', '2014-03-25 09:16:49'),
	(3, 2, 'a上帝发誓', 1, 'seaboy', '2014-03-25 09:21:19'),
	(4, 3, 'afd&nbsp;', 3, 'ann', '2014-03-25 21:56:05'),
	(5, 2, 'rtreyert', 3, 'ann', '2014-03-25 21:57:29'),
	(6, 4, '不错啊', 5, 'bbb', '2014-03-25 23:44:14'),
	(7, 4, '案发生的发送到', 4, 'aaa', '2014-03-25 23:49:06');
/*!40000 ALTER TABLE `com_pro_boke_message` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_city
DROP TABLE IF EXISTS `com_pro_city`;
CREATE TABLE IF NOT EXISTS `com_pro_city` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_city: ~2 rows (approximately)
/*!40000 ALTER TABLE `com_pro_city` DISABLE KEYS */;
INSERT INTO `com_pro_city` (`ID`, `NAME`) VALUES
	(1, '南京'),
	(2, '南通'),
	(3, '北京市');
/*!40000 ALTER TABLE `com_pro_city` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_friend_group
DROP TABLE IF EXISTS `com_pro_friend_group`;
CREATE TABLE IF NOT EXISTS `com_pro_friend_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `CRE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_friend_group: ~6 rows (approximately)
/*!40000 ALTER TABLE `com_pro_friend_group` DISABLE KEYS */;
INSERT INTO `com_pro_friend_group` (`ID`, `NAME`, `USER_ID`, `CRE_DATE`) VALUES
	(4, '大学同学', 1, '2014-03-25 14:27:32'),
	(5, '同事', 1, '2014-03-25 14:31:54'),
	(6, '高中同学', 1, '2014-03-25 14:56:46'),
	(7, '顶顶顶', 1, '2014-03-25 14:57:49'),
	(8, '同事', 3, '2014-03-25 16:55:07'),
	(9, '同学', 3, '2014-03-25 20:57:33'),
	(10, '好友', 5, '2014-03-25 23:46:24'),
	(11, '朋友', 5, '2014-03-25 23:47:07'),
	(12, '好友', 4, '2014-03-25 23:48:45');
/*!40000 ALTER TABLE `com_pro_friend_group` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_friend_group_user
DROP TABLE IF EXISTS `com_pro_friend_group_user`;
CREATE TABLE IF NOT EXISTS `com_pro_friend_group_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `FRIEND_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_friend_group_user: ~4 rows (approximately)
/*!40000 ALTER TABLE `com_pro_friend_group_user` DISABLE KEYS */;
INSERT INTO `com_pro_friend_group_user` (`ID`, `GROUP_ID`, `USER_ID`, `FRIEND_ID`) VALUES
	(1, 4, 1, 2),
	(2, 5, 1, 3),
	(7, 8, 3, 2),
	(8, 9, 3, 1),
	(9, 11, 5, 1),
	(11, 10, 5, 3),
	(12, 10, 5, 4),
	(13, 12, 4, 5);
/*!40000 ALTER TABLE `com_pro_friend_group_user` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_interest
DROP TABLE IF EXISTS `com_pro_interest`;
CREATE TABLE IF NOT EXISTS `com_pro_interest` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_interest: ~3 rows (approximately)
/*!40000 ALTER TABLE `com_pro_interest` DISABLE KEYS */;
INSERT INTO `com_pro_interest` (`ID`, `NAME`) VALUES
	(1, '看书'),
	(2, '钓鱼'),
	(3, '煮饭');
/*!40000 ALTER TABLE `com_pro_interest` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_member
DROP TABLE IF EXISTS `com_pro_member`;
CREATE TABLE IF NOT EXISTS `com_pro_member` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USERPASSWORD` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `GENDER` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `AGE` int(3) DEFAULT NULL,
  `ADDRESS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UNIVERSITY` int(10) DEFAULT NULL,
  `STATUS` int(1) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_member: ~3 rows (approximately)
/*!40000 ALTER TABLE `com_pro_member` DISABLE KEYS */;
INSERT INTO `com_pro_member` (`ID`, `USERNAME`, `USERPASSWORD`, `GENDER`, `AGE`, `ADDRESS`, `UNIVERSITY`, `STATUS`) VALUES
	(1, 'seaboy', 'haibing', '男', 20, '南京', 1, 1),
	(2, 'tommy', 'haibing', '男', 29, '南通', 1, 1),
	(3, 'ann', 'haibing', '女', 29, '南通', 1, 1),
	(4, 'aaa', 'aaa', '男', 20, '北京大学', 3, 1),
	(5, 'bbb', 'bbb', '男', 333, '佳木斯', 1, 1);
/*!40000 ALTER TABLE `com_pro_member` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_member_interest
DROP TABLE IF EXISTS `com_pro_member_interest`;
CREATE TABLE IF NOT EXISTS `com_pro_member_interest` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MEMBER_ID` int(11) NOT NULL,
  `INTEREST_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_member_interest: ~3 rows (approximately)
/*!40000 ALTER TABLE `com_pro_member_interest` DISABLE KEYS */;
INSERT INTO `com_pro_member_interest` (`ID`, `MEMBER_ID`, `INTEREST_ID`) VALUES
	(1, 1, 2),
	(2, 1, 3),
	(3, 2, 2),
	(6, 4, 1),
	(7, 4, 2),
	(9, 5, 1);
/*!40000 ALTER TABLE `com_pro_member_interest` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_message
DROP TABLE IF EXISTS `com_pro_message`;
CREATE TABLE IF NOT EXISTS `com_pro_message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FROM_USER_ID` int(11) NOT NULL,
  `TO_USER_ID` int(11) NOT NULL,
  `MESSAGE` text COLLATE utf8_bin,
  `CRE_DATE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `STATUS` int(1) DEFAULT '0',
  `FROM_USER_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_message: ~0 rows (approximately)
/*!40000 ALTER TABLE `com_pro_message` DISABLE KEYS */;
INSERT INTO `com_pro_message` (`ID`, `FROM_USER_ID`, `TO_USER_ID`, `MESSAGE`, `CRE_DATE`, `STATUS`, `FROM_USER_NAME`) VALUES
	(1, 4, 5, '啊啊', '2014-03-25 23:49:47', 1, 'aaa'),
	(2, 5, 4, '操', '2014-03-25 23:50:01', 1, 'bbb'),
	(3, 4, 5, 'nnn', '2014-03-25 23:50:16', 1, 'aaa'),
	(4, 4, 5, 'adsf a', '2014-03-25 23:50:33', 1, 'aaa'),
	(5, 4, 5, 'asdf', '2014-03-25 23:50:44', 1, 'aaa'),
	(6, 5, 4, '阿斯顿发送到', '2014-03-25 23:51:01', 1, 'bbb'),
	(7, 4, 5, 'sfaa', '2014-03-25 23:51:05', 1, 'aaa'),
	(8, 4, 5, '可以把刷新的时候改成10秒 或者 20 秒 现在默认是5秒', '2014-03-25 23:51:56', 1, 'aaa');
/*!40000 ALTER TABLE `com_pro_message` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_photo
DROP TABLE IF EXISTS `com_pro_photo`;
CREATE TABLE IF NOT EXISTS `com_pro_photo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHOTOBOX_ID` int(11) NOT NULL,
  `PATH` text COLLATE utf8_bin,
  `CRE_DATE` datetime DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_photo: ~29 rows (approximately)
/*!40000 ALTER TABLE `com_pro_photo` DISABLE KEYS */;
INSERT INTO `com_pro_photo` (`ID`, `PHOTOBOX_ID`, `PATH`, `CRE_DATE`, `NAME`) VALUES
	(33, 1, '\\friend\\\\resource\\20140325\\b7846488-4962-41ca-a228-35b4e459c23f.gif', '2014-03-25 11:52:31', 'psb.gif'),
	(34, 1, '\\friend\\\\resource\\20140325\\9a18be6e-5e11-4b4b-bf88-ccb4e7194af6.jpg', '2014-03-25 11:52:31', 'psb.jpg'),
	(35, 1, '\\friend\\\\resource\\20140325\\b9d12ef0-b14e-4cef-9c60-f96ac98522a3.png', '2014-03-25 11:52:32', 'Untitled.png'),
	(36, 1, '\\friend\\\\resource\\20140325\\006b5ed1-bce2-41f3-8e90-bbe7b55b1eab.jpg', '2014-03-25 11:52:32', '男道士.jpg'),
	(37, 1, '\\friend\\\\resource\\20140325\\ce377471-5148-427e-8fd8-9d82fd28f561.jpg', '2014-03-25 11:52:32', '男法师.jpg'),
	(38, 1, '\\friend\\\\resource\\20140325\\9a0b6a10-cefa-4755-9373-99023ff5c37b.jpg', '2014-03-25 11:52:32', '男武士.jpg'),
	(39, 1, '\\friend\\\\resource\\20140325\\919dfcf4-e397-4388-b918-2d7132898336.jpg', '2014-03-25 11:52:32', '女道士.jpg'),
	(40, 1, '\\friend\\\\resource\\20140325\\0aa087a3-21e1-4827-9f4a-edcebcdbbf25.jpg', '2014-03-25 11:52:33', '女法师.jpg'),
	(41, 1, '\\friend\\\\resource\\20140325\\f4bac0f5-e1fb-49ad-8309-a6fc994833dc.jpg', '2014-03-25 11:52:33', '女武士.jpg'),
	(42, 2, '\\friend\\\\resource\\20140325\\129df990-a2bc-4151-b1b4-0aa5afc3a6d2.png', '2014-03-25 21:58:47', '1.png'),
	(43, 2, '\\friend\\\\resource\\20140325\\fcbf0d68-7f41-4a7a-848b-a611c9c3d467.png', '2014-03-25 21:58:48', '2.png'),
	(44, 2, '\\friend\\\\resource\\20140325\\27b290f3-4afe-450b-b617-05df6e4f583f.png', '2014-03-25 21:58:49', '3.png'),
	(45, 2, '\\friend\\\\resource\\20140325\\44def2ad-6b0a-454d-b833-b5ab274ac773.png', '2014-03-25 21:58:49', '4.png'),
	(46, 2, '\\friend\\\\resource\\20140325\\6e23a145-4436-4508-955e-c390cce6f028.png', '2014-03-25 21:58:49', '5.png'),
	(47, 2, '\\friend\\\\resource\\20140325\\cb5422d3-c82c-4e0b-8d67-505326677aa5.png', '2014-03-25 21:58:49', '6.png'),
	(48, 2, '\\friend\\\\resource\\20140325\\169049dc-ddcb-4129-83cf-34041df1dbf2.png', '2014-03-25 21:58:50', '7.png'),
	(49, 2, '\\friend\\\\resource\\20140325\\bcc462b2-256c-4591-97de-99d8611d40cc.png', '2014-03-25 21:58:50', '8.png'),
	(50, 2, '\\friend\\\\resource\\20140325\\37e25ec7-bfc8-4163-b74a-02a7ac63f906.png', '2014-03-25 21:58:50', '9.png'),
	(51, 2, '\\friend\\\\resource\\20140325\\2221da9c-68b1-4d11-aaee-08fe27aa8e13.png', '2014-03-25 21:58:51', '10.png'),
	(52, 2, '\\friend\\\\resource\\20140325\\44fd9d49-d954-4fb6-a181-a2431005bfb3.png', '2014-03-25 21:58:52', '11.png'),
	(53, 2, '\\friend\\\\resource\\20140325\\320a4899-66f2-45d9-b36d-7dd7b99d6c50.png', '2014-03-25 21:58:53', '12.png'),
	(54, 2, '\\friend\\\\resource\\20140325\\f6479772-873c-43ae-b1bc-2152e55d4d08.png', '2014-03-25 21:58:54', '13.png'),
	(55, 2, '\\friend\\\\resource\\20140325\\bb0230a2-0474-4d57-8b3a-14c65998379f.png', '2014-03-25 21:58:55', '14.png'),
	(56, 2, '\\friend\\\\resource\\20140325\\a0136e6b-3b21-4990-8558-556c78902afc.png', '2014-03-25 21:58:56', '15.png'),
	(57, 2, '\\friend\\\\resource\\20140325\\ce88e887-b1ab-43a3-8530-5f5432a0eee9.png', '2014-03-25 21:58:57', '16.png'),
	(58, 2, '\\friend\\\\resource\\20140325\\8b3df308-4e7e-4d63-9c73-804d97605d4e.png', '2014-03-25 21:58:58', '17.png'),
	(59, 2, '\\friend\\\\resource\\20140325\\083b3703-2e36-4a66-89e1-9827e36a4f87.png', '2014-03-25 21:58:59', '18.png'),
	(60, 2, '\\friend\\\\resource\\20140325\\8d5b69b5-c8ce-424d-85c7-d226f6f5a65b.png', '2014-03-25 21:59:00', '19.png'),
	(61, 2, '\\friend\\\\resource\\20140325\\04a652bb-a0f9-4771-80c4-5f5750caec1b.png', '2014-03-25 21:59:01', '20.png'),
	(62, 3, '\\friend\\\\resource\\20140325\\332387a5-7abc-4e44-99f7-fa1daea4a6e0.png', '2014-03-25 23:44:54', '1.png'),
	(63, 3, '\\friend\\\\resource\\20140325\\f4838f5a-3429-4e9d-b7fb-abc890c18106.png', '2014-03-25 23:44:55', '2.png'),
	(64, 3, '\\friend\\\\resource\\20140325\\3ca3320b-8fa4-4737-902e-38ff362b1cfd.png', '2014-03-25 23:44:56', '3.png'),
	(65, 3, '\\friend\\\\resource\\20140325\\db6a8bb1-8aee-469d-9d47-be23f33f4cf3.png', '2014-03-25 23:44:57', '4.png'),
	(66, 3, '\\friend\\\\resource\\20140325\\03f79b8e-8343-4764-a9f3-ec185603d7c1.png', '2014-03-25 23:44:57', '5.png'),
	(67, 3, '\\friend\\\\resource\\20140325\\5b2f9b0c-2a6b-463b-b80c-1482bf4ce26b.png', '2014-03-25 23:44:57', '6.png'),
	(68, 3, '\\friend\\\\resource\\20140325\\244659cd-5e32-46be-bbd9-f1eaae7d2f38.png', '2014-03-25 23:44:57', '7.png'),
	(69, 3, '\\friend\\\\resource\\20140325\\0771f3cc-5c09-4530-9e1b-d6f78de4fedf.png', '2014-03-25 23:44:57', '8.png'),
	(70, 3, '\\friend\\\\resource\\20140325\\81da09da-43a5-45d2-88e2-7190aad053bf.png', '2014-03-25 23:44:58', '9.png'),
	(71, 3, '\\friend\\\\resource\\20140325\\13770f38-46bb-43e4-8042-733cb0a619c8.png', '2014-03-25 23:44:59', '10.png'),
	(72, 3, '\\friend\\\\resource\\20140325\\39ce18f9-6780-479c-89c1-659f9d28c47f.png', '2014-03-25 23:45:00', '11.png'),
	(73, 3, '\\friend\\\\resource\\20140325\\ab256cb3-2b90-4c4c-aca8-d9045ff6f5fa.png', '2014-03-25 23:45:01', '12.png'),
	(74, 3, '\\friend\\\\resource\\20140325\\25a93bbf-8f6d-487a-a9ea-951edb9cd9f8.png', '2014-03-25 23:45:02', '13.png'),
	(75, 3, '\\friend\\\\resource\\20140325\\061809a1-3fff-4624-bbd0-7072337e8b1a.png', '2014-03-25 23:45:03', '14.png'),
	(76, 3, '\\friend\\\\resource\\20140325\\b21e0957-2e10-4daf-8afe-5904335fe122.png', '2014-03-25 23:45:04', '15.png'),
	(77, 3, '\\friend\\\\resource\\20140325\\20021cf8-cdce-4771-9dd6-1ce78cb8aee4.png', '2014-03-25 23:45:05', '16.png'),
	(78, 3, '\\friend\\\\resource\\20140325\\a2768e24-6b13-4228-b7fa-28199b272c9f.png', '2014-03-25 23:45:06', '17.png'),
	(79, 3, '\\friend\\\\resource\\20140325\\e9cd953a-d83a-4d52-978f-204c437a21f8.png', '2014-03-25 23:45:07', '18.png'),
	(80, 3, '\\friend\\\\resource\\20140325\\ac383b44-525e-40fa-9171-c1cae4731212.png', '2014-03-25 23:45:08', '19.png'),
	(81, 3, '\\friend\\\\resource\\20140325\\08d5c282-db6a-425a-981b-10701e0563f1.png', '2014-03-25 23:45:09', '20.png'),
	(82, 3, '\\friend\\\\resource\\20140325\\d4bfc2cb-a8da-4351-8b1e-3c17ac32edb2.jpg', '2014-03-25 23:45:29', '73bOOOPIC8e_202.jpg');
/*!40000 ALTER TABLE `com_pro_photo` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_photobox
DROP TABLE IF EXISTS `com_pro_photobox`;
CREATE TABLE IF NOT EXISTS `com_pro_photobox` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `CRE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_photobox: ~2 rows (approximately)
/*!40000 ALTER TABLE `com_pro_photobox` DISABLE KEYS */;
INSERT INTO `com_pro_photobox` (`ID`, `NAME`, `USER_ID`, `CRE_DATE`) VALUES
	(1, '一日游', 1, '2014-03-25 09:25:56'),
	(2, 'adsf', 3, '2014-03-25 21:58:34'),
	(3, '南通合集', 5, '2014-03-25 23:44:31');
/*!40000 ALTER TABLE `com_pro_photobox` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_photobox_message
DROP TABLE IF EXISTS `com_pro_photobox_message`;
CREATE TABLE IF NOT EXISTS `com_pro_photobox_message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHOTOBOX_ID` int(11) NOT NULL,
  `MESSAGE` text COLLATE utf8_bin,
  `USERID` int(11) DEFAULT NULL,
  `USERNAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CRE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_photobox_message: ~3 rows (approximately)
/*!40000 ALTER TABLE `com_pro_photobox_message` DISABLE KEYS */;
INSERT INTO `com_pro_photobox_message` (`ID`, `PHOTOBOX_ID`, `MESSAGE`, `USERID`, `USERNAME`, `CRE_DATE`) VALUES
	(4, 1, 'd阿芳啊', 1, 'seaboy', '2014-03-25 10:01:40'),
	(5, 1, 'asdfasd', 3, 'ann', '2014-03-25 21:56:21'),
	(6, 2, 'haokan', 3, 'ann', '2014-03-25 21:59:24'),
	(7, 3, '好图片啊', 5, 'bbb', '2014-03-25 23:45:47'),
	(8, 3, '飒飒大法师敌法是短发', 4, 'aaa', '2014-03-25 23:49:28');
/*!40000 ALTER TABLE `com_pro_photobox_message` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_role
DROP TABLE IF EXISTS `com_pro_role`;
CREATE TABLE IF NOT EXISTS `com_pro_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `com_pro_role` DISABLE KEYS */;
INSERT INTO `com_pro_role` (`ID`, `NAME`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');
/*!40000 ALTER TABLE `com_pro_role` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_university
DROP TABLE IF EXISTS `com_pro_university`;
CREATE TABLE IF NOT EXISTS `com_pro_university` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CITY` int(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_university: ~2 rows (approximately)
/*!40000 ALTER TABLE `com_pro_university` DISABLE KEYS */;
INSERT INTO `com_pro_university` (`ID`, `NAME`, `CITY`) VALUES
	(1, '南京大学', 1),
	(2, '南通大学', 2),
	(3, '北京大学', 3);
/*!40000 ALTER TABLE `com_pro_university` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_user
DROP TABLE IF EXISTS `com_pro_user`;
CREATE TABLE IF NOT EXISTS `com_pro_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGINNAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `PASSWORD` varchar(50) COLLATE utf8_bin NOT NULL,
  `USERNAME` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `com_pro_user` DISABLE KEYS */;
INSERT INTO `com_pro_user` (`ID`, `LOGINNAME`, `PASSWORD`, `USERNAME`) VALUES
	(1, 'admin', 'admin', 'admin');
/*!40000 ALTER TABLE `com_pro_user` ENABLE KEYS */;


-- Dumping structure for table friend.com_pro_user_role
DROP TABLE IF EXISTS `com_pro_user_role`;
CREATE TABLE IF NOT EXISTS `com_pro_user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table friend.com_pro_user_role: ~1 rows (approximately)
/*!40000 ALTER TABLE `com_pro_user_role` DISABLE KEYS */;
INSERT INTO `com_pro_user_role` (`ID`, `USER_ID`, `ROLE_ID`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `com_pro_user_role` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
