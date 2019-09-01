/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - dc
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*Table structure for table `data_trans_ftpserv` */

DROP TABLE IF EXISTS `data_trans_ftpserv`;

CREATE TABLE `data_trans_ftpserv` (
  `servname` varchar(16) NOT NULL,
  `cnname` varchar(32) DEFAULT NULL,
  `ftptype` varchar(64) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `chartset` varchar(32) DEFAULT NULL,
  `workdir` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `filetype` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`servname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `data_trans_ftpserv` */

/*Table structure for table `dp_dev_physic_tab` */

DROP TABLE IF EXISTS `dp_dev_physic_tab`;

CREATE TABLE `dp_dev_physic_tab` (
  `TAB_ID` varchar(64) NOT NULL,
  `COL_NUM` varchar(32) DEFAULT NULL,
  `CREATE_TIME` varchar(32) DEFAULT NULL,
  `CREATOR` varchar(64) DEFAULT NULL,
  `CUSTOM_LIFE_CYCLE` varchar(5) DEFAULT NULL,
  `CYCLE_TYPE` varchar(32) DEFAULT NULL,
  `CYCLE_VALUE` varchar(32) DEFAULT NULL,
  `DB_ID` varchar(64) DEFAULT NULL,
  `DBUSER_ID` varchar(64) DEFAULT NULL,
  `EXTEND_CFG` text,
  `LEVEL_ID` varchar(64) DEFAULT NULL,
  `LIFE_CYCLE` varchar(10) DEFAULT NULL,
  `MODIFY_TIME` varchar(32) DEFAULT NULL,
  `MODIFY_USER` varchar(64) DEFAULT NULL,
  `OPEN_STATUS` varchar(10) DEFAULT NULL,
  `REMARK` varchar(1024) DEFAULT NULL,
  `SOURCE_TYPE` varchar(20) DEFAULT NULL,
  `TAB_CN_NAME` varchar(200) DEFAULT NULL,
  `TAB_NAME` varchar(100) DEFAULT NULL,
  `TAB_STATUS` varchar(16) DEFAULT NULL,
  `TEAM_CODE` varchar(32) DEFAULT NULL,
  `TEAM_ID` varchar(64) DEFAULT NULL,
  `TOPIC_CODE` varchar(32) DEFAULT NULL,
  `TOPIC_FULL_CODE` varchar(255) DEFAULT NULL,
  `LOGICTAB_TAB_ID` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dp_dev_physic_tab` */

/*Table structure for table `dp_dev_proc` */

DROP TABLE IF EXISTS `dp_dev_proc`;

CREATE TABLE `dp_dev_proc` (
  `XML_ID` varchar(64) NOT NULL,
  `CREATE_TIME` varchar(255) DEFAULT NULL,
  `CREATOR` varchar(32) DEFAULT NULL,
  `CYCLE_TYPE` varchar(64) DEFAULT NULL,
  `DB_NAME` varchar(64) DEFAULT NULL,
  `EXTEND_CFG` varchar(1024) DEFAULT NULL,
  `MODIFY_TIME` varchar(255) DEFAULT NULL,
  `PROC_CN_NAME` varchar(120) DEFAULT NULL,
  `PROC_NAME` varchar(64) NOT NULL,
  `PROC_PATH` varchar(32) DEFAULT NULL,
  `PROC_STATUS` varchar(32) DEFAULT NULL,
  `PROC_TYPE` varchar(32) DEFAULT NULL,
  `PROC_VERSION` varchar(32) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `RUN_PARA` varchar(300) DEFAULT NULL,
  `TEAM_CODE` varchar(64) DEFAULT NULL,
  `TOPIC_CODE` varchar(64) DEFAULT NULL,
  `TOPIC_NAME` varchar(64) DEFAULT NULL,
  `XML` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dp_dev_proc` */

/*Table structure for table `dp_dev_team_res` */

DROP TABLE IF EXISTS `dp_dev_team_res`;

CREATE TABLE `dp_dev_team_res` (
  `RES_TEAM_ID` varchar(64) NOT NULL,
  `DB_ID` varchar(32) DEFAULT NULL,
  `RES_ID` varchar(64) NOT NULL,
  `RES_STATUS` varchar(1) NOT NULL,
  `RES_TYPE` varchar(4) NOT NULL,
  `TEAM_ID` varchar(64) NOT NULL,
  `USER_NAME` varchar(200) NOT NULL,
  `USER_PWD` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dp_dev_team_res` */

/*Table structure for table `fw_dim` */

DROP TABLE IF EXISTS `fw_dim`;

CREATE TABLE `fw_dim` (
  `dim_id` varchar(32) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `dim_code` varchar(100) DEFAULT NULL,
  `dim_level` bigint(20) DEFAULT NULL,
  `dim_name` varchar(200) DEFAULT NULL,
  `dim_seq` bigint(20) DEFAULT NULL,
  `dim_status` varchar(5) DEFAULT NULL,
  `dim_value_code` varchar(100) DEFAULT NULL,
  `dim_value_name` varchar(200) DEFAULT NULL,
  `modify_time` varchar(20) DEFAULT NULL,
  `pid` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dim_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_dim` */

insert  into `fw_dim`(`dim_id`,`create_time`,`creator`,`dim_code`,`dim_level`,`dim_name`,`dim_seq`,`dim_status`,`dim_value_code`,`dim_value_name`,`modify_time`,`pid`,`remark`) values ('25366298544308224',NULL,NULL,'fa fa-cogs',2,'fa fa-cogs',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308225',NULL,NULL,'fa fa-cubes',2,'fa fa-cubes',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308226',NULL,NULL,'fa fa-user-o',2,'fa fa-user-o',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308227',NULL,NULL,'fa fa-address-card',2,'fa fa-address-card',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308228',NULL,NULL,'fa fa-server',2,'fa fa-server',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308229',NULL,NULL,'fa fa-calculator',2,'fa fa-calculator',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308230',NULL,NULL,'fa fa-cog',2,'fa fa-cog',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308231',NULL,NULL,'fa fa-volume-up',2,'fa fa-volume-up',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308232',NULL,NULL,'fa fa-users',2,'fa fa-users',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308233',NULL,NULL,'fa fa-bars',2,'fa fa-bars',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308234',NULL,NULL,'fa fa-barcode',2,'fa fa-barcode',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308235',NULL,NULL,'fa fa-file-text-o',2,'fa fa-file-text-o',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308236',NULL,NULL,'fa fa-list-ol',2,'fa fa-list-ol',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308237',NULL,NULL,'fa fa-pencil-square-o',2,'fa fa-pencil-square-o',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308238',NULL,NULL,'fa fa-codepen',2,'fa fa-codepen',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308239',NULL,NULL,'fa bm-program-deve',2,'fa bm-program-deve',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308240',NULL,NULL,'fa bm-resource-mana',2,'fa bm-resource-mana',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308241',NULL,NULL,'fa fa-list-alt',2,'fa fa-list-alt',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308242',NULL,NULL,'fa fa-paste',2,'fa fa-paste',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('25366298544308243',NULL,NULL,'fa fa-share-alt',2,'fa fa-share-alt',NULL,'1',NULL,NULL,NULL,'4028ef8160117bf9016011889601002a',NULL),('4028ef8160117bf90160118134da000d',NULL,NULL,'menuType',1,'菜单类型',NULL,'1',NULL,NULL,NULL,'',''),('4028ef8160117bf9016011816ed1000e',NULL,NULL,'-1',2,'主菜单',-1,'1',NULL,NULL,NULL,'4028ef8160117bf90160118134da000d',''),('4028ef8160117bf901601181d1f30010',NULL,NULL,'0',2,'左侧树形菜单',1,'1',NULL,NULL,NULL,'4028ef8160117bf90160118134da000d',''),('4028ef8160117bf901601181f9df0011',NULL,NULL,'2',2,'中间填充菜单',2,'1',NULL,NULL,NULL,'4028ef8160117bf90160118134da000d',''),('4028ef8160117bf901601182159f0012',NULL,NULL,'3',2,'新窗口菜单',3,'1',NULL,NULL,NULL,'4028ef8160117bf90160118134da000d',''),('4028ef8160117bf901601182159f0013',NULL,NULL,'4',2,'中间完全填充',4,'1',NULL,NULL,NULL,'4028ef8160117bf90160118134da000d',''),('4028ef8160117bf9016011889601002a',NULL,NULL,'menuIcon',1,'菜单图片',NULL,'1',NULL,NULL,NULL,'','');

/*Table structure for table `fw_module` */

DROP TABLE IF EXISTS `fw_module`;

CREATE TABLE `fw_module` (
  `module_id` varchar(32) NOT NULL,
  `child_num` varchar(4) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `modify_time` varchar(20) DEFAULT NULL,
  `module_code` varchar(100) DEFAULT NULL,
  `module_img` varchar(255) DEFAULT NULL,
  `module_name` varchar(200) DEFAULT NULL,
  `module_seq` int(11) DEFAULT NULL,
  `module_status` varchar(5) DEFAULT NULL,
  `module_type` varchar(200) DEFAULT NULL,
  `module_url` varchar(255) DEFAULT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_module` */

insert  into `fw_module`(`module_id`,`child_num`,`create_time`,`creator`,`modify_time`,`module_code`,`module_img`,`module_name`,`module_seq`,`module_status`,`module_type`,`module_url`,`parent_code`,`parent_name`,`remark`) values ('402819815e551388015e552f098b0001',NULL,'2017-09-06 17:49:41','1','2017-09-06 17:49:41','02-bziPV06l-w9SE1FxE','fa fa-file-text-o','系统初始化',1,'1','0','','02-bziPV06l',NULL,''),('402819815e551388015e5530c6130004',NULL,'2017-09-07 18:31:42','1','2017-09-07 18:31:42','02-bziPV06l-w9SE1FxE-WVviCNI7','fa fa-list-ol','系统菜单管理',1,'1','0','ftl/sysmgr/FwModuleMgr','02-bziPV06l-w9SE1FxE',NULL,''),('402819815e551388015e55314b7f0005',NULL,'2017-09-07 18:31:50','1','2017-09-07 18:31:50','02-bziPV06l-w9SE1FxE-Tn7krtCM','fa fa-cogs','系统参数管理',2,'1','0','ftl/sysmgr/FwParamConfigMgr','02-bziPV06l-w9SE1FxE',NULL,''),('402819815e551388015e5531c3230006',NULL,'2017-09-07 18:31:55','1','2017-09-07 18:31:55','02-bziPV06l-w9SE1FxE-jvlDUctG','fa fa-server','系统维度管理',3,'1','0','ftl/sysmgr/FwDimMgr','02-bziPV06l-w9SE1FxE',NULL,''),('402819815e551388015e5698401c0014',NULL,'2017-09-07 18:32:14','1','2017-09-07 18:32:14','02-bziPV06l-l6mk8NHL','fa fa-users','系统权限管理',2,'1','0','','02-bziPV06l',NULL,''),('402819815e551388015e569962280015',NULL,'2017-09-06 18:25:36','1','2017-09-06 18:25:36','02-bziPV06l-l6mk8NHL-bqclYiJ7','fa fa-file-text-o','用户管理',2,'1','0','ftl/sysmgr/FwUserMgrN','02-bziPV06l-l6mk8NHL',NULL,''),('402819815e551388015e569a475d0016',NULL,'2017-09-06 18:25:25','1','2017-09-06 18:25:25','02-bziPV06l-l6mk8NHL-G45LVSMz','fa fa-file-text-o','角色管理',1,'1','0','ftl/sysmgr/FwRoleMgrN','02-bziPV06l-l6mk8NHL',NULL,''),('402880ef641c711001641c7be5cc0002',NULL,NULL,NULL,NULL,'02-bziPV06l-l6mk8NHL-HXdFBqEp','fa fa-file-text-o','团队管理',0,'1','0','ftl/sysmgr/teamMgrN','02-bziPV06l-l6mk8NHL',NULL,''),('ff8080815e51f587015e526e79d80009',NULL,'2018-05-09 11:24:26','1','2018-05-09 11:24:26','02-bziPV06l','/res/images/channels-system.png','控制中心',99,'1','-1','','02',NULL,''),('ff808081620bd24601620bd3712d0023',NULL,'2018-03-09 17:40:10','1','2018-03-09 17:40:10','02-bziPV06l-J9bdO49L','fa fa-file-text-o','操作日志审计',7,'1','0','ftl/sysmgr/FwTraceLog','02-bziPV06l',NULL,'');

/*Table structure for table `fw_notice` */

DROP TABLE IF EXISTS `fw_notice`;

CREATE TABLE `fw_notice` (
  `notice_id` varchar(32) NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `eff_date` datetime NOT NULL,
  `mail_tip` varchar(1) DEFAULT NULL,
  `notice_title` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `sms_tip` varchar(1) DEFAULT NULL,
  `team_ids` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `user_ids` varchar(120) NOT NULL,
  `valid_days` int(11) DEFAULT NULL,
  `web_tip` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_notice` */

/*Table structure for table `fw_param_config` */

DROP TABLE IF EXISTS `fw_param_config`;

CREATE TABLE `fw_param_config` (
  `param_id` varchar(32) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `modify_time` varchar(20) DEFAULT NULL,
  `param_code` varchar(100) DEFAULT NULL,
  `param_name` varchar(200) DEFAULT NULL,
  `param_remark` varchar(255) DEFAULT NULL,
  `param_status` varchar(1) DEFAULT NULL,
  `param_type` varchar(32) DEFAULT NULL,
  `param_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`param_id`),
  UNIQUE KEY `UKgy2sglb3n7o8rui4awsut1gmm` (`param_type`,`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_param_config` */

INSERT INTO fw_param_config (param_id,create_time,creator,modify_time,param_code,param_name,param_remark,param_status,param_type,param_value) VALUES 
('1','2017-06-29 11:55:30','1','2017-06-29 11:55:30','loginBgImage','登录页面背景','登录页面背景','1','LoginConfig','/res/images/login/login-bg.jpg')
,('10','2017-06-29 11:54:37','1','2017-06-29 11:54:37','useXssFilter','是否开启XSS过滤','是否开启XSS过滤','1','LoginConfig','true')
,('11','2017-06-29 11:55:06','1','2017-06-29 11:55:06','loginImage','登录页图片','登录页图片','1','LoginConfig','/res/images/login/login-intro-bg.jpg')
,('12','2017-06-23 11:00:11','1','2017-06-23 11:00:11','channelLogo','频道页logo','公司logo','1','LoginConfig','/res/images/channel/channel-product-logo.png')
,('13','2017-06-23 17:15:51','1','2017-06-23 17:15:51','gugong@dpm.org.cn','问卷系统登录信息','问卷系统登录信息','1','LoginSSO_WJ','123456')
,('14','2017-06-23 17:15:51','1','2017-06-23 17:15:51','admin_oem','舆情系统登录信息','舆情系统登录信息','1','LoginSSO_YQ','123456')
,('2','2017-06-29 12:12:50','1','2017-06-29 12:12:50','loginSystemName','登录系统名称','登录系统名称','1','LoginConfig','故宫门户系统')
,('3','2017-06-23 12:15:07','1','2017-06-23 12:15:07','loginValidate','是否开启验证码','是否开启验证码','1','LoginConfig','false')
,('4','2017-06-23 10:01:21','1','2017-06-23 10:01:21','loginCopyRight','登录底部版权信息','登录底部版权信息','1','LoginConfig','')
,('5','2017-06-23 10:59:49','1','2017-06-23 10:59:49','loginCompanyName','公司名称','公司名称','1','LoginConfig','')
,('6','2017-06-23 11:00:11','1','2017-06-23 11:00:11','loginCompanyLogo','公司logo','公司logo','1','LoginConfig','/res/images/login/product-logo.png')
,('7','2017-06-23 15:58:47','1','2017-06-23 15:58:47','loginPage','登录页面','登录页面','1','LoginConfig','web/login')
,('8','2017-06-23 15:58:35','1','2017-06-23 15:58:35','loginRedirectUri','登录成功跳转页面','登录成功跳转页面','1','LoginConfig','/ftl/index')
,('9','2017-06-23 17:15:51','1','2017-06-23 17:15:51','noLoginIndexPage','系统首页','不登录默认页面','1','LoginConfig','')
;
/*Table structure for table `fw_role` */

DROP TABLE IF EXISTS `fw_role`;

CREATE TABLE `fw_role` (
  `role_id` varchar(32) NOT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `modify_time` varchar(20) DEFAULT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `role_code` varchar(100) DEFAULT NULL,
  `role_name` varchar(200) DEFAULT NULL,
  `role_status` varchar(5) DEFAULT NULL,
  `role_type` varchar(5) DEFAULT NULL,
  `team_id` varchar(64) DEFAULT NULL,
  `team_type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_axotf4sop3itjonp2voresh0w` (`role_code`),
  KEY `FK5m1homamld91bw2svk1svkam6` (`team_id`),
  CONSTRAINT `FK5m1homamld91bw2svk1svkam6` FOREIGN KEY (`team_id`) REFERENCES `fw_team` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_role` */

insert  into `fw_role`(`role_id`,`create_time`,`creator`,`modify_time`,`parent_code`,`parent_name`,`role_code`,`role_name`,`role_status`,`role_type`,`team_id`,`team_type`) values ('402819815e5b92cd015e5baedd640000','2017-09-07 17:32:19','1','2017-09-07 17:32:19',NULL,NULL,'SysAdmin','系统管理角色','1','0',NULL,NULL),('402880ef641c711001641c76a0f00001','2018-06-20 17:11:33','1','2018-06-20 17:11:33',NULL,NULL,'teatRole','teatRole','1','1','SysTeam',NULL);

/*Table structure for table `fw_role_module` */

DROP TABLE IF EXISTS `fw_role_module`;

CREATE TABLE `fw_role_module` (
  `role_id` varchar(32) NOT NULL,
  `module_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_role_module` */

insert  into `fw_role_module`(`role_id`,`module_id`) values ('402819815e5b92cd015e5baedd640000','402819815e551388015e552f098b0001'),('402819815e5b92cd015e5baedd640000','402819815e551388015e5530c6130004'),('402819815e5b92cd015e5baedd640000','402819815e551388015e55314b7f0005'),('402819815e5b92cd015e5baedd640000','402819815e551388015e5531c3230006'),('402819815e5b92cd015e5baedd640000','402819815e551388015e5698401c0014'),('402819815e5b92cd015e5baedd640000','402819815e551388015e569962280015'),('402819815e5b92cd015e5baedd640000','402819815e551388015e569a475d0016'),('402819815e5b92cd015e5baedd640000','402880ef641c711001641c7be5cc0002'),('402819815e5b92cd015e5baedd640000','ff8080815e51f587015e526e79d80009'),('402819815e5b92cd015e5baedd640000','ff808081620bd24601620bd3712d0023'),('402880ef641c711001641c76a0f00001','ff8080815e51f587015e526e79d80009'),('402880ef641c711001641c76a0f00001','ff808081620bd24601620bd3712d0023');

/*Table structure for table `fw_role_user` */

DROP TABLE IF EXISTS `fw_role_user`;

CREATE TABLE `fw_role_user` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKfll7nt0h4elhfw5goaap9rx21` (`role_id`),
  CONSTRAINT `FKfll7nt0h4elhfw5goaap9rx21` FOREIGN KEY (`role_id`) REFERENCES `fw_role` (`role_id`),
  CONSTRAINT `FKfq3qvf1rcw1e55lidx40g2hcv` FOREIGN KEY (`user_id`) REFERENCES `fw_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_role_user` */

insert  into `fw_role_user`(`user_id`,`role_id`) values ('1','402819815e5b92cd015e5baedd640000'),('402880ef641c711001641c7641d20000','402880ef641c711001641c76a0f00001');

/*Table structure for table `fw_table_space` */

DROP TABLE IF EXISTS `fw_table_space`;

CREATE TABLE `fw_table_space` (
  `id` varchar(64) NOT NULL,
  `create_time` varchar(32) DEFAULT NULL,
  `data_free` bigint(20) DEFAULT NULL,
  `data_length` bigint(20) DEFAULT NULL,
  `db_type` varchar(200) DEFAULT NULL,
  `index_length` bigint(20) DEFAULT NULL,
  `max_data_length` bigint(20) DEFAULT NULL,
  `modify_time` varchar(32) DEFAULT NULL,
  `physic_id` varchar(64) DEFAULT NULL,
  `table_name` varchar(200) DEFAULT NULL,
  `table_schema` varchar(100) DEFAULT NULL,
  `table_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_table_space` */

/*Table structure for table `fw_tbl_sql_conf` */

DROP TABLE IF EXISTS `fw_tbl_sql_conf`;

CREATE TABLE `fw_tbl_sql_conf` (
  `id` varchar(64) NOT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `sql_code` varchar(128) DEFAULT NULL,
  `sql_template` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qismgqbtcqlnco0jl7b4mbva5` (`sql_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_tbl_sql_conf` */

/*Table structure for table `fw_team` */

DROP TABLE IF EXISTS `fw_team`;

CREATE TABLE `fw_team` (
  `team_id` varchar(128) NOT NULL,
  `area_code` varchar(32) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `modify_time` varchar(20) DEFAULT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `team_code` varchar(100) DEFAULT NULL,
  `team_level` varchar(5) DEFAULT NULL,
  `team_name` varchar(200) DEFAULT NULL,
  `team_status` varchar(5) DEFAULT NULL,
  `team_type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_team` */

insert  into `fw_team`(`team_id`,`area_code`,`create_time`,`creator`,`modify_time`,`parent_code`,`parent_name`,`remark`,`team_code`,`team_level`,`team_name`,`team_status`,`team_type`) values ('SysTeam',NULL,'2017-07-07 15:39:32',NULL,'2017-07-07 15:39:32',NULL,NULL,NULL,'SysTeam','1','系统团队','1',NULL);

/*Table structure for table `fw_team_module` */

DROP TABLE IF EXISTS `fw_team_module`;

CREATE TABLE `fw_team_module` (
  `team_id` varchar(32) NOT NULL,
  `module_id` varchar(32) NOT NULL,
  PRIMARY KEY (`team_id`,`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_team_module` */

/*Table structure for table `fw_team_user` */

DROP TABLE IF EXISTS `fw_team_user`;

CREATE TABLE `fw_team_user` (
  `user_id` varchar(32) NOT NULL,
  `team_id` varchar(128) NOT NULL,
  PRIMARY KEY (`user_id`,`team_id`),
  KEY `FKs7owiytnlruyuqdawf5ufg3nb` (`team_id`),
  CONSTRAINT `FKqjlskiijcx2nftcin4cukghys` FOREIGN KEY (`user_id`) REFERENCES `fw_user` (`user_id`),
  CONSTRAINT `FKs7owiytnlruyuqdawf5ufg3nb` FOREIGN KEY (`team_id`) REFERENCES `fw_team` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_team_user` */

insert  into `fw_team_user`(`user_id`,`team_id`) values ('1','SysTeam'),('402880ef641c711001641c7641d20000','SysTeam');

/*Table structure for table `fw_tip_message` */

DROP TABLE IF EXISTS `fw_tip_message`;

CREATE TABLE `fw_tip_message` (
  `id` varchar(64) NOT NULL,
  `content` varchar(4028) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `from_user_id` varchar(64) DEFAULT NULL,
  `icon_name` varchar(520) DEFAULT NULL,
  `is_valid` varchar(11) DEFAULT NULL,
  `team_id` varchar(64) DEFAULT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `url` varchar(2024) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_tip_message` */

/*Table structure for table `fw_trace_log` */

DROP TABLE IF EXISTS `fw_trace_log`;

CREATE TABLE `fw_trace_log` (
  `team_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `url` varchar(2048) DEFAULT NULL,
  `op_cont` varchar(512) DEFAULT NULL,
  `op_cont_type` varchar(16) DEFAULT NULL,
  `request_cont` longtext,
  `response_cont` longtext,
  `trace_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ip` varchar(32) DEFAULT NULL,
  `ua` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*Table structure for table `fw_user` */

DROP TABLE IF EXISTS `fw_user`;

CREATE TABLE `fw_user` (
  `user_id` varchar(32) NOT NULL,
  `city_id` varchar(11) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `face_img` varchar(255) DEFAULT NULL,
  `modify_time` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `user_cn_name` varchar(200) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_pwd` varchar(32) DEFAULT NULL,
  `user_status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKj723kj8nr7ngc7lkpfs1hr5m6` (`user_name`),
  UNIQUE KEY `UK8ol69gxxxm3y4nwr3gx9k8npv` (`user_cn_name`),
  UNIQUE KEY `UKh4ihb710jtek8puta3kckphw7` (`phone`),
  UNIQUE KEY `UK492nr9uklpa6nl251ixbmajdk` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fw_user` */

insert  into `fw_user`(`user_id`,`city_id`,`create_time`,`creator`,`email`,`face_img`,`modify_time`,`phone`,`remark`,`user_cn_name`,`user_name`,`user_pwd`,`user_status`) values ('1',NULL,'2017-06-23 23:12:52','1','sys@163.com','2','2017-06-23 23:12:52','15390089119','2','系统管理员','sys','kRbA/Q3PhLGyGF6enNlTnA==','1'),('402880ef641c711001641c7641d20000',NULL,'2018-06-20 17:11:09','1','test@qq.com',NULL,'2018-06-20 17:11:09','15389909889',NULL,'test','test','kRbA/Q3PhLGyGF6enNlTnA==','1');

/*Table structure for table `kun_trace_log` */

DROP TABLE IF EXISTS `kun_trace_log`;

CREATE TABLE `kun_trace_log` (
  `user_id` varchar(16) NOT NULL,
  `ip_addr` varchar(32) DEFAULT NULL,
  `uri` varchar(128) DEFAULT NULL,
  `req_method` varchar(16) DEFAULT NULL,
  `req_params` varchar(256) DEFAULT NULL,
  `resp_header` varchar(128) DEFAULT NULL,
  `resp_header_len` int(11) DEFAULT NULL,
  `resp_body` varchar(1024) DEFAULT NULL,
  `resp_body_len` int(11) DEFAULT NULL,
  `resp_cont_type` varchar(64) DEFAULT NULL,
  `resp_time` int(11) DEFAULT NULL,
  `create_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `referer` varchar(128) DEFAULT NULL,
  `serv_host` varchar(32) DEFAULT NULL,
  `user_agent` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `kun_trace_log` */

/*Table structure for table `roleteamvo` */

DROP TABLE IF EXISTS `roleteamvo`;

CREATE TABLE `roleteamvo` (
  `value` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `teamname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roleteamvo` */

/*Table structure for table `valuelabelvo` */

DROP TABLE IF EXISTS `valuelabelvo`;

CREATE TABLE `valuelabelvo` (
  `value` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `valuelabelvo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
