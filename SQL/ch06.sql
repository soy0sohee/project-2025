-- 서브쿼리
-- 단일행
-- 최고 마일리지를 보유한 고객의 정보
USE	세계무역;

SELECT MAX(마일리지)
FROM 고객; -- 128,790 1행

SELECT *
FROM 고객
WHERE 마일리지 = 128790;

SELECT *
FROM 고객
WHERE 마일리지 = (
	SELECT MAX(마일리지)
	FROM 고객
	);

-- 주문번호 ‘H0250’을 주문한 고객의 고객회사명과 담당자명
SELECT 고객번호
FROM 주문
WHERE 주문번호 = 'H0250'; -- NARHA 1행

SELECT 고객회사명,
	담당자명
FROM 고객
WHERE 고객번호 = 'NARHA';

SELECT 고객회사명,
	담당자명
FROM 고객
WHERE 고객번호 = (
	SELECT 고객번호
	FROM 주문
	WHERE 주문번호 = 'H0250'
);

-- 복수행 비교연산자 
-- IN : 다중 행 중 하나라도 True
-- ‘부산광역시’ 고객이 주문한 주문건수
SELECT 고객번호
FROM 고객
WHERE 도시 = '부산광역시'; -- LIDBO, LLIWE, NGOHU, RICPE, TTMBO 5행

SELECT COUNT(*) AS 주문건수
FROM 주문
WHERE 고객번호 IN ('LIDBO', 'LLIWE', 'NGOHU', 'RICPE', 'TTMBO');

SELECT COUNT(*) AS 주문건수
FROM 주문
WHERE 고객번호 IN (
	SELECT 고객번호
	FROM 고객
	WHERE 도시 = '부산광역시'
);

-- ANY(SOME) : 마일리지 중 하나라도 크면 True(즉, 최소값보다 크면 True)
SELECT 마일리지
FROM 고객
WHERE 도시 = '부산광역시'; -- 2819|806|7795|1177|7329

SELECT 담당자명,
	고객회사명,
	마일리지
FROM 고객
WHERE 마일리지 > 806
ORDER BY 마일리지 ASC;

SELECT 담당자명,
	고객회사명,
	마일리지
FROM 고객
WHERE 마일리지 > ANY( 
	SELECT 마일리지
	FROM 고객
	WHERE 도시 = '부산광역시'
)
ORDER BY 마일리지 ASC;

-- ALL : 모든 조건이 True
SELECT 담당자명,
	고객회사명,
	마일리지
FROM 고객
WHERE 마일리지 > 7795
ORDER BY 마일리지 ASC;

SELECT 담당자명,
	고객회사명,
	마일리지
FROM 고객
WHERE 마일리지 > ALL( 
	SELECT 마일리지
	FROM 고객
	WHERE 도시 = '부산광역시'
)
ORDER BY 마일리지 ASC;

-- EXISTS : 행의 존재 여부 / 서브쿼리에서 메인쿼리 테이블을 조회
-- EXISTS는 중복 제거된 것처럼 보일뿐 DISTINCT나 GROUP BY 같은 집합 연산을 하지 않음
SELECT 고객번호
	,고객회사명
FROM 고객
WHERE EXISTS (
	SELECT *
	FROM 주문
	WHERE 고객번호 = 고객.고객번호
)
ORDER BY 고객번호 ASC;

SELECT 고객.고객번호
	,고객회사명
FROM 고객
	INNER JOIN 주문
	ON 고객.고객번호 = 주문.고객번호
GROUP BY 고객.고객번호
ORDER BY 고객번호 ASC;

-- 인라인뷰 : FROM절 안의 서브쿼리
-- 고객이 위치하는 도시의 평균마일리지와 각 고객의 마일리지 간의 차이
SELECT 도시,
	ROUND(AVG(마일리지)) AS 평균마일리지
FROM 고객
GROUP BY 도시
ORDER BY 평균마일리지 DESC;

SELECT 담당자명
	, 고객회사명
	, 고객.도시 AS 고객도시
	, 마일리지
	, 평균마일리지
	, 마일리지 - 평균마일리지 AS 평균차
