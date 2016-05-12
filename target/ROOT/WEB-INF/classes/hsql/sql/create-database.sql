CREATE TABLE category (
  id varchar(3) NOT NULL,
  name varchar(45) DEFAULT NULL,
  order INTEGER DEFAULT NULL,
  deleted INTEGER DEFAULT 1,
  PRIMARY KEY (id)
) ;

CREATE TABLE code (
  id varchar(3) NOT NULL,
  name varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE contents (
  id INTEGER NOT NULL,
  categorycode varchar(45) DEFAULT NULL,
  content varchar(45) DEFAULT NULL,
  subject varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE title (
  id varchar(3) NOT NULL,
  name varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);


