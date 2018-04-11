# 数据库建表语句
# 所有字段全部是NOT NULL 引擎使用InnoDB
# 以下是创建数据库及账号的语句
# CREATE DATABASE IF NOT EXISTS `osport` DEFAULT CHARACTER SET utf8mb4;
# grant all privileges on fee.* to 'fee'@'%' identified by 'fee..zz@#$1';
#flush privileges;
#use osport;
#set names utf8;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE admin(
    `adminid` varchar(100) NOT NULL COMMENT '管理员主键id',
    `adminname` varchar(100) NOT NULL COMMENT '管理员姓名',
    `adminpassword` varchar(100) NOT NULL COMMENT '管理员密码',
    `adminregistertime` timestamp NOT NULL default NOW() COMMENT '注册时间',
    `admintel` varchar(20) NOT NULL COMMENT '管理员手机',
primary key(`adminid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

CREATE TABLE user(
    `userid` varchar(100) NOT NULL COMMENT '用户主键id',
    `username` varchar(100) NOT NULL COMMENT '用户昵称',
    `password` varchar(100) NOT NULL COMMENT '用户密码',
    `tel` varchar(20) NOT NULL COMMENT '用户手机',
    `registerTime` timestamp NOT NULL default NOW() COMMENT '注册时间',
    `userimg` varchar(100) COMMENT '用户图片路径',
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
	primary key(`shopid`)
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
    `triptrading` INT NOT NULL default 0 COMMENT '出行团成交量',
    `tripprovice` varchar(10) NOT NULL COMMENT '省',
    `tripcity` varchar(10) NOT NULL COMMENT '市',
    `shopid` varchar(100) NOT NULL COMMENT '商店外键',
    primary key(`tripid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团表';

CREATE TABLE triptime(
    `triptimeid` varchar(100) NOT NULL COMMENT '出行团时间主键id',
    `triptime` varchar(100) NOT NULL COMMENT '出行团时间',
    `triptimemaxpeople` int NOT NULL COMMENT '出行团时间最大人数限制',
    `tripid` varchar(100) NOT NULL COMMENT '对应的出行团id',
    primary key(`triptimeid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团时间表';


CREATE TABLE triporder(
    `triporderid` varchar(100) NOT NULL COMMENT '出行团订单主键id',
    `triporderitemid` varchar(100) NOT NULL  COMMENT '出行团订单详情主键id',
    `tripordertotal` double NOT NULL COMMENT '出行团订单总计',
    `tripordertime` timestamp NOT NULL default NOW() COMMENT '下单时间',
    `userid` varchar(100) NOT NULL COMMENT '用户外键',
    `triporderstatus` varchar(20) default '已报名' NOT NULL COMMENT '订单状态',
    `username` varchar(100) NOT NULL COMMENT '用户名',
    primary key(`triporderid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团订单表';

CREATE TABLE triporderitem(
    `triporderitemid` varchar(100) NOT NULL COMMENT '出行团订单详情主键id',
    `tripid` varchar(100) NOT NULL COMMENT '出行团主键id',
    `triptimeid` varchar(100) NOT NULL COMMENT '出行时间主键id',
    `people` INT NOT NULL COMMENT '购买人数',
    primary key(`triporderitemid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行团订单详情表';

CREATE TABLE club(
    `clubid` varchar(100) NOT NULL COMMENT '部落主键id',
    `clubname` varchar(100) NOT NULL COMMENT '部落名称',
    `clubtab` varchar(100) NOT NULL COMMENT '部落标签',
    `clubimg` varchar(100) COMMENT '部落头像',
    `clubpeople` int default 0 COMMENT '部落人数',
    `clubowner` varchar(100) NOT NULL COMMENT '部落管理者',
    `clubpublishtime` timestamp NOT NULL default NOW() COMMENT '部落成立时间',
    primary key(`clubid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部落表';

CREATE TABLE clubdiary(
    `clubdiaryid` varchar(100) NOT NULL COMMENT '部落事件主键id',
    `clubdiaryimg` varchar(100) COMMENT '部落事件图片',
    `clubdiarytitle` varchar(100) NOT NULL COMMENT '部落事件标题',
    `clubdiarycontent` varchar(300) NOT NULL COMMENT '部落事件内容',
    `clubdiarytime` timestamp NOT NULL default NOW() COMMENT '部落事件发布时间',
    `userid` varchar(100) NOT NULL COMMENT '用户主键id',
    `username` varchar(100) NOT NULL COMMENT '用户名字',
    `clubid` varchar(100) NOT NULL COMMENT '部落主键id',
    primary key(`clubdiaryid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部落事件表';

CREATE TABLE clubactivity(
    `clubactivityid` varchar(100) NOT NULL COMMENT '部落活动主键id',
    `clubactivityimg` varchar(100) COMMENT '部落活动图片',
    `clubactivitytitle` varchar(100) NOT NULL COMMENT '部落活动标题',
    `clubactivitycontent` varchar(300) NOT NULL COMMENT '部落活动内容',
    `clubactivitytime` timestamp NOT NULL default NOW() COMMENT '部落活动发布时间',
    `clubactivitypeople` int default 0 COMMENT '部落活动参加人数',
    `clubid` varchar(100) NOT NULL COMMENT '部落主键id',
    primary key(`clubactivityid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部落活动表';

CREATE TABLE clubuseractivity(
    `clubuseractivityid` varchar(100) NOT NULL COMMENT '部落活动与参与用户主键id',
    `clubactivityid` varchar(100) NOT NULL COMMENT '部落活动主键id',
    `userid` varchar(100) NOT NULL COMMENT '用户id',
    primary key(`clubuseractivityid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部落活动参与用户表';

CREATE TABLE clubuseritem(
    `clubuseritemid` varchar(100) NOT NULL COMMENT '部落与用户关系主键id',
    `clubid` varchar(100) NOT NULL COMMENT '部落id',
    `userid` varchar(100) NOT NULL COMMENT '用户id',
    primary key(`clubuseritemid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部落与用户关系表';

CREATE TABLE evaluate(
    `evaluateid` varchar(100) NOT NULL COMMENT '评价主键id',
    `tripid` varchar(100) NOT NULL COMMENT 'tripid',
    `triporderid` varchar(100) NOT NULL COMMENT '订单id',
    `userid` varchar(100) NOT NULL COMMENT '用户id',
    `content` varchar(200) NOT NULL COMMENT '评价内容',
    `evaluatetime` timestamp NOT NULL default NOW() COMMENT '评价时间',
    primary key(`evaluateid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行评价表';


