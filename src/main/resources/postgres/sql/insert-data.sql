INSERT INTO category (id, name, order, deleted) VALUES (1,'Animation', 1, 1);
INSERT INTO category (id, name, order, deleted) VALUES (2,'Picture', 2, 1);
INSERT INTO category (id, name, order, deleted) VALUES (3,'Game', 3, 1);

INSERT INTO code (id, name) VALUES (1, 'category');
INSERT INTO code (id, name) VALUES (2, 'contents');
INSERT INTO code (id, name) VALUES (3, 'title');

INSERT INTO contents (id, code_id, subject, text) VALUES (1, 1, '어쩌고 저쩌고', '오늘 본 Animation');
INSERT INTO contents (id, code_id, subject, text) VALUES (2, 1, '저쩌고 어쩌고', '어제 본 Animation');

INSERT INTO title (id, name) VALUES (1, 'Ryuhas home');