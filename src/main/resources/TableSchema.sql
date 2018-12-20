-- ----------------------------
--  Table structure for `SYS_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(255) NOT NULL,
  `PASSWORD` VARCHAR(255) NOT NULL,
  `PHONE` VARCHAR(11),
  `STATUS` INT(4) DEFAULT '1',
  `REMARK` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `SYS_USER`
-- ----------------------------
BEGIN;
INSERT INTO `SYS_USER` VALUES 
(1, 'admin', admin, '0912123123', '1', ''), 
(2, 'william', 123, '0912123123', '1', ''), 
(3, 'qqq', 123, '0912123123', '1', '');
COMMIT;

-- ----------------------------
--  Table structure for `SYS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL COMMENT '角色名稱',
  `ROLE` VARCHAR(45) DEFAULT NULL,
  `DESCRIPTION` VARCHAR(45) DEFAULT NULL,
  `STATUS` INT(45) DEFAULT NULL COMMENT '0 不可用 1 可用', --9 測試@Transactional
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `SYS_ROLE`
-- ----------------------------
BEGIN;
INSERT INTO `SYS_ROLE` VALUES 
(1, 'Admin', 'admin', 'admin', '1'), 
(2, 'GroupA', 'GroupA', 'GroupA', '1'), 
(3, 'GroupB', 'GroupB', 'GroupB', '1');
COMMIT;

-- ----------------------------
--  Table structure for `SYS_USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_ROLE`;
CREATE TABLE `SYS_USER_ROLE` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` BIGINT(20) NOT NULL,
  `ROLE_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='使用者角色';

-- ----------------------------
--  Records of `SYS_USER_ROLE`
-- ----------------------------
BEGIN;
INSERT INTO `SYS_USER_ROLE` VALUES 
(1, '1', '1'), 
(2, '2', '2'), 
(3, '3', '2');
COMMIT;

-- ----------------------------
--  Table structure for `TOKEN`
-- ----------------------------
DROP TABLE IF EXISTS `TOKEN`;
CREATE TABLE `TOKEN` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` BIGINT(20) NOT NULL,
  `TOKEN` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
