/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.21-log : Database - boot_shiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`boot_shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `boot_shiro`;

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `pid` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `pname` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `ptype` int(3) DEFAULT NULL COMMENT '权限类型：1.菜单；2.按钮',
  `rule` varchar(30) DEFAULT NULL COMMENT '权限规则，shiro的权限控制表达式',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rname` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `rdesc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` bigint(15) NOT NULL,
  `perm_id` bigint(15) NOT NULL,
  PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(50) DEFAULT NULL COMMENT '登录名',
  `nick` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `pwd` varchar(200) DEFAULT NULL COMMENT '已加密的登录密码',
  `pwd_salt` varchar(200) DEFAULT NULL COMMENT '加密盐值',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`uname`,`nick`,`pwd`,`pwd_salt`,`created`,`updated`) values (1,'cai','菜籽','123456','445566','2018-03-31 02:40:03',NULL),(2,'bao','宝宝','J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=','wxKYXuTPST5SG0jMQzVPsg==','2018-03-31 02:45:54',NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` bigint(15) NOT NULL,
  `role_id` bigint(15) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
