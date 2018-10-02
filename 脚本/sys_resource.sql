/*
Navicat MySQL Data Transfer

Source Server         : gimo-dev
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : db_zeus

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-10-02 08:41:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `is_leaf` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否叶子资源',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '资源名称',
  `type` varchar(100) NOT NULL DEFAULT '' COMMENT '资源类型',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '资源路径',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '资源描述',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '优先级',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级资源id',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据创建用户',
  `last_change_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`),
  KEY `idx_last_change_time` (`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='zeus-资源表';
