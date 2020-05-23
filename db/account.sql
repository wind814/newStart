/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : wind_0415

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-20 17:46:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'wind', 'wind', '2020-04-20 17:15:04', null, null);


privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAoCqlaVqi4LabGpmsYhWNtDUhiuPcB6JLH+6a5AgKpRjooF1F601RdZuDg6lbY45TcsZMT8Ej4YPaGxJ+BN7jPQIDAQABAkBpN7rUfCtV7f/0uJFrGfeuM90mLcxE45PwlUIBnVrI24rsOFiXcvR9/3WIeSHCRhD70SItLaYvuKpwcsAKFJfNAiEA0rrTgH3+ZBcR8gXXyJ2uo4WOtPqcsZ7HcoVYHydZkcMCIQDCkxNCaRhdmO3PvHy56HzG+weFvfNOGxgCnMp5zvFm/wIhAM5cKhZgS6hUdzxcwG0zDPwRJCjSanP+Gy5oql0HuczlAiBAF9p8Qco46qtLhrc/NgoEMaSktZFz5RDfq242+agjWQIhAJFCb8/51LHGVcv6JbgEE4ybezBYF0WcuQmrE+RLl8Uk
publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKAqpWlaouC2mxqZrGIVjbQ1IYrj3AeiSx/umuQICqUY6KBdRetNUXWbg4OpW2OOU3LGTE/BI+GD2hsSfgTe4z0CAwEAAQ==
password:CCR5C89O0Un/tcF3klIy0+iBZr4y02bQs0DKsbO3UwG00grhjsmFPCi41zXK789+2ZkKK3+qEnxK6hC1VJWpyA==



publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIVsH0OcbBVZ52NxckzaD9Utbt3rxLmrryLpXV+0mfb82F7cNFfY6zzfmcvEoIPnSFacsNS1SQWVJH3Z7wcFIMsCAwEAAQ==
password:cpaIVG3A8yKLpVcShz8IySfSUrcCRwOQZR352tv2Zmh2Efs1v8UzlUVaZx/v3nyfBPufxQnvPVlAVFIm1Kh7pQ==