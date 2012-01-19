create database `spider` default character set 'utf8';
use `spider`;
create table `task`(
	`id` int(11) NOT NULL auto_increment,
	`created` datetime NOT NULL comment '创建时间',
	`destination` varchar(128) NOT NULL comment '',
	primary key(`id`)
)ENGINE=InnoDb DEFAULT CHARSET=utf8;