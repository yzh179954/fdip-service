CREATE DATABASE  IF NOT EXISTS `fdip` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `fdip`;

CREATE TABLE IF NOT EXISTS `holiday` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `year` varchar(4) COLLATE utf8_bin NOT NULL COMMENT '年份',
  `day` date COLLATE utf8_bin NOT NULL COMMENT '日期',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '节假日名称',
  `description` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '节假日描述',
  `festival` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '节日',
  `sort` int(5) NOT NULL COMMENT '当年的第几天',
  PRIMARY KEY (`id`),
  UNIQUE KEY `day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert  into `holiday`(`id`,`year`,`day`,`name`,`description`,`festival`,`sort`) values (1,'2016','2016-1-1','元旦','1月1日放假，与周末连休共3天','2016-1-1',1),(2,'2016','2016-1-2','元旦','1月1日放假，与周末连休共3天','2016-1-1',2),(3,'2016','2016-1-3','元旦','1月1日放假，与周末连休共3天','2016-1-1',3),(4,'2016','2016-1-9','周六','周六','2016-1-9',9),(5,'2016','2016-1-10','周日','周日','2016-1-10',10),(6,'2016','2016-1-16','周六','周六','2016-1-16',16),(7,'2016','2016-1-17','周日','周日','2016-1-17',17),(8,'2016','2016-1-23','周六','周六','2016-1-23',23),(9,'2016','2016-1-24','周日','周日','2016-1-24',24),(10,'2016','2016-1-30','周六','周六','2016-1-30',30),(11,'2016','2016-1-31','周日','周日','2016-1-31',31),(12,'2016','2016-2-6','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',37),(13,'2016','2016-2-7','除夕','除夕','2016-2-7',38),(14,'2016','2016-2-8','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',39),(15,'2016','2016-2-9','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',40),(16,'2016','2016-2-10','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',41),(17,'2016','2016-2-11','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',42),(18,'2016','2016-2-12','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',43),(19,'2016','2016-2-13','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',44),(20,'2016','2016-2-14','春节','2月7日至13日放假调休，共7天。2月6日（星期六）、2月14日（星期日）上班。','2016-2-8',45),(21,'2016','2016-2-20','周六','周六','2016-2-20',51),(22,'2016','2016-2-21','周日','周日','2016-2-21',52),(23,'2016','2016-2-27','周六','周六','2016-2-27',58),(24,'2016','2016-2-28','周日','周日','2016-2-28',59),(25,'2016','2016-3-5','周六','周六','2016-3-5',65),(26,'2016','2016-3-6','周日','周日','2016-3-6',66),(27,'2016','2016-3-12','周六','周六','2016-3-12',72),(28,'2016','2016-3-13','周日','周日','2016-3-13',73),(29,'2016','2016-3-19','周六','周六','2016-3-19',79),(30,'2016','2016-3-20','周日','周日','2016-3-20',80),(31,'2016','2016-3-26','周六','周六','2016-3-26',86),(32,'2016','2016-3-27','周日','周日','2016-3-27',87),(33,'2016','2016-4-2','清明节','4月4日放假，与周末连休。','2016-4-4',93),(34,'2016','2016-4-3','清明节','4月4日放假，与周末连休。','2016-4-4',94),(35,'2016','2016-4-4','清明节','4月4日放假，与周末连休。','2016-4-4',95),(36,'2016','2016-4-9','周六','周六','2016-4-9',100),(37,'2016','2016-4-10','周日','周日','2016-4-10',101),(38,'2016','2016-4-16','周六','周六','2016-4-16',107),(39,'2016','2016-4-17','周日','周日','2016-4-17',108),(40,'2016','2016-4-23','周六','周六','2016-4-23',114),(41,'2016','2016-4-24','周日','周日','2016-4-24',115),(42,'2016','2016-4-30','劳动节','5月1日放假，5月2日（星期一）补休。','2016-5-1',121),(43,'2016','2016-5-1','劳动节','5月1日放假，5月2日（星期一）补休。','2016-5-1',122),(44,'2016','2016-5-2','劳动节','5月1日放假，5月2日（星期一）补休。','2016-5-1',123),(45,'2016','2016-5-7','周六','周六','2016-5-7',128),(46,'2016','2016-5-8','周日','周日','2016-5-8',129),(47,'2016','2016-5-14','周六','周六','2016-5-14',135),(48,'2016','2016-5-15','周日','周日','2016-5-15',136),(49,'2016','2016-5-21','周六','周六','2016-5-21',142),(50,'2016','2016-5-22','周日','周日','2016-5-22',143),(51,'2016','2016-5-28','周六','周六','2016-5-28',149),(52,'2016','2016-5-29','周日','周日','2016-5-29',150),(53,'2016','2016-6-4','周六','周六','2016-6-4',156),(54,'2016','2016-6-5','周日','周日','2016-6-5',157),(55,'2016','2016-6-9','端午节','6月9日至11日放假调休，共3天。6月12日（星期日）上班。','2016-6-9',161),(56,'2016','2016-6-10','端午节','6月9日至11日放假调休，共3天。6月12日（星期日）上班。','2016-6-9',162),(57,'2016','2016-6-11','端午节','6月9日至11日放假调休，共3天。6月12日（星期日）上班。','2016-6-9',163),(58,'2016','2016-6-12','端午节','6月9日至11日放假调休，共3天。6月12日（星期日）上班。','2016-6-9',164),(59,'2016','2016-6-18','周六','周六','2016-6-18',170),(60,'2016','2016-6-19','周日','周日','2016-6-19',171),(61,'2016','2016-6-25','周六','周六','2016-6-25',177),(62,'2016','2016-6-26','周日','周日','2016-6-26',178),(63,'2016','2016-7-2','周六','周六','2016-7-2',184),(64,'2016','2016-7-3','周日','周日','2016-7-3',185),(65,'2016','2016-7-9','周六','周六','2016-7-9',191),(66,'2016','2016-7-10','周日','周日','2016-7-10',192),(67,'2016','2016-7-16','周六','周六','2016-7-16',198),(68,'2016','2016-7-17','周日','周日','2016-7-17',199),(69,'2016','2016-7-23','周六','周六','2016-7-23',205),(70,'2016','2016-7-24','周日','周日','2016-7-24',206),(71,'2016','2016-7-30','周六','周六','2016-7-30',212),(72,'2016','2016-7-31','周日','周日','2016-7-31',213),(73,'2016','2016-8-6','周六','周六','2016-8-6',219),(74,'2016','2016-8-7','周日','周日','2016-8-7',220),(75,'2016','2016-8-13','周六','周六','2016-8-13',226),(76,'2016','2016-8-14','周日','周日','2016-8-14',227),(77,'2016','2016-8-20','周六','周六','2016-8-20',233),(78,'2016','2016-8-21','周日','周日','2016-8-21',234),(79,'2016','2016-8-27','周六','周六','2016-8-27',240),(80,'2016','2016-8-28','周日','周日','2016-8-28',241),(81,'2016','2016-9-3','周六','周六','2016-9-3',247),(82,'2016','2016-9-4','周日','周日','2016-9-4',248),(83,'2016','2016-9-10','周六','周六','2016-9-10',254),(84,'2016','2016-9-11','周日','周日','2016-9-11',255),(85,'2016','2016-9-15','中秋节','9月15日至17日放假调休，共3天。9月18日（星期日）上班。','2016-9-15',259),(86,'2016','2016-9-16','中秋节','9月15日至17日放假调休，共3天。9月18日（星期日）上班。','2016-9-15',260),(87,'2016','2016-9-17','中秋节','9月15日至17日放假调休，共3天。9月18日（星期日）上班。','2016-9-15',261),(88,'2016','2016-9-18','中秋节','9月15日至17日放假调休，共3天。9月18日（星期日）上班。','2016-9-15',262),(89,'2016','2016-9-24','周六','周六','2016-9-24',268),(90,'2016','2016-9-25','周日','周日','2016-9-25',269),(91,'2016','2016-10-1','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',275),(92,'2016','2016-10-2','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',276),(93,'2016','2016-10-3','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',277),(94,'2016','2016-10-4','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',278),(95,'2016','2016-10-5','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',279),(96,'2016','2016-10-6','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',280),(97,'2016','2016-10-7','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',281),(98,'2016','2016-10-8','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',282),(99,'2016','2016-10-9','国庆节','10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。','2016-10-1',283),(100,'2016','2016-10-15','周六','周六','2016-10-15',289),(101,'2016','2016-10-16','周日','周日','2016-10-16',290),(102,'2016','2016-10-22','周六','周六','2016-10-22',296),(103,'2016','2016-10-23','周日','周日','2016-10-23',297),(104,'2016','2016-10-29','周六','周六','2016-10-29',303),(105,'2016','2016-10-30','周日','周日','2016-10-30',304),(106,'2016','2016-11-5','周六','周六','2016-11-5',310),(107,'2016','2016-11-6','周日','周日','2016-11-6',311),(108,'2016','2016-11-12','周六','周六','2016-11-12',317),(109,'2016','2016-11-13','周日','周日','2016-11-13',318),(110,'2016','2016-11-19','周六','周六','2016-11-19',324),(111,'2016','2016-11-20','周日','周日','2016-11-20',325),(112,'2016','2016-11-26','周六','周六','2016-11-26',331),(113,'2016','2016-11-27','周日','周日','2016-11-27',332),(114,'2016','2016-12-3','周六','周六','2016-12-3',338),(115,'2016','2016-12-4','周日','周日','2016-12-4',339),(116,'2016','2016-12-10','周六','周六','2016-12-10',345),(117,'2016','2016-12-11','周日','周日','2016-12-11',346),(118,'2016','2016-12-17','周六','周六','2016-12-17',352),(119,'2016','2016-12-18','周日','周日','2016-12-18',353),(120,'2016','2016-12-24','周六','周六','2016-12-24',359),(121,'2016','2016-12-25','周日','周日','2016-12-25',360),(122,'2016','2016-12-31','周六','周六','2016-12-31',366);


CREATE TABLE IF NOT EXISTS `3rd_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` bigint(20) NOT NULL,
  `accountInfo` text COLLATE utf8_bin NOT NULL COMMENT '三方账户详情(JSON)',
  `userId` varchar(50) NOT NULL COMMENT '三方账户唯一表示, 来自三方账户详情', 
  `account` bigint(20) NOT NULL COMMENT '基本账户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USERID` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `accountNo` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '个人用户子帐号(摊位号)',
  `bankAccountNo` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '个人用户银行子帐号',
  `bankAccount` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '银行卡号',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '姓名(银行卡户名)',
  `credential` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `tradePwd` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '交易密码',
  `status` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `account_fund_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `channel` bigint(20) NOT NULL COMMENT '渠道',
  `fund` bigint(20) NOT NULL COMMENT '基金',
  `account` bigint(20) NOT NULL COMMENT '用户',
  `amount` decimal(10,4) NOT NULL COMMENT '可用总份额(含已经计息和还未开始计息的份额)',
  `interestAmount` decimal(10,4) NOT NULL COMMENT '正在计息的份额',
  `frozenAmount` decimal(10,4) NOT NULL COMMENT '冻结总金额',
  `income` decimal(10,4) NOT NULL COMMENT '累计收益',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `incomeTime` String COMMENT '同步分红日期格式yyyyMMdd',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `capital_allocation` ( 
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `txTradeNo` VARCHAR(50) COLLATE utf8_bin COMMENT '资金划拨流水(由CBIP的资金划拨接口返回)',
  `tradeNos` VARCHAR(200) COLLATE utf8_bin  COMMENT '交易流水号, 以逗号分隔',
  `amount` DECIMAL(10,2) NOT NULL COMMENT '划拨金额',
  `status` BIT(1) NOT NULL DEFAULT b'1' COMMENT '资金划拨状态',
  `type` VARCHAR(50) NOT NULL COMMENT '资金冻结, 资金解冻, 资金划拨',
  `remark` TEXT COMMENT '备注',
  `occurTime` DATETIME NOT NULL COMMENT '发生时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '渠道编码',
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '渠道名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert  into `channel`(`code`,`name`) values ('THS','同花顺');


CREATE TABLE IF NOT EXISTS `fund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `channel` bigint(20) NOT NULL COMMENT '渠道',
  `code` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '基金代码',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '基金名称',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
