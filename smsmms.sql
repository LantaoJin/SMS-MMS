/*
SQLyog Trial v10.51 
MySQL - 5.5.25 : Database - sms-mms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sms-mms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sms-mms`;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memId` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `birthday` date NOT NULL,
  `birthdaystr` varchar(4) NOT NULL,
  `zip` varchar(6) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `edu` varchar(20) DEFAULT NULL,
  `industry` varchar(20) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `expert` varchar(50) DEFAULT NULL,
  `joindate` date NOT NULL,
  `lastdate` date DEFAULT NULL,
  `introducer` varchar(10) DEFAULT NULL,
  `feesum` decimal(10,0) NOT NULL,
  `description` text,
  `lunarstr` varchar(4) NOT NULL,
  PRIMARY KEY (`id`,`memId`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`id`,`memId`,`name`,`gender`,`birthday`,`birthdaystr`,`zip`,`address`,`tel`,`phone`,`email`,`edu`,`industry`,`title`,`expert`,`joindate`,`lastdate`,`introducer`,`feesum`,`description`,`lunarstr`) values (1,'000001','test1',1,'2000-02-01','0201','314413','钱塘江边','0573-88888888','13888888888','888@8.com','高中','自由职业','董事长','篮球','2014-04-10','2015-04-11','dad',10000000,'sdfsaf','1226'),(2,'000002','test2',0,'2000-02-01','0201','314413','长江边','0571-11111111','13900000000','111@1.com','博士后','IT','职员','无','2012-02-10','2012-12-28','cdsca',1400,'cdaafadf','1226'),(252,'000003','哈哈',1,'2000-02-01','0201','314413','where','0571-11111111','15799999997','11@11.cn','博士','无业','职员','','2012-02-10','2014-04-10','sdfaf',2015,'dscd','1226'),(253,'000004','test4',1,'2000-02-01','0201','314413','where','0571-11111111','15799999999','11@11.cn','本科','无业','职员','篮球','2012-02-10','2014-04-10',NULL,2015,NULL,'1226'),(254,'000005','test5',1,'2000-02-01','0201','314413','where','0571-11111111','15799999999','11@11.cn','本科','无业','职员','篮球','2012-02-10','2014-04-10',NULL,2015,NULL,'1226'),(255,'000006','test6',1,'2000-02-01','0201','314413','where','0571-11111111','15799999999','11@11.cn','本科','无业','职员','篮球','2012-02-10','2014-04-10',NULL,2015,NULL,'1226'),(256,'000007','test7',1,'2000-02-01','0201','314413','where','0571-11111111','15799999999','11@11.cn','本科','无业','职员','篮球','2012-02-10','2014-04-10',NULL,2015,NULL,'1226'),(257,'000008','test8',1,'2000-02-01','0201','314413','where','0571-11111111','13900000000','11@11.cn','本科','无业','职员','篮球','2012-02-10','2014-04-10',NULL,2015,NULL,'1226'),(258,'000009','test9',0,'1992-04-05','0405','314413','where','0571-11111111','13900000000','11@11.cn','硕士','无业','职员','','2012-02-10','2014-04-10',NULL,2015,'哈哈','0303'),(259,'000010','test10',0,'2000-02-02','0202','314413','where','0571-11111111','13900000000','11@11.cn','本科','工业','职员','','2012-02-10','2014-04-10','ddad',2115,'caccccc','1227'),(262,'000011','test11',0,'1970-01-09','0109','111111','shanghai','','15799999999','jin@jin.com','博士','','','','2014-04-24','2016-04-24','fd32',10365,NULL,'1202'),(263,'000012','test12',1,'1970-01-01','0101','111133','中文','3323','15921778099','','博士','工业','CEO','','2014-04-24','2015-04-24','2323efd',2999,NULL,'1124'),(264,'000013','金',1,'1970-01-01','0101','314499','美国','33332','13777777323','ed@ssc.com','博士','互联网','工程师','','2014-04-25','2016-04-27','2e323',55755,NULL,'1124'),(265,'000015','女女',0,'1970-01-01','0101','','','','13899900000','','硕士','','','','2014-04-25','2016-04-26','ewqe',523233,'csaddsaca','1124'),(266,'000016','adsfa',1,'1970-01-01','0101','asdfsa','asdfasdf','dsfsdfa','15842213244','','大专','','','','2014-04-29','2015-04-29','ee',1100,NULL,'1124'),(267,'000017','你好',1,'1972-03-07','0307','','','','15977777777','','初中及以下','','','','2014-04-30','2024-04-30','eee',3650,'csadcc','0122'),(268,'000018','jin',1,'1970-01-01','0101','','','','','','初中及以下','','','','2014-04-30','2015-04-30','eee',1000,NULL,'1124'),(269,'000018','金澜涛',1,'2014-04-30','0430',NULL,NULL,NULL,'15921778090',NULL,'大专',NULL,NULL,NULL,'2014-04-01','2015-04-29','dfwe',1000,NULL,'0402'),(270,'000019','jjjjjjasd',1,'1987-01-31','0131','','','','','','初中及以下','','','','2014-05-05','2015-06-05','dffe',1000,'csdacwcdece','0103'),(271,'000020','朱蕾',0,'1992-04-05','0405','','asdf','','15868132680','','本科','','','','2014-05-05','2015-05-05','金澜涛',1000000,'my girl friend','0303');

/*Table structure for table `model` */

DROP TABLE IF EXISTS `model`;

CREATE TABLE `model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modelname` varchar(50) NOT NULL,
  `usehead` int(1) NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`modelname`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `model` */

insert  into `model`(`id`,`modelname`,`usehead`,`title`,`content`,`description`) values (1,'model1',1,'先生/女士','哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈','model1'),(2,'model2',0,'施主/施主','呵呵呵','model2'),(3,'model3',0,'','sfasdfab',''),(4,'model4',0,'','dafd','sdfa'),(6,'model5',1,'','safaf','sdf'),(9,'model6',1,'施主/施主','ddd','抬头+施主'),(10,'model7',1,'','ddd','抬头'),(11,'model8',0,'','ddd','不用'),(12,'model9',1,'施主/施主','nihao','用抬头和称谓');

/*Table structure for table `receipt` */

DROP TABLE IF EXISTS `receipt`;

CREATE TABLE `receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiptId` varchar(50) DEFAULT NULL,
  `memId` varchar(20) NOT NULL,
  `money` decimal(10,0) NOT NULL,
  `attnname` varchar(20) DEFAULT NULL,
  `createdate` date NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `receipt` */

insert  into `receipt`(`id`,`receiptId`,`memId`,`money`,`attnname`,`createdate`,`description`) values (1,'d001','000011',10000,NULL,'2014-04-24','??'),(2,'v002','000012',2999,NULL,'2014-04-24','测试'),(3,'v004','000013',55555,NULL,'2014-04-25','又忘记提交了'),(4,'v007','000015',323233,NULL,'2014-04-25','嗯哼'),(5,'v113','000013',100,NULL,'2014-04-27','eee'),(6,'v012','000016',100,NULL,'2014-04-29',''),(7,'v013','000016',1000,NULL,'2014-04-29',''),(8,'v012','000011',365,NULL,'2014-04-30',''),(9,'v100','000017',3650,NULL,'2014-04-30',''),(10,'v1111','000018',1000,NULL,'2014-04-30',''),(11,'','000019',1000,NULL,'2014-05-05',''),(12,'','000020',1000000,'','2014-05-05','没交钱');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
