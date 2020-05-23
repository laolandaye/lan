/*
Navicat MySQL Data Transfer

Source Server         : localhost.3307
Source Server Version : 50717
Source Host           : localhost:3307
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-19 12:08:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `password` varchar(256) DEFAULT NULL COMMENT '地址',
  `name` varchar(255) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `sex` int(10) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'zhangsan', '123456', '张三', '10', '1', '2019-05-19', '2019-05-19 10:41:06', '2019-05-19 10:41:06');
INSERT INTO `tb_user` VALUES ('2', 'lisi', '12345', '李四', '20', '2', '2019-05-19', '2019-05-19 10:47:34', '2019-05-19 10:47:34');
SET FOREIGN_KEY_CHECKS=1;
