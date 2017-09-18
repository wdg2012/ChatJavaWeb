数据库初始化脚本
创建数据库
CREATE TABLE seckill(seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
name VARCHAR(120)NOT NULL ,
number int not null ,
start_time timestamp not null ,
end_time timestamp not null,
create_time timestamp not null DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time))ENGINE=INNODB AUTO_INCREMENT =1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表'


INSERT INTO seckill(name,number,start_time,end_time) VALUES
('1000元秒杀iphone6',100,'2017-07-03 00:00:00','2017-07-05 00:00:00'),
('500元秒杀ipad2',200,'2017-07-03 00:00:00','2017-07-05 00:00:00'),
('300元秒杀小米4',300,'2017-07-03 00:00:00','2017-07-05 00:00:00'),
('200元秒杀红米note',400,'2017-07-03 00:00:00','2017-07-05 00:00:00');


//创建用户表