INSERT  INTO `fund`(`code`,`name`,`channel`,`createTime`,`updateTime`) VALUES ('161608','融通易支付货币A',1,'2016-05-16 10:30:00','2016-05-16 10:30:00');

CREATE TABLE IF NOT EXISTS `income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` bigint(20) NOT NULL COMMENT '渠道',
  `fund` bigint(20) NOT NULL COMMENT '基金',
  `account` bigint(20) NOT NULL COMMENT '用户',
  `day` date NOT NULL COMMENT '收益日期',
  `income` decimal(10,3) NOT NULL DEFAULT '0.000' COMMENT '收益',
  `realIncome` decimal(10,3) DEFAULT '0.0000' COMMENT '实际收益',
  `interestAmount` decimal(10,3) NOT NULL DEFAULT '0.000' COMMENT '计息份额',
  `createTime` datetime NOT NULL COMMENT '同步时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `_account_fund_day` (`channel`,`fund`,`account`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `income_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `channel` bigint(20) NOT NULL COMMENT '渠道',
  `fund` bigint(20) NOT NULL COMMENT '基金',
  `millionIncomeRate` decimal(10,4) NOT NULL COMMENT '万份收益',
  `annualIncome7d` decimal(10,4) NOT NULL COMMENT '七日年化收益率',
  `day` date NOT NULL COMMENT '日期',
  `createTime` datetime NOT NULL COMMENT '同步时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `sgedit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade` bigint(20) NOT NULL COMMENT '表示修改了哪个申购请求',
  `amount` decimal(10,2) NOT NULL COMMENT '修改的金额',
  `createTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `trade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account` bigint(20) NOT NULL COMMENT '主账户',
  `thrdAccount` bigint(20) NOT NULL COMMENT '交易账户',
  `tradeNo` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '交易流水',
  `extTradeNo` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '外部交易流水',
  `amount` decimal(10,2) NOT NULL COMMENT '交易金额',
  `realAmount` decimal(10,2) DEFAULT NULL COMMENT '该申请单号最终的份额',
  `status` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '交易状态 (成功,失败,进行中)',
  `tradeType` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '交易类型(申购,赎回)',
  `createTime` datetime NOT NULL COMMENT '交易时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `channel` bigint(20) NOT NULL COMMENT '渠道',
  `fund` bigint(20) NOT NULL COMMENT '基金',
  `payday` date COMMENT '支付时间, 同花顺(或其他)基金(冻结)账户划拨到余额账户的时间, 赎回类型可为空',
  `editableDeadline` datetime DEFAULT NULL COMMENT '可编辑截至时间; tradeType为申购时,该字段不为空',
  `transferNo` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '资金划拨流水,录个人冻结到基金冻结; 记录同花顺T+0赎回时的资金划拨流水',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



