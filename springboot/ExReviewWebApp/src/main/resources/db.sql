USE mydb;
--

DROP TABLE users;
CREATE TABLE users (
                       id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       user_role VARCHAR(255) DEFAULT 'user'
);
SELECT * FROM users;
--

DROP TABLE sns_users;
CREATE TABLE sns_users (
                           id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           picture VARCHAR(255),
                           user_role VARCHAR(255) DEFAULT 'user'
);
SELECT * FROM sns_users;
--

DROP TABLE post;
CREATE TABLE post (
                      id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      rating VARCHAR(255) NOT NULL,
                      image VARCHAR(255),
                      date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM post;
--

DROP TABLE comment;
CREATE TABLE comment (
                         id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         content TEXT NOT NULL,
                         date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM comment;