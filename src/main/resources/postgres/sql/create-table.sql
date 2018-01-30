﻿-- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    display_order integer NOT NULL,
    deleted integer NOT NULL DEFAULT 1,
    url character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.category
    OWNER to postgres;


﻿-- Table: public.code

-- DROP TABLE public.code;

CREATE TABLE public.code
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT code_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.code
    OWNER to postgres;

﻿-- Table: public.contents

-- DROP TABLE public.contents;

CREATE TABLE public.contents
(
    code_id integer NOT NULL,
    subject character varying COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::bpchar,
    text text COLLATE pg_catalog."default",
    view_url character varying COLLATE pg_catalog."default",
    id bigint NOT NULL DEFAULT nextval('contents_id_seq'::regclass),
    uploaded_id character varying COLLATE pg_catalog."default",
    CONSTRAINT contents_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.contents
    OWNER to postgres;


﻿-- Table: public.title

-- DROP TABLE public.title;

CREATE TABLE public.title
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    deleted integer NOT NULL,
    used integer NOT NULL,
    CONSTRAINT title_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.title
    OWNER to postgres;