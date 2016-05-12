INSERT INTO `category` (`id`,`name`,`order`,`deleted`) VALUES ('ANI','Animation',1,1);
INSERT INTO `category` (`id`,`name`,`order`,`deleted`) VALUES ('COD','Code',2,1);
INSERT INTO `category` (`id`,`name`,`order`,`deleted`) VALUES ('GAM','Game',3,1);

INSERT INTO `code` (`id`,`name`) VALUES ('ANI','animation');
INSERT INTO `code` (`id`,`name`) VALUES ('CAT','category');
INSERT INTO `code` (`id`,`name`) VALUES ('COD','code');
INSERT INTO `code` (`id`,`name`) VALUES ('CON','contents');
INSERT INTO `code` (`id`,`name`) VALUES ('GAM','game');

INSERT INTO `contents` (`id`,`categorycode`,`content`,`subject`) VALUES (1,'ANI','어쩌고 저쩌고','오늘 본 Animation');
INSERT INTO `contents` (`id`,`categorycode`,`content`,`subject`) VALUES (2,'ANI','저쩌고 어쩌고','어제 본 Animation');

