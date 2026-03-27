USE mydb;

DROP TABLE member_security;

-- 회원가입 테이블
CREATE TABLE member_security (
                                 id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                 username VARCHAR(255) NOT NULL, -- 아이디
                                 password VARCHAR(255) NOT NULL, -- 비밀번호
                                 nick_name VARCHAR(255),
                                 user_role VARCHAR(255) DEFAULT "ROLE_USER",
                                 join_date DATE DEFAULT (CURRENT_DATE)
);

-- 암호는 Bcrypt 암호화하여 저장
-- Bcrypt-generator.com
INSERT INTO member_security
VALUES (0, 'hong', '$2a$10$fKr.eXugNj0OkbWA0g8P.u/BGIG5dCl9sjMXPO9bpOUaWafQDMagS', '홍길동', 'ROLE_USER', DEFAULT);

SELECT * FROM member_security;