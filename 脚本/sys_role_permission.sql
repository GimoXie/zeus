/*
Navicat MySQL Data Transfer

Source Server         : gimo-dev
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : db_zeus

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-04-04 22:54:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '菜单id',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据创建用户',
  `change_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `change_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`),
  KEY `idx_last_change_time` (`change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COMMENT='zeus-角色菜单表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1', '', '2018-12-09 11:27:55', '0', '2018-12-09 11:27:55', '0');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2', '', '2018-12-09 11:28:04', '0', '2018-12-09 11:28:36', '0');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '3', '', '2018-12-11 22:53:07', '0', '2018-12-11 22:53:07', '0');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '4', '', '2018-12-11 22:53:12', '0', '2018-12-11 22:53:26', '0');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '5', '', '2019-01-12 22:13:22', '0', '2019-01-12 22:13:26', '0');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '6', '', '2019-01-12 22:13:26', '0', '2019-01-12 22:13:28', '0');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '7', '', '2019-03-31 15:11:23', '0', '2019-03-31 15:11:23', '0');
