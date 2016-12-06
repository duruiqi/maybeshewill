/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.36 : Database - yhzm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yhzm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yhzm`;

/*Table structure for table `contactus` */

DROP TABLE IF EXISTS `contactus`;

CREATE TABLE `contactus` (
  `usId` int(11) NOT NULL AUTO_INCREMENT,
  `linkman` varchar(16) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `qq` varchar(32) DEFAULT NULL,
  `fax` varchar(16) DEFAULT NULL,
  `postcode` varchar(16) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `linkAddrr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`usId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contactus` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(16) DEFAULT NULL,
  `principal` varchar(16) DEFAULT NULL,
  `FOUND` date DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `department` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(16) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `gender` char(4) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `education` varchar(32) DEFAULT NULL,
  `job` varchar(32) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

/*Table structure for table `firminfo` */

DROP TABLE IF EXISTS `firminfo`;

CREATE TABLE `firminfo` (
  `firmId` varchar(32) NOT NULL,
  `frimName` varchar(32) DEFAULT NULL,
  `FOUND` date DEFAULT NULL,
  `loginFund` varchar(16) DEFAULT NULL,
  `introduce` text,
  `imagesAddr` text,
  `firmAddr` varchar(100) DEFAULT NULL,
  `beiAnInfo` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`firmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `firminfo` */

/*Table structure for table `messageboard` */

DROP TABLE IF EXISTS `messageboard`;

CREATE TABLE `messageboard` (
  `msId` varchar(32) NOT NULL,
  `msContent` text,
  `isDraft` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`msId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `messageboard` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `nid` varchar(32) NOT NULL,
  `nTitle` varchar(50) DEFAULT NULL,
  `nImages` varchar(200) DEFAULT NULL,
  `nContent` text,
  `nDate` date DEFAULT NULL,
  `sate` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `news` */

/*Table structure for table `produce` */

DROP TABLE IF EXISTS `produce`;

CREATE TABLE `produce` (
  `proId` int(11) NOT NULL AUTO_INCREMENT,
  `proName` varchar(32) DEFAULT NULL,
  `proType` varchar(32) DEFAULT NULL,
  `proImages` varchar(32) DEFAULT NULL,
  `proCatogroy` varchar(16) DEFAULT NULL,
  `proMaterial` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`proId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `produce` */

/*Table structure for table `projectorder` */

DROP TABLE IF EXISTS `projectorder`;

CREATE TABLE `projectorder` (
  `orderId` varchar(32) DEFAULT NULL,
  `projectName` varchar(32) DEFAULT NULL,
  `projectType` varchar(16) DEFAULT NULL,
  `projectAddr` varchar(100) DEFAULT NULL,
  `projectImg` text,
  `projectPrice` double(16,2) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `projectorder` */

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `purId` varchar(32) NOT NULL,
  `materialName` varchar(32) DEFAULT NULL,
  `materialType` varchar(16) DEFAULT NULL,
  `materialImages` varchar(32) DEFAULT NULL,
  `purCount` int(11) DEFAULT NULL,
  `purPrice` double(16,2) DEFAULT NULL,
  `isPay` tinyint(4) DEFAULT NULL,
  `payMethod` varchar(10) DEFAULT NULL,
  `purDate` date DEFAULT NULL,
  PRIMARY KEY (`purId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase` */

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `replyId` varchar(32) NOT NULL,
  `replyContent` text,
  `isDraft` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reply` */

/*Table structure for table `salary` */

DROP TABLE IF EXISTS `salary`;

CREATE TABLE `salary` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sBase` double(12,2) DEFAULT NULL,
  `sCount` double(12,2) DEFAULT NULL,
  `sBusiness` double(12,2) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salary` */

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `adminId` varchar(32) NOT NULL,
  `adminName` varchar(16) DEFAULT NULL,
  `adminPasswd` varchar(32) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `adminName` (`adminName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`adminId`,`adminName`,`adminPasswd`,`state`) values ('d3991087e4b44b9988ed8fa1482c1111','admin','admin',1);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `roleId` varchar(32) NOT NULL,
  `roleType` varchar(32) DEFAULT NULL,
  `accreditState` varchar(32) DEFAULT NULL,
  `accreditCode` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userImage` varchar(16) DEFAULT NULL,
  `userPasswd` varchar(32) DEFAULT NULL,
  `stateCode` varchar(64) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `delMark` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
