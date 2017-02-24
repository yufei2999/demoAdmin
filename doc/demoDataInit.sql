/*
SQLyog Trial v10.12 
MySQL - 5.0.22-community : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `demo`;

/*Table structure for table `sys_func` */

DROP TABLE IF EXISTS `sys_func`;

CREATE TABLE `sys_func` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `func_name` varchar(50) NOT NULL COMMENT '权限名称',
  `func_code` varchar(200) NOT NULL COMMENT '权限code',
  `url` varchar(200) NOT NULL COMMENT '权限url',
  `parent_id` varchar(32) NOT NULL COMMENT '父权限id',
  `parent_ids` varchar(500) NOT NULL COMMENT '父权限id全路径',
  `sort` int(11) NOT NULL COMMENT '排序',
  `remark` text COMMENT '备注',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `last_modifier` varchar(32) default NULL COMMENT '最后修改人',
  `last_modify_time` datetime default NULL COMMENT '最后修改时间',
  `deleted` int(11) NOT NULL COMMENT '删除标记：0删除；1正常',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统权限表';

/*Data for the table `sys_func` */

insert  into `sys_func`(`id`,`func_name`,`func_code`,`url`,`parent_id`,`parent_ids`,`sort`,`remark`,`creator`,`create_time`,`last_modifier`,`last_modify_time`,`deleted`) values ('1d95d68b79a44451b25db9ba9b247e39','系统管理','004','/sys*/.*','0','-1',0,'系统管理','3a6b23aa347f4b28be95c8066a730633','2016-11-03 18:42:08','3a6b23aa347f4b28be95c8066a730633','2016-11-04 20:36:26',1),('1b5f2681d1f244f49457cfa3acaaebb2','用户管理','004001','/sysUser/list','1d95d68b79a44451b25db9ba9b247e39','1d95d68b79a44451b25db9ba9b247e39',101,'用户管理','3a6b23aa347f4b28be95c8066a730633','2016-11-04 21:37:34','3a6b23aa347f4b28be95c8066a730633','2016-11-04 21:37:34',1),('f497fc476bee476ea42cda53e0098671','角色管理','004002','/sysRole/list','1d95d68b79a44451b25db9ba9b247e39','1d95d68b79a44451b25db9ba9b247e39',102,'角色管理','3a6b23aa347f4b28be95c8066a730633','2016-11-04 21:39:07','3a6b23aa347f4b28be95c8066a730633','2016-11-04 21:39:07',1),('1e494db6c0e346db9e5a46acbcb04b41','权限管理','004003','/sysFunc/listTree','1d95d68b79a44451b25db9ba9b247e39','1d95d68b79a44451b25db9ba9b247e39',103,'权限管理','3a6b23aa347f4b28be95c8066a730633','2016-11-04 21:43:05','3a6b23aa347f4b28be95c8066a730633','2016-11-04 21:43:05',1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `remark` text NOT NULL COMMENT '备注',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `last_modifier` varchar(32) default NULL COMMENT '最后修改人',
  `last_modify_time` datetime default NULL COMMENT '最后修改时间',
  `deleted` int(11) NOT NULL COMMENT '删除标记：0删除；1正常',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`remark`,`creator`,`create_time`,`last_modifier`,`last_modify_time`,`deleted`) values ('f1c25c4fd11f41829c7f1e84ce577c59','超级管理员','超级管理员','3a6b23aa347f4b28be95c8066a730633','2016-11-02 09:47:41','3a6b23aa347f4b28be95c8066a730633','2016-11-02 09:47:41',1),('bcbab9f10f8d4671a5f78febcbf1ff83','系统管理','系统管理','3a6b23aa347f4b28be95c8066a730633','2016-11-02 09:49:09','3a6b23aa347f4b28be95c8066a730633','2016-11-02 09:49:09',1);

/*Table structure for table `sys_role_func` */

DROP TABLE IF EXISTS `sys_role_func`;

CREATE TABLE `sys_role_func` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `func_id` varchar(32) NOT NULL COMMENT '权限id',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `last_modifier` varchar(32) default NULL COMMENT '最后修改人',
  `last_modify_time` datetime default NULL COMMENT '最后修改时间',
  `deleted` int(11) NOT NULL COMMENT '删除标记：0删除；1正常',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*Data for the table `sys_role_func` */

