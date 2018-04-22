-- 数据库初始化脚本

-- 创建数据库
create database seckill;

-- 使用数据库
use seckill;

-- 创建秒杀库存表
create table seckill(
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
	name varchar(120) NOT NULL COMMENT '商品名称',
	number int NOT NULL COMMENT '库存数量',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀创建时间',
	start_time timestamp NOT NULL COMMENT '秒杀开启时间',
	end_time timestamp NOT NULL COMMENT '秒杀结束时间',
	PRIMARY KEY(seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into 
	seckill(name,number,start_time,end_time)
values
	('5000元秒杀iphoneX',100,'2018-04-15 00:00:00','2018-04-16 00:00:00'),
	('1000元秒杀红米note',1000,'2018-04-18 00:00:00','2018-04-23 00:00:00'),
	('4000元秒杀华为p10',800,'2018-04-16 00:00:00','2018-04-30 00:00:00'),
	('200元秒杀小米4',300,'2018-04-20 00:00:00','2018-05-3 00:00:00'),
	('1000元秒杀mac air',900,'2018-04-16 00:00:00','2018-05-16 00:00:00');
	
insert into 
	seckill(name,number,start_time,end_time)
values
	('0元秒杀戴尔鼠标',100,'2018-04-30 00:00:00','2018-05-16 00:00:00')
	
-- 秒杀成功明细表
-- 用户登录认证相关的信息
create table success_killed(
	seckill_id bigint NOT NULL COMMENT '商品库存id',	
	user_phone bigint NOT NULL COMMENT '用户手机号',
	state tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1：无效   0：成功  1：已付款   2：已发货',
	create_time timestamp NOT NULL COMMENT '秒杀成功创建时间',
	PRIMARY KEY(seckill_id,user_phone),
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';