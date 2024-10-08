CREATE DATABASE documents_db;


CREATE TABLE IF NOT EXISTS documents
( id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255),
  content VARCHAR(255),
  author VARCHAR(255),
  instant VARCHAR(255),
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS author
( id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS documents_author
( documents_id BIGINT,
  author_id BIGINT
);
