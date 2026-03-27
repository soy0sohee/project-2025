-- db.sql (초보자용)
-- 과목테이블 / 수강신청테이블 2개만 사용 (단일 사용자 UI 기준)
-- 외래키(FK) 제약조건은 사용하지 않음

-- =========================
-- 0) DB 만들기(선택)
-- =========================
CREATE DATABASE IF NOT EXISTS excourses
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE excourses;

-- =========================
-- 1) 테이블 초기화(삭제 후 재생성)
-- =========================
DROP TABLE IF EXISTS registrations;
DROP TABLE IF EXISTS courses;

-- =========================
-- 2) 테이블 생성
-- =========================

-- (1) 과목 테이블
CREATE TABLE courses (
    course_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    professor VARCHAR(50) NOT NULL,
    time VARCHAR(50) NOT NULL,
    credits INT NOT NULL,
    capacity INT NOT NULL,
    enrolled INT NOT NULL DEFAULT 0,
    PRIMARY KEY (course_id)
) ENGINE=InnoDB;

-- (2) 수강신청 테이블
-- 외래키 제약조건은 없지만, 과목을 숫자로 연결해서 저장
CREATE TABLE registrations (
    registration_id INT NOT NULL AUTO_INCREMENT,
    course_id INT NOT NULL,
    registered_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (registration_id),
    -- 같은 과목을 중복 신청하지 못하게 막는 제약(단일 사용자 기준)
    UNIQUE KEY uq_registrations_course (course_id)
) ENGINE=InnoDB;

-- 조회 속도를 위한 인덱스(선택)
CREATE INDEX idx_courses_name ON courses(name);
CREATE INDEX idx_registrations_course ON registrations(course_id);

-- =========================
-- 3) 초기 데이터 넣기(INSERT)
-- =========================

-- 과목 데이터(기존 화면과 동일)
INSERT INTO courses (course_id, name, professor, time, credits, capacity, enrolled) VALUES
    (1, '자료구조',       '김교수', '월수 10:00-12:00', 3, 50, 35),
    (2, '운영체제',       '이교수', '화목 13:00-15:00', 3, 45, 42),
    (3, '데이터베이스',   '박교수', '월수 15:00-17:00', 3, 40, 28),
    (4, '컴퓨터네트워크', '최교수', '화목 10:00-12:00', 3, 50, 45),
    (5, '알고리즘',       '정교수', '수금 13:00-15:00', 3, 35, 30),
    (6, '웹프로그래밍',   '강교수', '월수 13:00-15:00', 3, 40, 25);

-- 수강신청 데이터(예시)
-- (단일 사용자: 1번/3번 과목 신청)
INSERT INTO registrations (course_id) VALUES
    (1),
    (3);

SELECT * FROM courses;
SELECT * FROM registrations;
