create database if not exists `spider_junior` default character set 'utf8';
use `spider_junior`;
create table if not exists `item`(
	`id` int(11) NOT NULL auto_increment,
	`title` varchar(64) NOT NULL,
	`rank` int(11) NOT NULL,
	`index` int(11) NOT NULL,
	`director_id` int(11) NULL,
	`data_source_id` int(11) NOT NULL,
	`area` varchar(64) NULL,
	`task_id` int(11) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `item_actors`(
	`id` int(11) NOT NULL auto_increment,
	`item_id` int(11) NOT NULL,
	`actor_id` int(11) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `item_categories`(
	`id` int(11) NOT NULL auto_increment,
	`item_id` int(11) NOT NULL,
	`category_id` int(11) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `person`(
	`id` int(11) NOT NULL auto_increment,
	`name` varchar(64) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `category`(
	`id` int(11) NOT NULL auto_increment,
	`name` varchar(64) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `company`(
	`id` int(11) NOT NULL auto_increment,
	`name` varchar(64) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `type`(
	`id` int(11) NOT NULL auto_increment,
	`name` varchar(64) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;

create table if not exists `data_source`(
	`id` int(11) NOT NULL auto_increment,
	`name` varchar(64) NOT NULL,
	`company_id` int(11) NOT NULL,
	`url` varchar(256) NOT NULL,
	`type_id` int(11) NOT NULL,
	primary key(`id`)
)ENGINE=InnodDb DEFAULT CHARSET=utf8;
ALTER TABLE `data_source` ADD COLUMN `bean` VARCHAR(64) NULL  AFTER `type_id` , ADD COLUMN `data_sourcecol` VARCHAR(45) NOT NULL  AFTER `bean` ;

create table if not exists `task`(
	`id` int(11) NOT NULL auto_increment,
	`created` datetime NOT NULL ,
	`target` datetime NOT NULL ,
	primary key(`id`)
)ENGINE=InnoDb DEFAULT CHARSET=utf8;

GRANT ALL PRIVILEGES ON `spider_junior`.* TO 'rosicky'@'localhost' identified by 'love';