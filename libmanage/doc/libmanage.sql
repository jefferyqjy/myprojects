/*
MySQL Data Transfer
Source Host: localhost
Source Database: libmanage
Target Host: localhost
Target Database: libmanage
Date: 2012-5-13 20:46:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bookhj
-- ----------------------------
DROP TABLE IF EXISTS `bookhj`;
CREATE TABLE `bookhj` (
  `id` int(11) NOT NULL auto_increment,
  `jtime` varchar(255) default NULL,
  `htime` varchar(255) default NULL,
  `bookname` varchar(255) default NULL,
  `readername` varchar(255) default NULL,
  `yjin` varchar(255) default NULL,
  `bei` varchar(255) default NULL,
  `hbtime` varchar(255) default NULL,
  `hbkou` varchar(255) default NULL,
  `hbbei` varchar(255) default NULL,
  `sjtime` varchar(255) default NULL,
  `sjstatus` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL auto_increment,
  `isbn` varchar(255) default NULL,
  `bookname` varchar(255) default NULL,
  `price` varchar(255) default NULL,
  `tslb` varchar(255) default NULL,
  `cbs` varchar(255) default NULL,
  `jianj` varchar(255) default NULL,
  `author` varchar(255) default NULL,
  `cbrq` varchar(255) default NULL,
  `kucun` varchar(255) default NULL,
  `filename` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bookyy
-- ----------------------------
DROP TABLE IF EXISTS `bookyy`;
CREATE TABLE `bookyy` (
  `id` int(11) NOT NULL auto_increment,
  `yytime` varchar(255) default NULL,
  `htime` varchar(255) default NULL,
  `readername` varchar(255) default NULL,
  `bookname` varchar(255) default NULL,
  `bei` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sreader
-- ----------------------------
DROP TABLE IF EXISTS `sreader`;
CREATE TABLE `sreader` (
  `id` int(11) NOT NULL auto_increment,
  `uname` varchar(255) default NULL,
  `upass` varchar(255) default NULL,
  `tname` varchar(255) default NULL,
  `xueli` varchar(255) default NULL,
  `ziye` varchar(255) default NULL,
  `kjnum` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `sc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for syspros
-- ----------------------------
DROP TABLE IF EXISTS `syspros`;
CREATE TABLE `syspros` (
  `id` int(11) NOT NULL auto_increment,
  `proname` varchar(255) default NULL,
  `infoa` varchar(255) default NULL,
  `infob` varchar(255) default NULL,
  `infoc` varchar(255) default NULL,
  `infod` varchar(255) default NULL,
  `infoe` varchar(255) default NULL,
  `infof` varchar(255) default NULL,
  `infog` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `uname` varchar(255) default NULL,
  `upass` varchar(255) default NULL,
  `tname` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `bookhj` VALUES ('1', '2012-05-10', '2012-05-13', '杀人鬼绘卷', '111', '10', '..', '2012-05-10', '0', '...', null, null);
INSERT INTO `bookhj` VALUES ('2', '2012-05-10', '2012-05-11', '杀人鬼绘卷', '123', '10', '...', '2012-05-18', '0', '...', null, null);
INSERT INTO `bookhj` VALUES ('3', '2012-05-10', '2012-05-25', '国家地理第一期', '123', '10', '...', null, null, null, '2012-06-01', '已通过');
INSERT INTO `books` VALUES ('1', 'no.123121', '国家地理第一期', '10', '小说类', '北方出版社', '狮子老婆', 'disco', '1991年1月12日', '100', '201205112123590002.jpg');
INSERT INTO `books` VALUES ('2', 'no.123123', '杀人鬼绘卷', '20', '小说类', '武汉儿童出版社', '吸血鬼', '乔靖夫', '1980年1月2日第一版', '3', '201205112123510001.jpg');
INSERT INTO `bookyy` VALUES ('4', '2012-05-23', '2012-05-31', '123', '杀人鬼绘卷', '...', '已通过');
INSERT INTO `bookyy` VALUES ('5', '2012-05-11', '2012-05-24', '123', '国家地理第一期', '...', '已通过');
INSERT INTO `sreader` VALUES ('5', '123', '123', '王龙', '大专', '老师', '3', '02788888888', '123@121113.com', '-1--2-');
INSERT INTO `sreader` VALUES ('6', '111', '111', '胡三', '高中', '医生 ', '5', '02788888889', '3422411222@qq.com', '-2-');
INSERT INTO `syspros` VALUES ('2', '武汉电子工业出版社', '出版社', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('3', '北方出版社', '出版社', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('4', '北京电子出版社', '出版社', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('5', '苏州出版社', '出版社', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('6', '武汉儿童出版社', '出版社', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('7', '湖北省出社城', '出版社', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('10', '杂志类', '图书类别', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('11', '小说类', '图书类别', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('12', '技术手册类', '图书类别', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('13', '工具书类', '图书类别', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('14', '小学', '学历', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('15', '初中', '学历', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('16', '高中', '学历', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('17', '大专', '学历', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('18', '本科', '学历', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('19', '博士', '学历', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('20', '程序员', '职业', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('21', '学生', '职业', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('22', '医生 ', '职业', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('23', '工程人员', '职业', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('24', '老师', '职业', null, null, null, null, null, null);
INSERT INTO `syspros` VALUES ('25', '技术人员', '职业', null, null, null, null, null, null);
INSERT INTO `sysuser` VALUES ('1', 'admin', '123', '系统管理员', '小马哥', '123@121113.com');
INSERT INTO `sysuser` VALUES ('7', '123', '123', '小王', '027888888886', '123@321.com');
