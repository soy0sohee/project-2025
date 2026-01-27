USE 세계무역;

-- DDL(Data Definition Lang) : 데이터 정의어

CREATE DATABASE IF NOT EXISTS 세계학사;

USE 세계학사;

-- 컬럼명 데이터타입 정리
-- CHAR(길이) : 고정길이 문자열, 길이만큼 공백 체움
-- 	예) 이름 CHAR(5) -> '홍길동' = '홍길동  '

-- VARCHAR(길이) : 가변길이 문자열, 길이보다 적어도 괜츈(컬럼용 가변)
-- 	예) 이름 VARCHAR(5) -> '홍길동' = '홍길동'
-- 	예) 게시판 APP -> 제목, 이름

-- TEXT : 큰 길이의 문자열(본문용 가변)
-- 	예) 게시판 APP -> 내용, 상세설명

-- INT : 정수(약 +-21억)
-- INT(길이)에서 길이는 ZEROFILL 예약어와 함께 쓰지않으면 의미 없음

-- FLOAT : 실수(소숫점 7자리)
-- DATE/TIME : 날짜/시간
-- DATETIME : 날짜+시간(1000년~9999년)
-- TIMESTAMP : 날짜+시간(UTC적용, 1970년~2038년)

CREATE TABLE 학과(
	학과번호 CHAR(2) -- PK(기본키), 학과 99개이하 예상, 고정길이
	, 학과명 VARCHAR(20) -- 길이 예측이 어려움, 20자이하 예상
	, 학과장명 VARCHAR(20)
);

DESC 학과;

INSERT INTO 학과
VALUES ('AA', '컴퓨터공학과', '배경민')
	,('BB', '소프트웨어학과', '김남준')
	,('CC', '디자인융합학과', '박선영');

SELECT * FROM 학과;

DROP TABLE 학생;

CREATE TABLE 학생(
	학번 CHAR(5) -- PK(기본키)
	, 이름 VARCHAR(20)
	, 생일 DATE -- 0000-00-00
	, 연락처 VARCHAR(20)
	, 학과번호 CHAR(2) -- FK(외래키)
);

DESC 학생;

INSERT INTO 학생
VALUES ('S0001', '이윤주', '2020-01-30', '01033334444', 'AA')
	,('S0002', '이승은', '2021-02-23', NULL, 'BB')
	,('S0003', '백재용', '2018-03-31', '01077778888', 'DD');

SELECT * FROM 학생;

-- 다른 테이블 구조와 데이터 복사해서 테이블 생성
CREATE TABLE 휴학생 AS
	SELECT *
	FROM 학생;

-- 데이터만 지우기
TRUNCATE TABLE 휴학생;

-- 다른 테이블 구조 복사해서 테이블 생성
CREATE TABLE 휴학생 AS
	SELECT *
	FROM 학생
	WHERE 1 = 2; -- 항상 FALSE 조건
	
DESC 휴학생;

-- 가상 컬럼(Generated Column) : 계산된 결과를 저장
CREATE TABLE 회원 (
	아이디 VARCHAR(20) PRIMARY KEY -- 기본키 설정 : 중복 허용 안함(UNIQE), NOT NULL 속성
	, 회원명 VARCHAR(10)
	, 키 INT
	, 몸무게 INT
	, 체질량지수 DECIMAL(4,1) AS (몸무게 / POWER(키/100,2)) STORED
);

DESC 회원;

INSERT INTO 회원(아이디, 회원명, 키, 몸무게)
VALUES ('ARANG','김아랑','170','55');

SELECT * FROM 회원;

-- ALTER : 테이블(객체) 속성 변경
DESC 학생;

-- 컬럼 추가
ALTER TABLE 학생
ADD 성별 CHAR(1);

-- 컬럼 변경
ALTER TABLE 학생
CHANGE COLUMN 연락처 핸드폰번호 VARCHAR(20);

-- 컬럼 삭제
ALTER TABLE 학생
DROP COLUMN 성별;

-- 테이블 이름 변경 
ALTER TABLE 학생
RENAME 졸업생;

DESC 졸업생;

-- DROP : 테이블 상제
DROP TABLE 학과;
DROP TABLE 졸업생;

-- 제약조건1
CREATE TABLE 학과 (
	학과번호 CHAR(2) PRIMARY KEY -- NOT NULL, UNIQUE 제약조건 자동 설정(무결성 충족해야함)
	,학과명 VARCHAR(20) NOT NULL
	,학과장명 VARCHAR(20) UNIQUE
);

