/*
Navicat MySQL Data Transfer

Source Server         : gimo-dev
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : db_zeus

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-10-02 08:41:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_resource`;
CREATE TABLE `sys_permission_resource` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限id',
  `resource_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '资源id',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据创建用户',
  `last_change_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`),
  KEY `idx_last_change_time` (`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='zeus-权限资源表';
