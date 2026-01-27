-- 연습문제
USE 세계무역;

DROP TABLE 주문세부_복사;

CREATE TABLE 제품복사 AS SELECT * FROM 제품;
CREATE TABLE 주문세부복사 AS SELECT * FROM 주문세부;

SELECT * FROM 제품복사;

-- 문제1
INSERT INTO 제품복사
VALUES ('97', '마이너스재고', '100g', '500', '-5');

SELECT * FROM 제품복사;

DELETE FROM 제품복사
WHERE 제품번호 = '97';

ALTER TABLE 제품복사
ADD CONSTRAINT CK재고 CHECK(재고>=0);

-- ALTER TABLE 제품 MODIFY COLUMN 재고 INT CHECK(재고>=0);
-- ALTER TABLE 제품 CHANGE COLUMN 재고 재고 INT CHECK(재고>=0);

DESC 제품복사;

-- 문제2
SELECT 단가 * 재고 AS 재고금액
FROM 제품복사;

ALTER TABLE 제품복사
ADD 재고금액 INT AS (단가 * 재고);

SELECT * FROM 제품복사;

-- 문제3
DESC 주문세부복사;
DESC 제품복사;

ALTER TABLE 제품복사
ADD CONSTRAINT PK_주문 PRIMARY KEY(제품번호);

ALTER TABLE 주문세부복사
ADD CONSTRAINT FK_주문 
	FOREIGN KEY(제품번호) REFERENCES 제품복사(제품번호)
	ON DELETE CASCADE;

INSERT INTO 제품복사(제품번호,제품명)
VALUES ('0','임시제품');

INSERT INTO 주문세부복사(주문번호,제품번호)
VALUES ('H0000','0');

DELETE FROM 제품복사
WHERE 제품번호 = '0';

SELECT * FROM 제품복사;
SELECT * FROM 주문세부복사 WHERE 주문번호 = 'H0000';

-- 실전문제
-- 문제1
CREATE DATABASE IF NOT EXISTS 세계영화;

USE 세계영화;

CREATE TABLE 영화 (
	영화번호 CHAR(5) PRIMARY KEY
	,타이틀 VARCHAR(100) NOT NULL
	,장르 VARCHAR(20) CHECK(장르 IN('코미디','드라마','다큐','SF','액션','역사','기타'))
	,배우 VARCHAR(100) NOT NULL
	,감독 VARCHAR(50) NOT NULL
	,제작사 VARCHAR(150) NOT NULL
	,개봉일 DATE
	,등록일 DATE DEFAULT(CURDATE())
);

DESC 영화;

-- 문제2
CREATE TABLE 평점관리 (
	번호 INT AUTO_INCREMENT PRIMARY KEY
	,평가자닉네임 VARCHAR(50) NOT NULL
	,영화번호 CHAR(20) REFERENCES 영화(영화번호)
	,평점 INT NOT NULL CHECK(평점 BETWEEN 1 AND 5)
	,평가 VARCHAR(2000) NOT NULL
	,등록일 DATE DEFAULT(CURDATE())
	,FOREIGN KEY (영화번호) REFERENCES 영화(영화번호)
);

DESC 평점관리;

-- 문제3
INSERT INTO 영화(영화번호, 타이틀, 장르, 배우, 감독, 제작사, 개봉일)
VALUES ('00001','파묘','드라마','최민식,김고은','장재현','쇼박스','2024-02-22')
	,('00002','듄:파트2','액션','티미시 샬라메,젠 데이아','드니 뵐뇌브','레전더리 픽처스','2024-02-28');

SELECT * FROM 영화;

-- 문제4
INSERT INTO 평점관리(평가자닉네임, 영화번호, 평점, 평가)
VALUES ('영화광','00001','5','미치도록 스릴이 넘쳐요')
	,('무비러브','00002','4','장엄한 스케일이 좋다');

SELECT * FROM 평점관리;

-- 문제5
INSERT INTO 영화(영화번호)
VALUES ('00003');

-- 문제6
DELETE FROM 영화
WHERE 영화번호 = '00001';

-- 문제7
ALTER TABLE 평점관리
ADD CONSTRAINT FK평점 
	FOREIGN KEY (영화번호) REFERENCES 영화(영화번호)
	ON DELETE CASCADE;

DELETE FROM 영화
WHERE 영화번호 = '00001';

SELECT * FROM 영화;

