DESC 학과;

INSERT INTO 학과
VALUES ('01', '국어국문학과', '홍교수');

SELECT * FROM 학과;

INSERT INTO 학과
VALUES ('01', '영어영문학과', '데이비드교수'); -- UNIQUE 제약조건

INSERT INTO 학과
VALUES (NULL , '영어영문학과', '데이비드교수'); -- NOT NULL 제약조건

INSERT INTO 학과
VALUES ('02' , NULL, '데이비드교수'); -- NOT NULL 제약조건

INSERT INTO 학과
VALUES (NULL , '영어영문학과', '홍교수'); -- UNIQUE 제약조건

DROP TABLE 학과;

-- 제약조건2
CREATE TABLE 학과 (
	학과번호 CHAR(2)
	,학과명 VARCHAR(20)
	,학과장명 VARCHAR(20)
	,PRIMARY KEY(학과번호)
);

DESC 학과;

DROP TABLE 학과;

-- 제약조건3
CREATE TABLE 학과 (
	학과번호 CHAR(2)
	,학과명 VARCHAR(20)
	,학과장명 VARCHAR(20)
);

ALTER TABLE 학과
ADD CONSTRAINT PK_학과 PRIMARY KEY(학과번호);

DESC 학과;

DROP TABLE 학과;

-- 외래키(FK) 제약조건 추가
DROP TABLE 학생;
-- 학과번호 insert시, 학과 테이블의 학과번호에 있는것이여야 함
CREATE TABLE 학생 (
	학번 CHAR(5) PRIMARY KEY
	,이름 VARCHAR(20) NOT NULL
	,생일 DATE NOT NULL
	,연락처 VARCHAR(20) UNIQUE
	,학과번호 CHAR(2) REFERENCES 학과(학과번호) -- 외래키 제약조건
	,성별 CHAR(1) CHECK(성별 IN('남', '여'))
	,등록일 DATE DEFAULT(CURDATE()) 
	,FOREIGN KEY(학과번호) REFERENCES 학과(학과번호) -- 외래키 제약조건
);

DESC 학생;

SELECT * FROM 학과;
SELECT * FROM 학생;

INSERT INTO 학과
VALUES ('01', '국어국문학과', '홍교수');

INSERT INTO 학생
VALUES ('S0001', '강감찬', '2000-02-03', '01022223333', '01', '남', NULL);

-- 남, 여 외에는 안됨
INSERT INTO 학생
VALUES ('S0001', '강감찬', '2000-02-03', '01022223333', '01', '홍', NULL);

-- 등록일이 공백일때 DEFAULT 적용
INSERT INTO 학생(학번, 이름, 생일, 연락처, 학과번호, 성별)
VALUES ('S0002', '이순신', '2000-03-04', '01033334444', '01', '남');

-- 외래키 제약조건 - 데이터의 무결성
-- 학과 테이블에 학과번호 02는 없으므로 제약조건에 걸림
INSERT INTO 학생(학번, 이름, 생일, 연락처, 학과번호, 성별)
VALUES ('S0003', '신사임당', '2000-04-05', '01044445555', '02', '여');

-- ON DELETE/UPDATE CASCADE
-- 참조하는 부모 테이블에서 삭제/수정이 일어날 때 자식테이블도 자동으로 변경

DROP TABLE 학생;
DROP TABLE 학과;

CREATE TABLE 학과 (
	학과번호 CHAR(2) PRIMARY KEY
	,학과명 VARCHAR(20)
);
DESC 학과;
CREATE TABLE 학생 (
	학번 CHAR(5) PRIMARY KEY
	,이름 VARCHAR(20)
	,학과번호 CHAR(2)
	,FOREIGN KEY(학과번호) REFERENCES 학과(학과번호)
		ON DELETE CASCADE 
		ON UPDATE CASCADE
);
DESC 학생;

INSERT INTO 학과
VALUES ('01', '국어국문학과')
	,('02', '컴퓨터공학과');
SELECT * FROM 학과;

INSERT INTO 학생
VALUES ('S0001','홍길동','01')
	,('S0002','이소룡','02');
SELECT * FROM 학생;

-- 학과번호를 수정하면, 참조하던 학생 테이블의 학과번호 수정
UPDATE 학과 
SET 학과번호 = '03' 
WHERE 학과번호 = '02';

-- 학과번호를 삭제하면, 참조하던 학생 테이블의 레코드 삭제
DELETE FROM 학과
WHERE 학과번호 = '01';


























