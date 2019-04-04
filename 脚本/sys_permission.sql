/*
Navicat MySQL Data Transfer

Source Server         : gimo-dev
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : db_zeus

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-04-04 22:54:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级id',
  `name` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '权限名称',
  `icon` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '图标',
  `uri` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '资源uri',
  `code` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '权限编码',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0 - 菜单 1 - 按钮',
  `description` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '权限描述',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '优先级',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据创建用户',
  `change_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `change_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`),
  KEY `idx_last_change_time` (`change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COMMENT='zeus-权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '主页', 'fa fa-home', '/dashboard', '', '0', '仪表盘', '0', '', '2018-12-09 10:40:18', '0', '2018-12-09 11:32:07', '0');
INSERT INTO `sys_permission` VALUES ('2', '0', '系统管理', '', '#', '', '0', '', '1', '', '2018-12-09 11:28:18', '0', '2018-12-09 11:28:31', '0');
INSERT INTO `sys_permission` VALUES ('3', '2', '用户管理', '', '/userManage', '', '0', '', '1', '', '2018-12-11 22:51:20', '0', '2018-12-18 20:40:42', '0');
INSERT INTO `sys_permission` VALUES ('4', '2', '系统监控', '', '/@in', '', '0', '', '99', '', '2018-12-11 22:51:20', '0', '2019-01-12 22:12:14', '0');
INSERT INTO `sys_permission` VALUES ('5', '0', '示例界面', '', '/example', '', '0', '', '0', '', '2018-12-18 20:41:12', '0', '2019-01-12 22:09:56', '0');
INSERT INTO `sys_permission` VALUES ('6', '2', '角色管理', '', '/roleManage', '', '0', '', '2', '', '2019-01-12 22:12:05', '0', '2019-01-12 22:16:14', '0');
INSERT INTO `sys_permission` VALUES ('7', '2', '菜单管理', '', '/menuManage', '', '0', '', '3', '', '2019-03-31 15:10:42', '0', '2019-03-31 15:10:42', '0');
