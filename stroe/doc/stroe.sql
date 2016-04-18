/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : stroe

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2016-04-13 21:42:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_customerinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_customerinfo`;
CREATE TABLE `t_customerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_nameid` varchar(200) DEFAULT NULL,
  `t_name` varchar(200) DEFAULT NULL,
  `t_sex` varchar(200) DEFAULT NULL,
  `t_address` varchar(200) DEFAULT NULL,
  `t_tel` varchar(200) DEFAULT NULL,
  `t_type` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_customerinfo
-- ----------------------------
INSERT INTO `t_customerinfo` VALUES ('4', 'C001', '张小姐', '女', '陕西省西安市', '13880999999', '服装零食', '112');
INSERT INTO `t_customerinfo` VALUES ('5', 'C002', '王小强', '男', '天津宝山路', '13890999999', '男女服装', '11');

-- ----------------------------
-- Table structure for `t_employee`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_nameid` varchar(200) DEFAULT NULL,
  `t_name` varchar(200) DEFAULT NULL,
  `t_sex` varchar(200) DEFAULT NULL,
  `t_datebirth` datetime DEFAULT NULL,
  `t_entityid` varchar(200) DEFAULT NULL,
  `t_address` varchar(200) DEFAULT NULL,
  `t_tel` varchar(200) DEFAULT NULL,
  `t_datehire` datetime DEFAULT NULL,
  `t_type` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('1', 'Y001', '员工1', '男', null, '123456', '江宁路5号', '123456', null, '采购员', 'test');
INSERT INTO `t_employee` VALUES ('2', 'Y0002', '员工2', '男', '2015-03-03 00:00:00', '124567898', '鑫都花园2栋', '134654', null, '采购员, 送货员', '');

-- ----------------------------
-- Table structure for `t_finance`
-- ----------------------------
DROP TABLE IF EXISTS `t_finance`;
CREATE TABLE `t_finance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_financetype` varchar(200) DEFAULT NULL,
  `t_appgenid` varchar(200) DEFAULT NULL,
  `t_appnameid` varchar(200) DEFAULT NULL,
  `t_supname` varchar(200) DEFAULT NULL,
  `t_custgenid` int(11) DEFAULT NULL,
  `t_custnameid` varchar(200) DEFAULT NULL,
  `t_custname` varchar(200) DEFAULT NULL,
  `t_datefin` datetime DEFAULT NULL,
  `t_totalmoney` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_finance
-- ----------------------------
INSERT INTO `t_finance` VALUES ('10', '欠供货商货款', '1', 'G001', '供货商1', '4', 'C001', '张小姐', '2016-04-12 00:00:00', '1000', '11');
INSERT INTO `t_finance` VALUES ('11', '客户欠款', null, null, null, '5', 'C002', '王小强', '2016-04-13 00:00:00', '5000', '11');

-- ----------------------------
-- Table structure for `t_orderproduct`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderproduct`;
CREATE TABLE `t_orderproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_orderid` varchar(200) DEFAULT NULL,
  `t_orderstatus` varchar(200) DEFAULT NULL,
  `t_dateorder` varchar(200) DEFAULT NULL,
  `t_datefinished` varchar(200) DEFAULT NULL,
  `t_productid` varchar(200) DEFAULT NULL,
  `t_productgenid` varchar(200) DEFAULT NULL,
  `t_productname` varchar(200) DEFAULT NULL,
  `t_suppliernaem` varchar(200) DEFAULT NULL,
  `t_suppliergenid` varchar(200) DEFAULT NULL,
  `t_supplierid` varchar(200) DEFAULT NULL,
  `t_amount` varchar(200) DEFAULT NULL,
  `t_datebirth` varchar(200) DEFAULT NULL,
  `t_datequality` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_orderproduct
-- ----------------------------
INSERT INTO `t_orderproduct` VALUES ('8', 'D0001', '订单完成', '2016-04-06', '2016-04-20', 'S001', '8', '女士衬衫', '供货商1', '1', 'G001', '10', '2016-04-06', '2016-04-21', '123');
INSERT INTO `t_orderproduct` VALUES ('9', 'io09', '下订单', '2016-04-13', '2016-04-14', 'S00002', '9', '男士T恤', '供货商5', '3', 'G0005', '1', '2016-04-06', '', '');
INSERT INTO `t_orderproduct` VALUES ('10', 'D00002', '订单完成', '2016-04-06', '2016-04-14', 'S00002', '9', '男士T恤', '供货商2', '2', 'G002', '100', '2016-04-01', '2016-04-13', '11');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_nameid` varchar(200) DEFAULT NULL,
  `t_name` varchar(200) DEFAULT NULL,
  `t_suppliernaem` varchar(200) DEFAULT NULL,
  `t_suppliergenid` varchar(200) DEFAULT NULL,
  `t_supplierid` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('8', 'S001', '女士衬衫', '供货商1', '1', 'G001', '11');
