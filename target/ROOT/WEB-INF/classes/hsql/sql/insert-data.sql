/*
-- Query: SELECT * FROM myblog.category
LIMIT 0, 1000

-- Date: 2016-03-17 16:39
*/
INSERT INTO category (id,name,order,deleted) VALUES ('ANI','Animation',1,1);
INSERT INTO category (id,name,order,deleted) VALUES ('COD','Code',2,1);
INSERT INTO category (id,name,order,deleted) VALUES ('GAM','Game',3,1);

/*
-- Query: SELECT * FROM myblog.code
LIMIT 0, 1000

-- Date: 2016-03-17 16:45
*/
INSERT INTO code (id,name) VALUES ('ANI','animation');
INSERT INTO code (id,name) VALUES ('CAT','category');
INSERT INTO code (id,name) VALUES ('COD','code');
INSERT INTO code (id,name) VALUES ('CON','contents');
INSERT INTO code (id,name) VALUES ('GAM','game');

/*
-- Query: SELECT * FROM myblog.contents
LIMIT 0, 1000

-- Date: 2016-03-17 16:47
*/
INSERT INTO contents (id,categorycode,content,subject) VALUES (1,'ANI','어쩌고 저쩌고','오늘 본 Animation');

/*
-- Query: SELECT * FROM myblog.title
LIMIT 0, 1000

-- Date: 2016-03-17 16:48
*/
INSERT INTO title (id,name) VALUES ('TIT','Welcome To the Ryuha Blog');

