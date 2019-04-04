/*
Navicat MySQL Data Transfer

Source Server         : gimo-dev
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : db_zeus

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-04-04 22:54:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据创建用户',
  `change_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `change_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`),
  KEY `idx_last_change_time` (`change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='zeus-用户 角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '', '2018-12-08 23:03:41', '0', '2018-12-08 23:03:41', '0');
