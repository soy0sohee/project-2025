-- security-login-db.sql
USE mydb;

DROP TABLE member_security;

CREATE TABLE member_security (
                                 id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                 username VARCHAR(255) NOT NULL, -- 아이디
                                 password VARCHAR(255) NOT NULL, -- 비밀번호
                                 nick_name VARCHAR(255),
                                 user_role VARCHAR(255) DEFAULT "ROLE_USER",
                                 join_date DATE DEFAULT (CURRENT_DATE)
);

-- 암호는 Bcrypt 암호화하여 저장 : Bcrypt-generator.com
INSERT INTO member_security
VALUES (0, 'hong', '$2a$10$fKr.eXugNj0OkbWA0g8P.u/BGIG5dCl9sjMXPO9bpOUaWafQDMagS', '홍길동', 'ROLE_USER', DEFAULT);

SELECT * FROM member_security;

-- SNS 로그인
-- SNS 회원가입은 간편하게 빨리 회원가입을 시키는 목적
-- SNS는 가져올 수 있는 회원정보에 제한이 있음
-- 내 사이트는 SNS 로그인만 지원하겠다? -> 가능함

-- 정식 로그인
-- 정식 회원가입은 절차가 까다롭고, 입력할 내용이 많음
-- 추가적인 정보가 필요하지 않다는 장점이 있음
-- 개인정보보호관리자가 있어야 하고, 유출에 대한 책임도 져야함

-- 대안 : 제3의 인증 사이트 이용
-- 예) Supabase, Firebase, CloudFlare
-- -> 큰 사이트가 아니라면 서비스 구축시 Google / 카카오 회원가입 / 인증만 지원 추천

-- sns-login-db.sql
USE mydb;

DROP TABLE sns_user;

CREATE TABLE sns_user(
                         id      BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         name    VARCHAR(255) NOT NULL, -- 닉네임(별명)
                         email   VARCHAR(255) NOT NULL, -- 이메일(계정)
                         picture VARCHAR(255) NOT NULL, -- 프로필이미지 경로
                         user_role   VARCHAR(255) DEFAULT 'ROLE_USER',
                         created_date  DATE DEFAULT (current_date)
);

INSERT INTO sns_user VALUES (0, 'hong', 'hong@gmail.com', '', 'ROLE_USER', DEFAULT );
INSERT INTO sns_user VALUES (0, 'tom', 'tom@gmail.com', '', 'ROLE_USER', DEFAULT );

SELECT * FROM sns_user;