FROM 고객
	, (
	SELECT 도시,
		ROUND(AVG(마일리지)) AS 평균마일리지
	FROM 고객
	GROUP BY 도시
	) AS 도시요약
WHERE 고객.도시 = 도시요약.도시;
	
-- CTE(WITH)절 
WITH 도시요약 AS (
	SELECT 도시
		, ROUND(AVG(마일리지)) AS 평균마일리지
	FROM 고객
	GROUP BY 도시
)
SELECT 담당자명
	, 고객회사명
	, 고객.도시 AS '고객도시'
	, 마일리지
	, 평균마일리지
	, 마일리지 - 평균마일리지 AS '평균차'
FROM 고객
	INNER JOIN 도시요약
	ON 고객.도시 = 도시요약.도시;

-- 스칼라서브쿼리 : SELECT절에서 컬럼처럼 사용
-- 1행 1열 반환, 행이 0이면 NULL
-- 2개 이상의 행 반환 = 오류
SELECT 담당자명
	, MAX(주문일) AS '최종주문일'
FROM 고객
	INNER JOIN 주문
	ON 고객.고객번호 = 주문.고객번호
GROUP BY 담당자명
ORDER BY 담당자명 ASC;
	
SELECT 담당자명
	, (
		SELECT MAX(주문일)
		FROM 주문
		WHERE 고객번호 = 고객.고객번호
	) AS '최종주문일'
FROM 고객
ORDER BY 담당자명 ASC;

-- HAVING절에서 도시별 평균 마일리지가 전체 고객의 평균 마일리지보다 높은 도시
SELECT ROUND(AVG(마일리지))
FROM 고객; -- 8624

SELECT 도시
	, ROUND(AVG(마일리지)) AS 평균마일리지
FROM 고객
GROUP BY 도시
HAVING 평균마일리지 > 8624;

SELECT 도시
	, ROUND(AVG(마일리지)) AS 평균마일리지
FROM 고객
GROUP BY 도시
HAVING 평균마일리지 > (
	SELECT AVG(마일리지)
	FROM 고객
);

-- 연습문제
-- 1. ‘배재용’ 사원의 부서명을 보이시오.
--    서브쿼리 또는 조인 방법으로 해결하시오.
SELECT 부서명
	, 사원.이름
FROM 부서
	INNER JOIN 사원
	ON 부서.부서번호 = 사원.부서번호
WHERE 이름 = '배재용';

SELECT 부서명
	,(
		SELECT 이름
		FROM 사원
		WHERE 이름 = '배재용'
	) AS 이름
FROM 부서
WHERE 부서번호 IN (
	SELECT 부서번호
	FROM 사원
	WHERE 이름 = '배재용'
);


-- 2. 한번도 주문한 적이 없는 제품의 정보를 보이시오.
--    서브쿼리 또는 조인 방법으로 해결하시오.
SELECT *
FROM 제품
WHERE NOT EXISTS(
	SELECT *
	FROM 주문세부
	WHERE 제품번호 = 제품.제품번호
);

SELECT *
FROM 제품
WHERE 제품번호 NOT IN ( -- 결과에 NULL이 하나나라도 있으면 결과 전부 안나옴, NOT EXISTS가 안정적
	SELECT 제품번호
	FROM 주문세부
);

SELECT 제품.*
FROM 제품
	LEFT OUTER JOIN 주문세부
	ON 제품.제품번호 = 주문세부.제품번호
WHERE 주문세부.제품번호 IS NULL;

-- 3. 담당자명, 고객회사명, 주문건수, 최초주문일과 최종주문일을 보이시오.
SELECT 담당자명
	, 고객회사명
	, COUNT(주문.주문번호) AS 주문건수
	, MIN(주문.주문일) AS 최초주문일
	, MAX(주문.주문일) AS 최종주문일
FROM 고객
	INNER JOIN 주문
	ON 고객.고객번호 = 주문.고객번호
