CREATE TABLE `User` (
`userId`  int NOT NULL AUTO_INCREMENT ,
`account`  varchar(20) NOT NULL DEFAULT '' ,
`password`  varchar(255) NOT NULL ,
`username`  varchar(255) NOT NULL ,
`status`  int(11) NOT NULL DEFAULT 0 ,
`create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`userId`),
UNIQUE KEY `account` (`account`)
)CHARSET=utf8
;

