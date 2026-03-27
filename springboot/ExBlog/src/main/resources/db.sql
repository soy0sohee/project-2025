USE mydb;

DROP TABLE article;

CREATE TABLE article (
                         id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         title VARCHAR(255) NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO article(title, content, created_at, updated_at)
VALUES ('제목1','내용1', NOW(), NOW());

SELECT * FROM article;