GROUP BY 담당자명, 고객회사명
ORDER BY 담당자명;

WITH 주문cte AS (
	SELECT 고객번호
		, COUNT(주문번호) AS 주문건수
		, MIN(주문일) AS 최초주문일
		, MAX(주문일) AS 최종주문일
	FROM 주문
	GROUP BY 고객번호
)
SELECT 담당자명
	, 고객회사명
	, 주문건수
	, 최초주문일
	, 최종주문일
FROM 고객
	INNER JOIN 주문cte
	ON 고객.고객번호 = 주문cte.고객번호
ORDER BY 담당자명;

SELECT 담당자명
	, 고객회사명
	, 주문건수
	, 최초주문일
	, 최종주문일
FROM 고객
	,(SELECT 고객번호
		, COUNT(주문번호) AS 주문건수
		, MIN(주문일) AS 최초주문일
		, MAX(주문일) AS 최종주문일
	FROM 주문
	GROUP BY 고객번호
	) AS 주문cte
WHERE 고객.고객번호 = 주문cte.고객번호
ORDER BY 담당자명;

-- 실전문제
-- 1. 제품 테이블에 있는 제품 중 단가가 가장 높은 제품명은 무엇인가?
-- WHERE 단일행
SELECT 제품명
	,단가
FROM 제품
WHERE 단가 = (
	SELECT MAX(단가)
	FROM 제품
);

-- JOIN
SELECT 제품명
FROM 제품
	INNER JOIN (
		SELECT MAX(단가) AS 최고가
		FROM 제품
	) AS 제품단가
	ON 제품.단가 = 제품단가.최고가;

-- 2. 제품 테이블에 있는 제품 중 단가가 가장 높은 제품의 주문수량합은 얼마인가?
-- JOIN + WHERE 단일행 서브쿼리
SELECT 제품명
	,SUM(주문수량) AS 주문수량합
FROM 주문세부
	INNER JOIN 제품
	ON 주문세부.제품번호 = 제품.제품번호
WHERE 제품.단가 = (
		SELECT MAX(제품.단가)
		FROM 제품
)
GROUP BY 제품명;

-- SELECT 서브쿼리 + WHERE 단일행 서브쿼리
SELECT (SELECT 제품명
		FROM 제품
		WHERE 단가 = (
			SELECT MAX(단가)
			FROM 제품
		)) AS 제품명
	, SUM(주문수량) AS 주문수량합
FROM 주문세부
WHERE 제품번호 = (
	SELECT 제품번호
	FROM 제품
	WHERE 단가 = (
		SELECT MAX(단가)
		FROM 제품
	)
);

-- 3. ‘아이스크림’ 제품의 주문수량합은 얼마인가?
-- WHERE 다중행 서브쿼리
SELECT SUM(주문수량) AS 주문수량합
FROM 주문세부
WHERE 제품번호 IN (
	SELECT 제품번호
	FROM 제품
	WHERE 제품명 LIKE '%아이스크림%'
);

-- JOIN
SELECT SUM(주문수량) AS 주문수량합
FROM 주문세부
	INNER JOIN 제품
	ON 주문세부.제품번호 = 제품.제품번호
WHERE 제품명 LIKE '%아이스크림%';

-- 4. ‘서울특별시’ 고객들에 대한 주문년도별 주문건수를 보이시오.
-- WHERE 다중행 서브쿼리
SELECT YEAR(주문일) AS 주문년도
	, COUNT(주문번호) AS 주문건수
FROM 주문
WHERE 고객번호 IN (
	SELECT 고객번호
	FROM 고객
	WHERE 도시 = '서울특별시'
)
GROUP BY YEAR(주문일)
	WITH ROLLUP;

-- JOIN
SELECT YEAR(주문일) AS 주문년도
	, COUNT(주문번호) AS 주문건수
FROM 주문
	INNER JOIN 고객
	ON 주문.고객번호 = 고객.고객번호
WHERE 도시 = '서울특별시'
GROUP BY YEAR(주문일)
	WITH ROLLUP;

















