# 数据库建表语句
# 所有字段全部是NOT NULL 引擎使用InnoDB
# 以下是创建数据库及账号的语句
# CREATE DATABASE IF NOT EXISTS `fee` DEFAULT CHARACTER SET utf8mb4;
# grant all privileges on fee.* to 'fee'@'%' identified by 'fee..zz@#$1';
#flush privileges;
#use fee;
#set names utf8;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE admin(
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `login_name` varchar(100) NOT NULL COMMENT '登录名称',
    `true_name` varchar(100) NOT NULL COMMENT '真实名称',
    `type` varchar(10) NOT NULL COMMENT '管理员标识,admin管理员，cashier收银员',
    `password` varchar(100) NOT NULL COMMENT '用户密码',
    `last_login_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '最近登录时间',
    `reg_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '注册时间',
primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';
