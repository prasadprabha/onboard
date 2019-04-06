CREATE DATABASE onboard CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `onboard`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE onboard.`role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO role VALUES (1,'admin');

CREATE TABLE `onboard`.`user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into onboard.user (id,username,password) values (5,"prasad@infosys.com","password123");
insert into onboard.user (id,username,password) values (6,"jyothi@infosys.com","password123");
insert into onboard.user (id,username,password) values (7,"aswathy@infosys.com","password123");
insert into onboard.user (id,username,password) values (8,"nima@infosys.com","password123");
insert into onboard.user (id,username,password) values (9,"jojy@infosys.com","password123");


insert into role (id,name) values (6,"onboardingteam");
insert into user_role (user_id,role_id) values (6,6);

insert into role (id,name) values (7,"cryptocardallocationteam");
insert into user_role (user_id,role_id) values (7,7);

insert into role (id,name) values (8,"bgcheck");
insert into user_role (user_id,role_id) values (8,8);

insert into role (id,name) values (9,"admin");
insert into user_role (user_id,role_id) values (9,1);