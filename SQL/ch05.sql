USE 세계무역;

SELECT *
FROM 고객;

-- JOIN : 2개 이상의 테이블을 조합하여 하나의 결과를 반환
-- ANSI : 국제표준규격 SQL문, 어떻게 연결할지 먼저 말하고 조건 부여
-- NON-ANSI : 예전 조인 문법, 테이블을 다 던지고 WHERE에서 필터링

-- JOIN의 종류
-- CROSS : JOIN하는 모든 테이블의 모든 행 조인, 카티션곱(모든 경우의 수)
-- ANSI
SELECT COUNT(*)
FROM 부서; -- 4행
DESC 부서; -- 2열

SELECT COUNT(*)
FROM 사원; -- 10행
DESC 사원; -- 13열

SELECT *
FROM 부서
	CROSS JOIN 사원; -- 15열 40행
	
-- 부서 + 사원 테이블에서 WHERE로 필터링 가능
-- JOIN 시 중복되는 컬럼은 테이블.컬럼명 명시적으로 작성
SELECT 부서.부서번호
	, 사원.부서번호
	, 부서명
	, 이름
FROM 부서
	CROSS JOIN 사원
WHERE 이름 = '배재용';

-- NON-ANSI : WHERE 조건이 없으면 CROSS
SELECT *
FROM 부서
	, 사원; -- 15열 40행
	
SELECT 부서.부서번호
	, 사원.부서번호
	, 부서명
	, 이름
FROM 부서
	, 사원
WHERE 이름 = '배재용';

-- INNER : 각 테이블에서 조건이 일치하는 데이터만 조인
-- 1. EQUI JOIN(등가조인) : 등호로 비교
-- 2. NON-EQUI JOIN(비등가조인) : 등호 외 비교연산자 비교

-- 이소미 사원의 부서명 호출
-- 사원테이블과 부서테이블을 연결 할 수 있는 컬럼명 확인
-- 부서.부서번호 = 회사의 모든 부서번호를 가짐
-- 사원.부서번호 = 해당 사원의 부서번호를 가짐
-- 부서테이블 + 사원테이블 사원은 하나의 부서만 소속되므로 INNER JOIN
-- 즉, 사원별 일치하는 부서번호의 데이터만 조인
SELECT 사원.사원번호
	, 이름
	, 부서명
FROM 사원
	INNER JOIN 부서
	ON 사원.부서번호 = 부서.부서번호 
WHERE 이름 = '이소미';

SELECT 사원번호
	, 직위
	, 사원.부서번호
	, 부서명
FROM 사원
	, 부서
WHERE 이름 = '이소미' AND 사원.부서번호 = 부서.부서번호;

-- JOIN + GROUP BY
SELECT 고객.고객번호
	, 담당자명
	, 고객회사명
FROM 고객; -- 93

SELECT 고객.고객번호
	, 담당자명
	, 고객회사명
FROM 고객
	INNER JOIN 주문
	ON 고객.고객번호 = 주문.고객번호;
WHERE 고객회사명 = '대두식품'
GROUP BY 고객.고객번호
	, 담당자명
	, 고객회사명;

DESC 고객; -- PK = 기본키, 
DESC 주문; -- FK = 외래키, 다른 테이블의 키 값

-- INNER 등가조인 : 행을 =로 동등한 행만 조인
-- 고객사별 주문금액합
-- 고객테이블과 주문세부테이블은 중복되는 행이 없으므로 바로 불러올 수 없음
-- 주문테이블이 연결다리 역할을 해줌
DESC 고객;
SELECT 고객.고객번호
	, 고객회사명
	, TRUNCATE(SUM(단가 * 주문수량 * (1 - 할인율)), -1) AS '주문금액합'
FROM 고객
	INNER JOIN 주문
	ON 고객.고객번호 = 주문.고객번호
	INNER JOIN 주문세부
	ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 고객.고객번호;

-- NON-ANSI : WHERE 조건으로 테이블 연결
SELECT 고객.고객번호
	, 고객회사명
	, TRUNCATE(SUM(단가 * 주문수량 * (1 - 할인율)), -1) AS '주문금액합'
FROM 고객
	, 주문
	, 주문세부
WHERE 고객.고객번호 = 주문.고객번호 AND 주문.주문번호 = 주문세부.주문번호
GROUP BY 고객.고객번호;

-- INNER 비등가 조인 : 행을 비교 연산자로 동등한 행만 조인
DESC 고객; -- 마일리지
DESC 마일리지등급; -- 하한/상한마일리지

SELECT 담당자명
	, 고객회사명
	, 마일리지
	, 하한마일리지
	, 상한마일리지
FROM 고객
	INNER JOIN 마일리지등급
	ON 마일리지 BETWEEN 하한마일리지 AND 상한마일리지
WHERE 담당자명 = '이은광';

SELECT 담당자명
	, 고객회사명
	, 마일리지
	, 하한마일리지
	, 상한마일리지
FROM 고객
	,마일리지등급
WHERE 마일리지 BETWEEN 하한마일리지 AND 상한마일리지 AND 담당자명 = '이은광';
	
-- OUTER : 조건에 맞지 않는 행(NULL)도 결과값으로 나옴
-- 		 : A테이블의 결과를 기준으로 B테이블의 데이터 매칭

