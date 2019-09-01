/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : spring7

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2018-11-12 23:06:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('AA', 'aa@163.com', '10');
INSERT INTO `employees` VALUES ('bb', 'bb@163.com', '9');
INSERT INTO `employees` VALUES ('cc', 'cc@163.com', '8');
INSERT INTO `employees` VALUES ('dd', 'dd@163.com', '7');
INSERT INTO `employees` VALUES ('ee', 'ee@163.com', '6');

-- ----------------------------
-- Table structure for sh_account
-- ----------------------------
DROP TABLE IF EXISTS `sh_account`;
CREATE TABLE `sh_account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) DEFAULT NULL,
  `BALANCE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sh_account
-- ----------------------------
INSERT INTO `sh_account` VALUES ('1', 'aa', '30');

-- ----------------------------
-- Table structure for sh_book
-- ----------------------------
DROP TABLE IF EXISTS `sh_book`;
CREATE TABLE `sh_book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOK_NAME` varchar(255) DEFAULT NULL,
  `ISBN` varchar(255) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sh_book
-- ----------------------------
INSERT INTO `sh_book` VALUES ('1', 'java', '1001', '100', '8');
INSERT INTO `sh_book` VALUES ('2', 'oracle', '1002', '70', '9');

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL,
  `name` varbinary(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`dept_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('李雷', '520');
INSERT INTO `user` VALUES ('李雷', '520');
INSERT INTO `user` VALUES ('李雷', '520');
SET FOREIGN_KEY_CHECKS=1;