insert  into `sys_role_func`(`id`,`role_id`,`func_id`,`creator`,`create_time`,`last_modifier`,`last_modify_time`,`deleted`) values ('e1c49f3b19f0431b96d7f275408fe783','f1c25c4fd11f41829c7f1e84ce577c59','f497fc476bee476ea42cda53e0098671','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56',1),('40e64e9477184ac0ab6c51afa6a3a8fc','bcbab9f10f8d4671a5f78febcbf1ff83','f497fc476bee476ea42cda53e0098671','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40',1),('e4d060e1db644dcda66a21c9dfb8b923','f1c25c4fd11f41829c7f1e84ce577c59','1b5f2681d1f244f49457cfa3acaaebb2','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56',1),('1304df9bd59c4cb295ef00c0257a32db','f1c25c4fd11f41829c7f1e84ce577c59','1d95d68b79a44451b25db9ba9b247e39','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56',1),('43107b7e46e1414695e8a3f658f47ff5','f1c25c4fd11f41829c7f1e84ce577c59','1e494db6c0e346db9e5a46acbcb04b41','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:26:56',1),('9f0d10284f9648fd924cb359775e58ef','bcbab9f10f8d4671a5f78febcbf1ff83','1b5f2681d1f244f49457cfa3acaaebb2','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40',1),('e316cac76f784f59bf00fb2c4dac7927','bcbab9f10f8d4671a5f78febcbf1ff83','1d95d68b79a44451b25db9ba9b247e39','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40',1),('53ed121a69024ac7821c0c305f3bd14b','bcbab9f10f8d4671a5f78febcbf1ff83','1e494db6c0e346db9e5a46acbcb04b41','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:27:40',1);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `show_name` varchar(100) NOT NULL COMMENT '显示名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `mobile` varchar(20) default NULL COMMENT '手机号',
  `status` int(11) default NULL COMMENT '状态：0停用；1启用',
  `last_login_ip` varchar(20) default NULL COMMENT '最后登录ip',
  `last_login_time` datetime default NULL COMMENT '最后登录时间',
  `remark` text COMMENT '备注',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `last_modifier` varchar(32) default NULL COMMENT '最后修改人',
  `last_modify_time` datetime default NULL COMMENT '最后修改时间',
  `deleted` int(11) NOT NULL COMMENT '删除标记：0删除；1正常',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`show_name`,`password`,`mobile`,`status`,`last_login_ip`,`last_login_time`,`remark`,`creator`,`create_time`,`last_modifier`,`last_modify_time`,`deleted`) values ('02916be7f2254ceb960a1920d6fcbb04','1','1','E10ADC3949BA59ABBE56E057F20F883E','1',1,'127.0.0.1','2016-11-04 22:45:59','改改','1',NULL,'3a6b23aa347f4b28be95c8066a730633','2016-10-31 17:46:35',1),('2997be6bbd2b4d3d8f22e0d403462845','2','2','E10ADC3949BA59ABBE56E057F20F883E','2',1,'127.0.0.1','2016-11-04 22:53:38','2','2',NULL,'2',NULL,2),('7ce0ea61ae7f4eb392ad00cbc4aab41a','3','3','E10ADC3949BA59ABBE56E057F20F883E','3',1,'3','2016-10-28 09:35:58','3','3',NULL,'3',NULL,3),('4b1e6bba4b3547be89d512a4ba065238','5','5','E10ADC3949BA59ABBE56E057F20F883E','5',1,'5','2016-10-28 00:00:00','5','5',NULL,'5',NULL,1),('3a6b23aa347f4b28be95c8066a730633','admin','admin','E10ADC3949BA59ABBE56E057F20F883E','13512345678',1,'127.0.0.1','2016-11-04 22:57:31','中文','02916be7f2254ceb960a1920d6fcbb04','2016-10-31 16:23:13','02916be7f2254ceb960a1920d6fcbb04','2016-10-31 16:23:13',1),('566ace5c7a324abe967f7aac6825851f','6','6','1679091c5a880faf6fb5e6087eb1b2dc','6',0,NULL,NULL,'6','3a6b23aa347f4b28be95c8066a730633','2016-11-04 22:58:03','3a6b23aa347f4b28be95c8066a730633','2016-11-04 23:02:13',1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `last_modifier` varchar(32) default NULL COMMENT '最后修改人',
  `last_modify_time` datetime default NULL COMMENT '最后修改时间',
  `deleted` int(11) NOT NULL COMMENT '删除标记：0删除；1正常',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`creator`,`create_time`,`last_modifier`,`last_modify_time`,`deleted`) values ('2a9504e4a2e64db69d99b6b9cedf9b24','02916be7f2254ceb960a1920d6fcbb04','bcbab9f10f8d4671a5f78febcbf1ff83','3a6b23aa347f4b28be95c8066a730633','2016-11-04 11:46:42','3a6b23aa347f4b28be95c8066a730633','2016-11-04 11:46:42',1),('295c6736f2b640d7a453b5f131a2dee2','3a6b23aa347f4b28be95c8066a730633','f1c25c4fd11f41829c7f1e84ce577c59','3a6b23aa347f4b28be95c8066a730633','2016-11-04 11:47:21','3a6b23aa347f4b28be95c8066a730633','2016-11-04 11:47:21',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
