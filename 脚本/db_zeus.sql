/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : db_zeus

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 02/02/2019 13:17:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作名称',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作编码',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-操作表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES (1, '新增', 'create', b'1', '2018-12-09 10:34:24', 0, '2018-12-09 10:34:24', 0);
INSERT INTO `sys_operation` VALUES (2, '删除', 'delete', b'1', '2018-12-09 10:34:47', 0, '2018-12-09 10:35:06', 0);
INSERT INTO `sys_operation` VALUES (3, '修改', 'update', b'1', '2018-12-09 10:34:55', 0, '2018-12-09 10:35:14', 0);
INSERT INTO `sys_operation` VALUES (4, '查看', 'query', b'1', '2018-12-09 10:35:05', 0, '2018-12-09 10:35:09', 0);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'url地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限描述',
  `priority` int(11) NOT NULL DEFAULT 0 COMMENT '优先级',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '主页', 'fa fa-home', '/dashboard', '仪表盘', 0, b'1', '2018-12-09 10:40:18', 0, '2018-12-09 11:32:07', 0);
INSERT INTO `sys_permission` VALUES (2, 0, '系统管理', '', '#', '', 1, b'1', '2018-12-09 11:28:18', 0, '2018-12-09 11:28:31', 0);
INSERT INTO `sys_permission` VALUES (3, 2, '用户管理', '', '/userManage', '', 0, b'1', '2019-01-31 21:57:59', 0, '2019-01-31 21:58:06', 0);
INSERT INTO `sys_permission` VALUES (4, 2, '角色管理', '', '/roleManage', '', 0, b'1', '2019-01-31 21:58:26', 0, '2019-01-31 21:58:33', 0);

-- ----------------------------
-- Table structure for sys_permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_operation`;
CREATE TABLE `sys_permission_operation`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `permission_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '权限id',
  `operation_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '操作id',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-权限 操作表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色类型',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色描述',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '?????', 'ROLE_SUPER_ADMIN', '??????', b'1', '2018-12-08 23:04:02', 1, '2019-02-02 13:04:30', 1);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '菜单id',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-角色菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1, b'1', '2018-12-09 11:27:55', 0, '2018-12-09 11:27:55', 0);
INSERT INTO `sys_role_permission` VALUES (2, 1, 2, b'1', '2018-12-09 11:28:04', 0, '2018-12-09 11:28:36', 0);
INSERT INTO `sys_role_permission` VALUES (3, 1, 3, b'1', '2019-01-31 21:59:10', 0, '2019-01-31 21:59:15', 0);
INSERT INTO `sys_role_permission` VALUES (4, 1, 4, b'1', '2019-01-31 21:59:23', 0, '2019-01-31 21:59:23', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `telephone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电话号码',
  `last_login_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$tXaX6GwvMfYnQA4YNdUmi.yhAiS.pWYS/18qZ2Bbs.zS1CmRPFVvm', '', '17777777777', '2018-12-08 22:02:17', b'1', '2018-12-08 22:02:17', 1, '2019-02-02 12:59:29', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色id',
  `is_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据创建用户',
  `last_change_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `last_change_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据最后修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_last_change_time`(`last_change_time`) USING BTREE COMMENT '最后修改时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'zeus-用户 角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, b'1', '2018-12-08 23:03:41', 0, '2018-12-08 23:03:41', 0);

SET FOREIGN_KEY_CHECKS = 1;
