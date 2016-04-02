-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.3-m13-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 db_zl 的数据库结构
CREATE DATABASE IF NOT EXISTS `db_zl` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `db_zl`;


-- 导出  表 db_zl.t_admin 结构
CREATE TABLE IF NOT EXISTS `t_admin` (
  `userId` int(11) NOT NULL,
  `userName` varchar(77) DEFAULT NULL,
  `userPw` varchar(777) DEFAULT NULL,
  `remark` varchar(777) DEFAULT NULL,
  `userRealname` varchar(50) DEFAULT NULL,
  `userAddress` varchar(777) DEFAULT NULL,
  `userTel` varchar(50) DEFAULT NULL,
  `userType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_admin 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` (`userId`, `userName`, `userPw`, `remark`, `userRealname`, `userAddress`, `userTel`, `userType`) VALUES
	(1, 'a', 'a', NULL, NULL, NULL, NULL, '2'),
	(2, 'tt', 'tt', 'rwqrwerq', NULL, 'tqwerwer', '323324242', '1'),
	(3, 'yy', 'yy', '我是房东', NULL, '南京市江宁区玉树路', '15950469405', '1'),
	(4, 'test1', '111', '好房出租', NULL, '苏州市', '15900000001', '1'),
	(5, 'test2', '111', '求租三室一厅普通装修的房子', NULL, '苏州市', '15900000000', '1'),
	(6, 'landlord111', '111', '长期出租房屋', NULL, '江苏省', '15950000000', '1');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;


-- 导出  表 db_zl.t_che 结构
CREATE TABLE IF NOT EXISTS `t_che` (
  `id` int(11) NOT NULL,
  `chexing` varchar(77) DEFAULT NULL,
  `pinpai` varchar(777) DEFAULT NULL,
  `beizhu` varchar(500) DEFAULT NULL,
  `fujian` varchar(50) DEFAULT NULL,
  `rizu` int(11) DEFAULT NULL,
  `zt` varchar(50) DEFAULT NULL,
  `del` varchar(50) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `quyu` varchar(50) DEFAULT NULL,
  `ttype` varchar(50) DEFAULT NULL,
  `zhxiu` varchar(50) DEFAULT NULL,
  `mianji` varchar(50) DEFAULT NULL,
  `linkman` varchar(50) DEFAULT NULL,
  `linkphone` varchar(50) DEFAULT NULL,
  `bookdate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_che 的数据：~13 rows (大约)
/*!40000 ALTER TABLE `t_che` DISABLE KEYS */;
INSERT INTO `t_che` (`id`, `chexing`, `pinpai`, `beizhu`, `fujian`, `rizu`, `zt`, `del`, `userid`, `quyu`, `ttype`, `zhxiu`, `mianji`, `linkman`, `linkphone`, `bookdate`) VALUES
	(6, '好房子出租', '一室一厅', '33333', '/upload/1429684097684.jpg', 100, '空闲中', 'yes', 2, '鼓楼区', '公寓', '普通', '125', '张先生', '13851715972', '2016-04-21 12:04'),
	(7, '大学城精装三室一厅', '三室一厅', '家电齐全', '/upload/1429749556092.jpg', 3000, '空闲中', 'no', 2, '鼓楼区', '公寓', '精装', '145', '张先生', '15950469405', '2016-04-23 08:39'),
	(8, '精装单室出租', '一室一厅', '精装单室出租', '/upload/1429749622889.jpg', 900, '已租出', 'no', 2, '鼓楼区', '牌楼', '精装', '24', '张先生', '13851715972', '2016-04-23 08:40'),
	(9, '大学城单室出租，好房不等人', '三室一厅', '大学城单室出租，好房不等人', '/upload/1429790825962.jpg', 800, '已租出', 'no', 1, '江宁区', '牌楼', '精装', '25', '大雄', '15950469405', '2016-04-23 20:07'),
	(10, '鼓楼地铁口精装三室放出租', '三室一厅', '设施齐全，鼓楼地铁口精装三室放出租', '/upload/1429795448235.jpg', 5000, '已租出', 'no', 2, '鼓楼区', '牌楼', '精装', '341', '李先生', '13851715972', '2016-04-23 21:24'),
	(11, '江宁大学城精装单室居出租', '一室一厅', '江宁大学城精装单室居出租，实施齐全', '/upload/1429796759507.jpg', 605, '空闲中', 'no', 3, '江宁区', '公寓', '精装', '25', '游仙诗', '15950469405', '2016-04-23 21:46'),
	(12, '江宁大学城三室一厅', '三室一厅', '31312213231', '/upload/1429798047273.jpg', 2455, '已租出', 'no', 1, '江宁区', '牌楼', '普通', '125', '大雄', '13851715972', '2016-04-23 22:07'),
	(13, '好房出租', '两室一厅', '<ul>\r\n    <li><em><strong>&nbsp;这是内容</strong></em></li>\r\n</ul>', '/upload/1459075517704.jpg', 3500, '空闲中', 'no', 1, '鼓楼区', '公寓', '中等', '75', '11', '11', '2016-03-27 18:45'),
	(14, '三室一厅好房出租', '三室一厅', '三室一厅好房出租<br  />', '/upload/1459254652212.jpg', 100, '已租出', 'no', 1, '六合区', '公寓', '精装', '150', '杜拉拉', '15900000000', '2016-03-29 20:31'),
	(15, '两室一厅好房出租', '两室一厅', '位于鼓楼区繁华地带，两室一厅，精装修，两个卧室都朝南，不可多得的好房<br  />', '/upload/1459256062026.jpg', 3500, '空闲中', 'yes', 4, '鼓楼区', '公寓', '精装', '85', '张三', '15933333333', '2016-03-29 20:54'),
	(16, '房东1-单身公寓便宜出租', '一室一厅', '单身公寓便宜出租<br  />', '/upload/1459256145370.jpg', 1500, '已租出', 'no', 4, '建邺区', '牌楼', '中等', '50', '李四', '15944444444', '2016-03-29 20:55'),
	(17, '新房东1-两室一厅好房出租', '两室一厅', '两室一厅好房出租<br  />', '/upload/1459257100037.jpg', 2500, '已租出', 'no', 6, '鼓楼区', '公寓', '普通', '100', '新房东1', '15950000000', '2016-03-29 21:11'),
	(18, '房东1-租房信息2', '三室一厅', '三大法师打发<br  />', '/upload/1459260714307.jpg', 3000, '空闲中', 'yes', 6, '六合区', '牌楼', '精装', '100', '嘿嘿', '15950000000', '2016-03-29 22:11');
/*!40000 ALTER TABLE `t_che` ENABLE KEYS */;


-- 导出  表 db_zl.t_contract 结构
CREATE TABLE IF NOT EXISTS `t_contract` (
  `contractId` int(11) NOT NULL,
  `adminId` int(11) NOT NULL DEFAULT '0' COMMENT '房东Id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '客户Id',
  `content` mediumtext COMMENT '合同内容',
  `status` int(11) DEFAULT NULL COMMENT '合同状态：0：未签约；1：已签约；2：已结束',
  `startDate` date DEFAULT NULL COMMENT '合同生效时间',
  `endDate` date DEFAULT NULL COMMENT '合同失效时间',
  `zulinId` int(11) DEFAULT NULL,
  PRIMARY KEY (`contractId`),
  KEY `INDEX_adminId` (`adminId`),
  KEY `INDEX_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='合同管理';

-- 正在导出表  db_zl.t_contract 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_contract` DISABLE KEYS */;
INSERT INTO `t_contract` (`contractId`, `adminId`, `userId`, `content`, `status`, `startDate`, `endDate`, `zulinId`) VALUES
	(1, 2, 3, '333333333', 1, '2016-03-21', '2016-03-27', 1),
	(4, 6, 10, '12321321312321<br  />', 0, '2016-01-03', '2016-06-30', 14),
	(5, 3, 6, '<strong>&nbsp;测试合同</strong>', 0, '2016-03-01', '2016-06-30', 8);
/*!40000 ALTER TABLE `t_contract` ENABLE KEYS */;


-- 导出  表 db_zl.t_fund 结构
CREATE TABLE IF NOT EXISTS `t_fund` (
  `id` int(11) NOT NULL,
  `contractId` int(11) DEFAULT NULL,
  `money` decimal(18,2) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `payType` int(11) DEFAULT NULL COMMENT '是否退款：0：不是；1：是',
  `receiptNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='资金管理';

-- 正在导出表  db_zl.t_fund 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `t_fund` DISABLE KEYS */;
INSERT INTO `t_fund` (`id`, `contractId`, `money`, `type`, `status`, `payType`, `receiptNumber`) VALUES
	(2, 1, 200.00, 0, 0, 0, '111111111'),
	(4, 4, 500.00, 0, 1, 0, '55555555'),
	(5, 4, 1500.00, 1, 0, 0, '23123123213'),
	(6, 4, 200.00, 1, 0, 1, '123123213213'),
	(8, 4, 4500.00, 2, 0, 0, '123131');
/*!40000 ALTER TABLE `t_fund` ENABLE KEYS */;


-- 导出  表 db_zl.t_lianjie 结构
CREATE TABLE IF NOT EXISTS `t_lianjie` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `del` varchar(777) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_lianjie 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_lianjie` DISABLE KEYS */;
INSERT INTO `t_lianjie` (`id`, `name`, `url`, `del`) VALUES
	(1, '百度搜素引擎网', 'http://www.baidu.com', 'no'),
	(2, '谷歌搜素引擎网', 'http://www.google.com.ck', 'no'),
	(3, '新浪网', 'http://www.sina.com.cn', 'no');
/*!40000 ALTER TABLE `t_lianjie` ENABLE KEYS */;


-- 导出  表 db_zl.t_liuyan 结构
CREATE TABLE IF NOT EXISTS `t_liuyan` (
  `id` int(11) NOT NULL,
  `neirong` mediumtext,
  `liuyanshi` varchar(77) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `huifu` mediumtext,
  `huifushi` varchar(777) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_liuyan 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `t_liuyan` DISABLE KEYS */;
INSERT INTO `t_liuyan` (`id`, `neirong`, `liuyanshi`, `userId`, `huifu`, `huifushi`) VALUES
	(1, '1111111111111111111111111111111111111111111', '2016-01-21 01:57', 1, '11', '2016-01-22 01:58'),
	(2, '嘎嘎嘎搞个嘎嘎嘎灌灌灌灌灌', '2016-02-21 23:54', 1, '顶顶顶顶顶', '2016-02-22 23:56'),
	(3, 'ffffffff', '2016-03-21 12:00', 1, NULL, NULL),
	(4, '个古古怪怪古古怪怪古古怪怪古古怪怪', '2016-03-20 12:03', 1, '拜拜拜拜拜拜拜拜吧', '2016-03-21 12:04'),
	(5, '2345678987654', '2016-02-12 17:04', 3, 'tttt', '2016-02-13 17:04');
/*!40000 ALTER TABLE `t_liuyan` ENABLE KEYS */;


-- 导出  表 db_zl.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_pw` varchar(77) DEFAULT NULL,
  `user_realname` varchar(50) DEFAULT NULL,
  `user_sex` varchar(777) DEFAULT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_tel` varchar(50) DEFAULT NULL,
  `user_del` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_user 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`user_id`, `user_name`, `user_pw`, `user_realname`, `user_sex`, `user_age`, `user_address`, `user_tel`, `user_del`, `remark`) VALUES
	(1, 'liusan', '000000', '刘三', '男', 33, '北京路', '13555555555', 'no', NULL),
	(2, 'lisisi', '000000', '李斯', '男', 20, '上海路', '13666666666', 'no', NULL),
	(3, 'qq', 'qq', 'qq', '男', 20, NULL, NULL, 'no', NULL),
	(4, 'gg', 'gg', '李浩明', NULL, 0, '南京市建邺区100号', '15950469405', 'no', NULL),
	(5, 'oo', 'oo', '欧阳小蔡', NULL, 0, '南京是软件谷', '1595046855', 'no', NULL),
	(6, 'kk', 'kk', '孔一子', NULL, 0, '南京市夫子庙', '15950469405', 'no', NULL),
	(9, 'user1', '123', 'tom', NULL, 0, '江苏省', '15950000000', 'no', NULL),
	(10, 'user111', '111', 'user111', NULL, 0, '江苏省', '15900000000', 'no', NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


-- 导出  表 db_zl.t_yuyue 结构
CREATE TABLE IF NOT EXISTS `t_yuyue` (
  `id` int(11) NOT NULL,
  `cheId` int(11) DEFAULT NULL,
  `xingming` varchar(255) DEFAULT NULL,
  `lianxi` varchar(777) DEFAULT NULL,
  `zhuzhi` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `belonguserid` int(11) DEFAULT NULL,
  `liuyanremark` varchar(500) DEFAULT NULL,
  `huifuremark` varchar(500) DEFAULT NULL,
  `isType` char(10) DEFAULT NULL,
  `pinjiaremark` varchar(500) DEFAULT NULL,
  `huifushij` varchar(20) DEFAULT NULL,
  `pinjiashij` varchar(20) DEFAULT NULL,
  `liushij` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_yuyue 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `t_yuyue` DISABLE KEYS */;
INSERT INTO `t_yuyue` (`id`, `cheId`, `xingming`, `lianxi`, `zhuzhi`, `userid`, `belonguserid`, `liuyanremark`, `huifuremark`, `isType`, `pinjiaremark`, `huifushij`, `pinjiashij`, `liushij`) VALUES
	(2, 7, NULL, NULL, NULL, 3, 2, NULL, NULL, 'pj', '很好的房子，我很喜欢', NULL, '2016-02-23 15:24', NULL),
	(3, 7, 'qq', NULL, NULL, 3, 2, NULL, NULL, 'pj', '此房家电齐全，是大学生理想住处；很喜欢', NULL, '2016-03-23 16:09', NULL),
	(4, 7, 'qq', NULL, NULL, 3, 5, '房东，我准备租半年，房租统一打您卡里，请发下你的卡号', '好的，6234567896345', 'ly', NULL, '2016-02-23 21:51', NULL, '2016-02-23 16:23'),
	(5, 8, '李浩明', NULL, NULL, 4, 2, NULL, NULL, 'pj', '房子挺好滴！', NULL, '2016-03-23 21:37', NULL),
	(7, 8, '李浩明', NULL, NULL, 4, 2, NULL, NULL, 'pj', '433333', NULL, '2016-03-23 21:40', NULL),
	(8, 7, '欧阳小蔡', NULL, NULL, 5, 5, '房东，我要租半年，房租一次性打您卡里，请发下您的卡号', '好的，卡号：612000000000', 'ly', NULL, '2016-03-29 20:57', NULL, '2016-03-23 21:50'),
	(9, 7, '欧阳小蔡', NULL, NULL, 5, 2, NULL, NULL, 'pj', '这是我的评价', NULL, '2016-03-23 21:54', NULL),
	(11, 11, '孔一子', NULL, NULL, 6, 3, '重新租，准备租半年，发下卡号', '好的，卡号为：62345766543234', 'ly', NULL, '2016-02-23 22:04', NULL, '2016-02-23 22:02'),
	(12, 11, '孔一子', NULL, NULL, 6, 3, NULL, NULL, 'pj', '房子挺好的，我很喜欢！', NULL, '2016-03-23 22:05', NULL),
	(13, 7, 'qq', NULL, NULL, 3, 2, NULL, NULL, 'pj', '哇！太棒了！', NULL, '2016-03-29 20:39', NULL),
	(14, 12, 'qq', NULL, NULL, 3, 1, '很好', NULL, 'ly', NULL, NULL, NULL, '2016-03-29 20:39'),
	(15, 14, 'tom', NULL, NULL, 9, 1, '想要租你的这间三室一厅的房子', NULL, 'ly', NULL, NULL, NULL, '2016-03-29 20:52'),
	(16, 14, 'tom', NULL, NULL, 9, 1, '想租房子', NULL, 'ly', NULL, NULL, NULL, '2016-03-29 21:03'),
	(17, 17, 'user111', NULL, NULL, 10, 6, '想要租这套房子', '好的', 'ly', NULL, '2016-03-29 21:13', NULL, '2016-03-29 21:12');
/*!40000 ALTER TABLE `t_yuyue` ENABLE KEYS */;


-- 导出  表 db_zl.t_zulin 结构
CREATE TABLE IF NOT EXISTS `t_zulin` (
  `id` int(11) NOT NULL,
  `cheId` int(11) DEFAULT NULL,
  `kehuming` varchar(66) DEFAULT NULL,
  `shenfenzheng` varchar(77) DEFAULT NULL,
  `jiazhaohao` varchar(77) DEFAULT NULL,
  `kaishishijian` varchar(50) DEFAULT NULL,
  `yajin` int(11) DEFAULT NULL,
  `beizhu` varchar(50) DEFAULT NULL,
  `shifouhuan` varchar(50) DEFAULT NULL,
  `jieshushijian` varchar(50) DEFAULT NULL,
  `feiyong` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `belonguserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- 正在导出表  db_zl.t_zulin 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `t_zulin` DISABLE KEYS */;
INSERT INTO `t_zulin` (`id`, `cheId`, `kehuming`, `shenfenzheng`, `jiazhaohao`, `kaishishijian`, `yajin`, `beizhu`, `shifouhuan`, `jieshushijian`, `feiyong`, `userId`, `belonguserId`) VALUES
	(1, 7, 'qq', '1595046405', NULL, '2016-03-23 13:05', 45684, '12345677d', '已搬走', '2015-04-23', 3000, 3, 2),
	(2, 8, '李浩明', '15950469405', NULL, '2016-03-23 21:37', NULL, NULL, '未还', NULL, 900, 4, 2),
	(3, 8, '李浩明', '15950469405', NULL, '2016-03-23 21:38', NULL, NULL, '未还', NULL, 900, 4, 2),
	(6, 7, '欧阳小蔡', '1595046855', NULL, '2016-03-23 21:50', NULL, NULL, '未还', NULL, 3000, 5, 2),
	(7, 10, '孔一子', '15950469405', NULL, '2016-03-23 21:58', NULL, NULL, '未还', NULL, 5000, 6, 2),
	(8, 11, '孔一子', '15950469405', NULL, '2016-03-23 22:01', 4562, '房客回老家了，不租了', '已搬走', '2015-04-23', 605, 6, 3),
	(9, 7, 'qq', NULL, NULL, '2016-03-28 21:11', NULL, NULL, 'δ??', NULL, 3000, 3, 2),
	(10, 12, 'qq', NULL, NULL, '2016-03-28 21:16', NULL, NULL, '未还', NULL, 2455, 3, 1),
	(11, 14, 'tom', '15950000000', NULL, '2016-03-29 20:51', NULL, NULL, '未还', NULL, 100, 9, 1),
	(12, 16, 'tom', '15950000000', NULL, '2016-03-29 21:03', NULL, NULL, '未还', NULL, 1500, 9, 4),
	(14, 17, 'user111', '15900000000', NULL, '2016-03-29 21:12', NULL, NULL, '未还', NULL, 2500, 10, 6);
/*!40000 ALTER TABLE `t_zulin` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
