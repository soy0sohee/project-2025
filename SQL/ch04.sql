-- 집계함수(여러행 함수) : NULL값은 연산에서 제외
USE 세계무역;

-- COUNT()
SELECT COUNT(*)
FROM 고객;

SELECT COUNT(고객번호),
	COUNT(도시),
	COUNT(지역)
FROM 고객;

-- SUM()
-- AVG()
-- MIN()
-- MAX()
-- STDDEV()
SELECT SUM(마일리지) AS 합계,
	AVG(마일리지) AS 평균,
	MIN(마일리지) AS 최소,
	MAX(마일리지) AS 최대,
	TRUNCATE(STDDEV(마일리지), -1) AS 표준편차
FROM 고객;

SELECT SUM(마일리지) AS 합계,
	AVG(마일리지) AS 평균,
	MIN(마일리지) AS 최소,
	MAX(마일리지) AS 최대,
	TRUNCATE(STDDEV(마일리지), -1) AS 표준편차
FROM 고객
WHERE 도시 = '서울특별시';

-- GROUP BY절 : 특정 컬럼에 대한 그룹(묶음)으로 집계할 때
-- SELECT절에 컬럼 이름은 반드시 GROUP BY절에도 써야됨
-- SELECT절에 컬럼 순서로 호출 가능
SELECT 도시
	, 마일리지
	, COUNT(*) AS '도시별-고객수'
	, AVG(마일리지) AS '도시별-평균마일리지'
FROM 고객
GROUP BY 도시, 마일리지;

SELECT 도시
	, 마일리지
	, COUNT(*) AS '도시별-고객수'
	, AVG(마일리지) AS '도시별-평균마일리지'
FROM 고객
GROUP BY 1, 2;

SELECT 담당자직위
	,도시
	,COUNT(*) AS '고객수'
	,ROUND(AVG(마일리지), -1) AS '평균-마일리지'
FROM 고객
GROUP BY 1, 2
ORDER BY 1, 2;

-- HAVING절 : SELECT문에 들어가는 컬럼과 집계 함수에만 적용
--         : GRUOP BY절과 함께 사용
SELECT 도시
	,COUNT(*) AS '도시별고객수'
FROM 고객
GROUP BY 도시
HAVING 도시별고객수 >= 10
ORDER BY 도시별고객수 DESC;

SELECT 도시
	,COUNT(*) AS '고객수'
	,AVG(마일리지) AS '평균마일리지'
FROM 고객
WHERE 도시 LIKE '%광역시' -- 집계 전 집계 참여할 행 필터
GROUP BY 도시
HAVING 고객수 >= 5; -- 집계 후 집계 결과물 필터

-- SELECT에 쓰기전에 이 컬럼은 그룹당 하나로 값이 나오는지 확인
-- 가능하면 SELECT 사용 / 불가능하면 사용불가
-- SELECT에 쓰인 일반 컬럼은 ORDER BY에 모두 있어야 함(원칙)
SELECT 도시
	,담당자직위
	,SUM(마일리지)
FROM 고객
WHERE 고객번호 LIKE 'T%'
GROUP BY 도시, 담당자직위
HAVING SUM(마일리지) >= 1000;

-- 연습문제
-- 1. 고객 테이블에서 담당자 직위 별 집계하되,
--    담당자 직위와 최대 마일리지 출력
--    다만, 집계에 참여하는 고객은 '광역시' 거주자
--    최대 마일리지는 10000이상 출력
SELECT 담당자직위
	, MAX(마일리지) AS '최대마일리지'
FROM 고객
WHERE 도시 LIKE ('%광역시')
GROUP BY 담당자직위
HAVING 최대마일리지 >= 10000;

-- COUNT()함수에 DISTINCT 예약어 추가
-- 중복값을 제거한 개수.

SELECT 도시 -- 93
FROM 고객;

SELECT DISTINCT 도시 -- 27
FROM 고객;

SELECT COUNT(도시) AS '도시 입력한 고객수'
	, COUNT(DISTINCT 도시) AS '도시수'
FROM 고객;

-- 주문연도별로 주문 갯수
SELECT YEAR(주문일) AS '주문연도'
	, COUNT(*) AS '주문건수'
FROM 주문
GROUP BY YEAR(주문일);

-- 분기별(QUARTER) : 4분기로 나눔 
-- 소계,합계(ROLLUP) : 중간합계와 전체합계 추가
SELECT YEAR(주문일) AS '주문연도'
	, QUARTER(주문일) AS '분기'
	, COUNT(*) AS '주문건수'
FROM 주문
GROUP BY YEAR(주문일), QUARTER(주문일)
	WITH ROLLUP;

-- 월별 주문지역 건수
SELECT MONTH(주문일) AS '주문월'
	, COUNT(*) AS '주문건수'
FROM 주문
WHERE 요청일 < 발송일
GROUP BY MONTH(주문일)
ORDER BY 주문월;

-- 제품 테이블에서 '아이스크림'이 들어간 제품 갯수
SELECT 제품명
	, SUM(재고) AS '총재고'
FROM 제품
WHERE 제품명 LIKE '%아이스크림%'
GROUP BY 제품명
	WITH ROLLUP;

-- 실전문제
-- 1. 주문세부 테이블에서 주문수량합과 주문금액합을 보이시오.
SELECT SUM(주문수량) AS '주문수량합'
	, TRUNCATE(SUM(단가 * 주문수량 * (1 - 할인율)), -1) AS '주문금액합'
FROM 주문세부;

-- 2. 주문세부 테이블에서 주문번호별로 주문된 제품번호의 목록과 
--    주문금액합을 보이시오.
--    주문번호는 주문 건당 하나씩 발급됨.
SELECT 주문번호
	, GROUP_CONCAT(제품번호 SEPARATOR ', ') AS 제품번호
	, TRUNCATE(SUM(단가 * 주문수량 * (1 - 할인율)), -1) AS '주문금액합'
FROM 주문세부
GROUP BY 주문번호, 제품번호
	WITH ROLLUP;

-- 3. 주문 테이블에서 2021년 주문내역에 대해서 고객번호별로 
--    주문건수를 보이되, 주문건수가 많은 상위 3건의 고객의 정보만 보이시오.
SELECT 고객번호
	,COUNT(고객번호) AS '주문건수'
FROM 주문
WHERE 주문일 LIKE '2021%'
GROUP BY 고객번호
ORDER BY 주문건수 DESC
LIMIT 3;

-- GROUP_CONCAT() : 여러행의 문자열을 묶어줌
SELECT 직위
	, GROUP_CONCAT(이름 SEPARATOR ',	')
FROM 사원
GROUP BY 직위
ORDER BY 직위;








































