-- Database: myblog

-- DROP DATABASE myblog;

CREATE DATABASE myblog
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ko_KR.UTF-8'
    LC_CTYPE = 'ko_KR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE myblog
    IS 'myblog';