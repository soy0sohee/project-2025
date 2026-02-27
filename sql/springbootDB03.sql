DROP TABLE counter.count;

CREATE DATABASE counter;

USE counter;

CREATE TABLE counter.count(
	count_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	count INT DEFAULT(0)
)

INSERT INTO counter.count
VALUES(0, DEFAULT);

SELECT * FROM counter.count;

UPDATE counter.count
	SET count = 1
WHERE count_no = 1;

SELECT * FROM counter.count;