-- 파일이름 : db.sql

-- db 생성
DROP DATABASE IF EXISTS loginjoin;
CREATE DATABASE loginjoin;

-- db 사용 설정
USE loginjoin;

-- 회원정보 테이블
DROP TABLE if EXISTS loginjoin.member;
CREATE TABLE loginjoin.member(
                                 member_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키
                                 member_username VARCHAR(255) NOT NULL, -- 아이디
                                 member_password VARCHAR(255) NOT NULL, -- 암호
                                 member_email VARCHAR(255), -- 이메일
                                 member_joindate DATE DEFAULT NOW() -- 가입일
);

-- 테이블 구조 확인
DESCRIBE loginjoin.member;