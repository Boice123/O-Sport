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

CREATE TABLE user(
    `userid` varchar(100) NOT NULL COMMENT '用户主键id',
    `username` varchar(100) NOT NULL COMMENT '用户昵称',
    `password` varchar(100) NOT NULL COMMENT '用户密码',
    `tel` varchar(20) NOT NULL COMMENT '用户手机',
    `registerTime` timestamp NOT NULL default NOW() COMMENT '注册时间',
    `realname` varchar(20) COMMENT '真实姓名',
    `realid` varchar(20) COMMENT '身份证号',
primary key(`userid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE shop(
    `shopid` varchar(100) NOT NULL COMMENT '商店主键id',
    `shopname` varchar(100) NOT NULL COMMENT '商店名称',
    `shopimg` varchar(100) NOT NULL COMMENT '商店图片路径',
    `shopstatus` INT NOT NULL default 1 COMMENT '商店状态',
    `shoppublishtime` timestamp NOT NULL default NOW() COMMENT '成立时间',
		`userid` varchar(100) NOT NULL COMMENT '用户外键',
		primary key(`shopid`),
		FOREIGN KEY (userid) REFERENCES user ( userid )
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商店表';

CREATE TABLE trip(
    `tripid` varchar(100) NOT NULL COMMENT '出行团主键id',
    `tripname` varchar(100) NOT NULL COMMENT '出行团名称',
    `tripdescription` varchar(100) NOT NULL COMMENT '出行团描述',
    `tripnotice` varchar(100) NOT NULL COMMENT '出行团注意事项',
    `tripprice` double NOT NULL COMMENT '出行团价钱',
    `maxpeople` INT NOT NULL COMMENT '出行最大人数限制',
    `trippublishtime` timestamp NOT NULL default NOW() COMMENT '发布时间',
    `tripimg` varchar(100) NOT NULL COMMENT '出行团图片',
    `shopid` varchar(100) NOT NULL COMMENT '商店外键',
    primary key(`tripid`),
    FOREIGN KEY (shopid) REFERENCES shop ( shopid )
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团表';

CREATE TABLE triporder(
    `triporderid` varchar(100) NOT NULL COMMENT '出行团订单主键id',
    `triporderitemid` INT NOT NULL  COMMENT '出行团订单详情主键id',
    `tripordertotal` double NOT NULL COMMENT '出行团订单总计',
    `triporderime` timestamp NOT NULL default NOW() COMMENT '下单时间',
    `userid` INT NOT NULL COMMENT '用户外键',
    primary key(`triporderid`),
    FOREIGN KEY (userid) REFERENCES user ( userid )
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团订单表';

CREATE TABLE triporderitem(
    `triporderitemid` varchar(100) NOT NULL COMMENT '出行团订单详情主键id',
    `tripid` INT NOT NULL COMMENT '出行团主键id',
    `people` INT NOT NULL COMMENT '购买人数',
    primary key(`triporderitemid`),
    FOREIGN KEY (tripid) REFERENCES trip ( tripid )
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团订单详情表';

-- CREATE TABLE tripimg(
--     `tripimgid` INT NOT NULL AUTO_INCREMENT COMMENT '出行团图片id',
--     `tripimg` varchar(100) NOT NULL COMMENT '出行团图片路径',
--     primary key(`tripimgid`)
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团图片表';