-- 사원테이블 기준 부서명이 없는 1명을 INNER JOIN = 사원 9명 / OUTER JOIN = 사원 10명
SELECT *
FROM 사원; -- 사원 10명

SELECT *
FROM 사원
WHERE 부서번호 = 'A4'; -- 부서번호 A4의 사원은 없음

SELECT 부서명
	,사원.*
FROM 사원
	LEFT OUTER JOIN 부서
	ON 사원.부서번호 = 부서.부서번호; 

-- NON-ANSI : 오라클에서는 기준테이블 뒤에 (+)로 표시
SELECT 부서명
	,사원.*
FROM 사원
	,부서
WHERE 사원.부서번호(+) = 부서.부서번호;

-- 부서 번호가 NULL인 사원
SELECT 이름,
	부서.*
FROM 사원
	LEFT OUTER JOIN 부서
	ON 사원.부서번호 = 부서.부서번호
WHERE 부서.부서번호 IS NULL;

-- SELF : 하나의 테이블을 대상으로 조인
-- 자신의 테이블을 INNER JOIN 별칭을 사용하여 조인
SELECT 상사번호
	, 직위
	, 사원번호
	, 이름
FROM 사원
WHERE 이름 = '이소미'; -- E06

SELECT 상사번호
	, 직위
	, 사원번호
	, 이름
FROM 사원
WHERE 사원번호 = 'E06';

SELECT 사원.사원번호
	, 사원.이름
	, 상사.사원번호 AS 상사번호
	, 상사.이름 AS 상사이름
FROM 사원
	INNER JOIN 사원 AS 상사
	ON 사원.상사번호 = 상사.사원번호;

-- 연습문제
-- 1. 세계무역 데이터베이스의 제품 테이블과 주문 세부 테이블을 조인하여 
--    제품명별로 주문수량합과 주문금액합을 보이시오.
SELECT 제품명
	, SUM(주문수량) AS '주문수량합'
	, ROUND(SUM(주문세부.단가 * 주문수량 * (1-할인율)), -1) AS '주문금액합'
FROM 제품
	INNER JOIN 주문세부
	ON 제품.제품번호 = 주문세부.제품번호
GROUP BY 제품명;

-- 2. 주문, 주문세부, 제품 테이블을 활용하여 '아이스크림'제품에 대해서
--   (주문년도 제품명)별로 주문수량합을 보이시오.
SELECT CONCAT(YEAR(주문일),',	',제품.제품명) AS '주문년도+제품명'
	, SUM(주문수량) AS '주문수량합'
FROM 주문세부
	LEFT OUTER JOIN 주문
	ON 주문세부.주문번호 = 주문.주문번호
	LEFT OUTER JOIN 제품
	ON 주문세부.제품번호 = 제품.제품번호
WHERE 제품명 LIKE '%아이스크림%'
GROUP BY CONCAT(YEAR(주문일),',	',제품.제품명);

-- 3. 제품, 주문세부 테이블을 활용하여 제품명별로 주문수량합을 보이시오.
--    이때 주문이 한 번도 안 된 제품에 대한 정보도 함께 나타내시오.
SELECT 제품명
	, SUM(주문수량) AS '주문수량합'
FROM 제품
	LEFT OUTER JOIN 주문세부
	ON 제품.제품번호 = 주문세부.제품번호
GROUP BY 제품명;

-- 4. 고객 회사 중 마일리지 등급이 'A'인 고객의 정보를 조회하시오. 
--    조회할 컬럼은 고객번호, 담당자명, 고객회사명, 등급명, 마일리지입니다.
SELECT 고객번호
	, 담당자명
	, 고객회사명
	, 등급명
	, 마일리지
FROM 고객
	INNER JOIN 마일리지등급
	ON 마일리지 BETWEEN 하한마일리지 AND 상한마일리지
WHERE 등급명 = 'A';

-- 실전문제
-- 1. 마일리지 등급명별로 고객수를 보이시오.
SELECT 등급명
	, COUNT(등급명) AS '고객수'
FROM 고객
	INNER JOIN 마일리지등급
	ON 마일리지 BETWEEN 하한마일리지 AND 상한마일리지
GROUP BY 등급명
ORDER BY 등급명 ASC;
	
-- 2. 주문번호 ‘H0249’를 주문한 고객의 모든 정보를 보이시오.
SELECT 주문번호
	, 고객.*
FROM 주문
	INNER JOIN 고객
	ON 주문.고객번호 = 고객.고객번호
WHERE 주문번호 = 'H0249';

-- 3. 2020년 4월 29일에 주문한 고객의 모든 정보를 보이시오.
SELECT 주문일
	, 고객.*
FROM 주문
	INNER JOIN 고객
	ON 주문.고객번호 = 고객.고객번호
WHERE 주문일 = '2020-04-29';

-- 4. 도시별로 주문금액합을 보이되 주문금액합이 많은 상위 5개의 도시에 대한 결과만 보이시오.
SELECT 도시
	, ROUND(SUM(단가 * 주문수량 * (1 - 할인율)),-1) AS '주문금액합'
FROM 고객 
	INNER JOIN 주문 
	ON 고객.고객번호 = 주문.고객번호
	INNER JOIN 주문세부
	ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 도시
ORDER BY 주문금액합 DESC
LIMIT 5;





































