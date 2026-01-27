-- DML : 데이터 조작어(데이터를 관리하는데 사용하는 언어)
USE 세계무역;

-- INSERT : 행추가(레코드추가)
SELECT * FROM 부서;

INSERT INTO 부서 (부서번호, 부서명)
VALUES ('A5', '마케팅부');

SELECT * FROM 제품
ORDER BY 제품번호 DESC;

INSERT INTO 제품
VALUES (91, '연어피클소스', NULL, 5000, 40);

SELECT * FROM 사원;

INSERT INTO 사원(사원번호, 이름, 직위, 성별, 입사일)
VALUES ('E20', '김사과', '수습사원', '남', CURDATE())
	, ('E21', '박바나나', '수습사원', '여', CURDATE())
	, ('E22', '정오렌지', '수습사원', '여', CURDATE());

-- UPDATE : 행수정(레코드수정)
SELECT * FROM 사원;

UPDATE 사원
SET 이름 = '김레몬'
	, 주소 = '금천구'
WHERE 사원번호 = 'E20';

SELECT * FROM 제품;

UPDATE 제품
SET 포장단위 = '200 ml bottles'
WHERE 제품번호 = '91';

UPDATE 제품
SET 단가 = 단가 * 1.1
	, 재고 = 재고 - 10
WHERE 제품번호 = '91';

-- DELETE : 행삭제(레코드삭제)
DELETE FROM 제품
WHERE 제품번호 = '91';

SELECT * FROM 사원;

DELETE FROM 사원
ORDER BY 입사일 DESC
LIMIT 3;

-- ON DUPLICATE KEY UPDATE : 레코드 추가시, 있으면 업데이트
INSERT INTO 제품(제품번호, 제품명, 단가, 재고)
VALUES (91, '연어피클핫소스', 6000, 50)
ON DUPLICATE KEY UPDATE
제품명 = '연어피클핫소스', 단가 = 7000, 재고 = 60;

SELECT * FROM 제품
WHERE 제품번호 = 91;

-- 연습문제
-- 1. 제품 테이블에 레코드를 추가하시오.
-- 제품번호: 95, 제품명: 망고베리 아이스크림, 포장단위 : 400g, 단가: 800, 재고: 30
SELECT * FROM 제품;

INSERT INTO 제품(제품번호, 제품명, 포장단위, 단가, 재고)
VALUES (95, '망고베리 아이스크림', '400g', 800, 30)
ON DUPLICATE KEY UPDATE
제품명 = '망고베리 아이스크림', 포장단위 = '400g', 단가 = 800, 재고 = 30;

SELECT * FROM 제품
WHERE 제품번호 = 95;

-- 2. 제품 테이블에 레코드를 추가하시오.
-- 제품번호: 96, 제품명: 눈꽃빙수맛 아이스크림, 단가: 2000
INSERT INTO 제품(제품번호, 제품명, 단가)
VALUES (96, '눈꽃빙수맛 아이스크림', 2000)
ON DUPLICATE KEY UPDATE
제품명 = '눈꽃빙수맛 아이스크림', 단가 = 2000;

SELECT * FROM 제품
WHERE 제품번호 = 96;

-- 3. 문제2에서 추가한 96번 제품의 재고를 30으로 변경하시오.
UPDATE 제품
SET 재고 = 30
WHERE 제품번호 = 96;

SELECT * FROM 제품
WHERE 제품번호 IN (95, 96);

-- 4. 사원이 한 명도 존재하지 않는 부서를 부서 테이블에서 삭제하시오.
SELECT *
FROM 부서
WHERE NOT EXISTS (
	SELECT 부서번호
	FROM 사원
	WHERE 부서번호 = 부서.부서번호
);

SELECT 부서.*
	, 이름
	, 사원.부서번호
FROM 부서
	LEFT OUTER JOIN 사원
	ON 부서.부서번호 = 사원.부서번호;

DELETE FROM 부서
WHERE NOT EXISTS (
	SELECT 부서번호
	FROM 사원
	WHERE 부서번호 = 부서.부서번호
);

SELECT * FROM 부서;







