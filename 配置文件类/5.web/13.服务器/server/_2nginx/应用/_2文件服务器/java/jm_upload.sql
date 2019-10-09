/*
Navicat MySQL Data Transfer

Source Server         : 118.184.212.218
Source Server Version : 50724
Source Host           : 118.184.212.218:3306
Source Database       : jmrh

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-06-06 10:59:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jm_upload
-- ----------------------------
DROP TABLE IF EXISTS `jm_upload`;
CREATE TABLE `jm_upload` (
  `id` varchar(42) NOT NULL,
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件绝对路径',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名+后缀',
  `file_md5` varchar(255) DEFAULT NULL,
  `file_content_type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `file_size` varchar(255) DEFAULT NULL COMMENT '文件大小',
  `file_path_real` varchar(255) DEFAULT NULL COMMENT '文件访问路径',
  `file_suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jm_upload
-- ----------------------------
INSERT INTO `jm_upload` VALUES ('4028b8816b29fca1016b29fd9bb20000', '/tmp/nginx_upload/0000000054', '66EFA7418130EEEE05FCF3C97E2B6715.jpg', '', 'image/jpeg', '24193', '/pic/0000000054', null);
INSERT INTO `jm_upload` VALUES ('4028b8816b2a05da016b2a07478a0000', '/tmp/nginx_upload/0000000055', '66EFA7418130EEEE05FCF3C97E2B6715.jpg', '', 'image/jpeg', '24193', '/pic/0000000055', null);
INSERT INTO `jm_upload` VALUES ('4028b8816b2a05da016b2a07b0ce0001', '/tmp/nginx_upload/0000000056', '66EFA7418130EEEE05FCF3C97E2B6715.jpg', '', 'image/jpeg', '24193', '/pic/0000000056', null);
INSERT INTO `jm_upload` VALUES ('4028b8816b2a18ba016b2a24a4be0000', '/tmp/nginx_upload/0000000064', '66EFA7418130EEEE05FCF3C97E2B6715.jpg', '', 'image/jpeg', '24193', '/pic/0000000064', '.jpg');
INSERT INTO `jm_upload` VALUES ('4028b8816b2a6d98016b2a752bb30000', '/tmp/nginx_upload/0005015224', '未来爱人.pptx', '', 'application/vnd.openxmlformats-officedocument.presentationml.presentation', '3752872', '/pic/0005015224', '.pptx');
INSERT INTO `jm_upload` VALUES ('4028b8816b2a7f49016b2a8070360000', '/tmp/nginx_upload/0005015225', '未来爱人.pptx', '', 'application/vnd.openxmlformats-officedocument.presentationml.presentation', '3752872', '/pic/0005015225', '.pptx');
SET FOREIGN_KEY_CHECKS=1;
