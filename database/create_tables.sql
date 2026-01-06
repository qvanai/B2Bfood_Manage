-- 创建数据库
CREATE DATABASE IF NOT EXISTS `B2B_food-manage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `B2B_food-manage`;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `user_type` INT(1) DEFAULT NULL COMMENT '用户类型: 1-采购商, 2-供应商',
  `status` INT(1) DEFAULT '1' COMMENT '用户状态: 1-正常, 2-禁用',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 创建角色表
CREATE TABLE IF NOT EXISTS `roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '角色描述',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 创建钱包表
CREATE TABLE IF NOT EXISTS `wallets` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL COMMENT '所属用户ID',
  `balance` DECIMAL(10,2) DEFAULT '0.00' COMMENT '当前余额(元)',
  `status` INT(1) DEFAULT '1' COMMENT '钱包状态: 1-正常, 2-冻结',
  `last_update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `wallets_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='钱包表';

-- 创建交易记录表
CREATE TABLE IF NOT EXISTS `transaction_records` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `wallet_id` INT(11) NOT NULL COMMENT '所属钱包ID',
  `type` INT(1) NOT NULL COMMENT '交易类型: 1-充值, 2-提现, 3-支付',
  `amount` DECIMAL(10,2) NOT NULL COMMENT '交易金额(元)',
  `balance_after` DECIMAL(10,2) NOT NULL COMMENT '交易后余额(元)',
  `payment_method` VARCHAR(50) DEFAULT NULL COMMENT '支付方式(仅充值有此参数)',
  `bank_card_number` VARCHAR(50) DEFAULT NULL COMMENT '银行卡号(仅提现有此参数)',
  `payee_id` INT(11) DEFAULT NULL COMMENT '收款方ID(仅支付有此参数)',
  `transaction_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '交易备注',
  PRIMARY KEY (`id`),
  KEY `wallet_id` (`wallet_id`),
  CONSTRAINT `transaction_records_ibfk_1` FOREIGN KEY (`wallet_id`) REFERENCES `wallets` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='交易记录表';