INSERT INTO `t_product` VALUES ('9', 'S00002', '男士T恤', '供货商2', '2', 'G002', '');

-- ----------------------------
-- Table structure for `t_saleproduct`
-- ----------------------------
DROP TABLE IF EXISTS `t_saleproduct`;
CREATE TABLE `t_saleproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_productid` varchar(200) DEFAULT NULL,
  `t_productgenid` varchar(200) DEFAULT NULL,
  `t_productname` varchar(200) DEFAULT NULL,
  `t_orderstatus` varchar(200) DEFAULT NULL,
  `t_amount` varchar(200) DEFAULT NULL,
  `t_totalmoney` varchar(200) DEFAULT NULL,
  `t_datesale` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_saleproduct
-- ----------------------------
INSERT INTO `t_saleproduct` VALUES ('5', 'S001', '8', '女士衬衫', '配送中', '10', '500', '2016-04-13 00:00:00', '111');
INSERT INTO `t_saleproduct` VALUES ('6', 'S00002', '9', '男士T恤', '完成', '50', '5000', '2016-04-13 00:00:00', '11');

-- ----------------------------
-- Table structure for `t_shortcut`
-- ----------------------------
DROP TABLE IF EXISTS `t_shortcut`;
CREATE TABLE `t_shortcut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(200) DEFAULT NULL,
  `t_url` varchar(200) DEFAULT NULL,
  `t_username` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_shortcut
-- ----------------------------

-- ----------------------------
-- Table structure for `t_supplier`
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_nameid` varchar(200) DEFAULT NULL,
  `t_name` varchar(200) DEFAULT NULL,
  `t_address` varchar(200) DEFAULT NULL,
  `t_tel` varchar(200) DEFAULT NULL,
  `t_type` varchar(200) DEFAULT NULL,
  `t_remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO `t_supplier` VALUES ('1', 'G001', '供货商1', '上海市普陀区苏州路10号', '12354689', '技术及原材料', 'test');
INSERT INTO `t_supplier` VALUES ('2', 'G002', '供货商2', '上海市南京路15号', '12346548', '添加剂', 'test');
INSERT INTO `t_supplier` VALUES ('3', 'G0005', '供货商5', '南京路12号', '1324567', '技术支持', 'test');

-- ----------------------------
-- Table structure for `t_sysadmin`
-- ----------------------------
DROP TABLE IF EXISTS `t_sysadmin`;
CREATE TABLE `t_sysadmin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isLocked` varchar(255) DEFAULT NULL,
  `isLogon` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `accountCreateTime` datetime DEFAULT NULL,
  `passErrorTimes` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_sysadmin
-- ----------------------------
INSERT INTO `t_sysadmin` VALUES ('aaa', 'aaa', null, null, null, '1', null, '0', null, null, null, null, null, null, '0');
INSERT INTO `t_sysadmin` VALUES ('ccc', 'ccc', null, null, null, '1', null, '0', null, null, null, null, null, null, '0');
