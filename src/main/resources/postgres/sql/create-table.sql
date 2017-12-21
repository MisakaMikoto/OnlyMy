﻿CREATE TABLE category (
  id int primary key NOT NULL,
  name char(45),
  order int NOT NULL,
  deleted int DEFAULT 1
);

CREATE TABLE code (
  id int primary key NOT NULL,
  name char(45) NOT NULL
);

CREATE TABLE contents (
  id int primary key NOT NULL,
  code_id int NOT NULL,
  subject char(45) DEFAULT NULL,
  text TEXT DEFAULT NULL
);

CREATE TABLE title (
  id int primary key NOT NULL,
  name char(45) NOT NULL
);