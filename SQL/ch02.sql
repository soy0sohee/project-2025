USE 세계무역;

-- * = 전체 조회
SELECT *
FROM 고객;

-- 행(레코드)의 갯수 조회
-- AS(Alias) = 컬럼 별칭(생략 가능), 의미를 알아보기 쉽게 하려고 사용
SELECT count(*) AS "행의 갯수"
FROM 고객;

-- 일부 열만 조회
SELECT 고객번호, 담당자명, 고객회사명, 마일리지 AS "포인트", 마일리지 * 1.1 AS "10% 인상 마일리지"
FROM 고객;

-- Where절 : 조건적 데이터 조회 / 논리, 비교 연산자 사용
-- 마일리지가 10만점 이상 고객 조회
SELECT 고객번호, 담당자명, 마일리지 
FROM 고객
WHERE 마일리지 >= 100000;

-- ORDER BY : 정렬 
-- DESC = 내림차순 / ASC = 오름차순
-- 서울 사는 고객 중 마일리지가 많은 순서로 조회
SELECT 고객번호, 담당자명, 도시, 마일리지 AS "포인트"
FROM 고객
WHERE 도시 = "서울특별시"
ORDER BY 마일리지 DESC;

-- LIMIT n : 행(레코드) 지정 갯수 조회
-- 고객 중 마일리지 TOP3 조회
SELECT *
FROM 고객
ORDER BY 마일리지 DESC
LIMIT 3;

-- DISTINCT : 중복된 데이터를 제거
-- 고객이 사는 도시 조회
SELECT DISTINCT 도시
FROM 고객;

-- 산술연산자
SELECT 23 + 5 AS "더하기"
	  ,23 - 5 AS "빼기"
	  ,23 * 5 AS "곱하기"
	  ,23 / 5 AS "나누기 몫(실수)"
	  ,23 DIV 5 AS "나누기 몫(정수)"
	  ,23 % 5 AS "나머지"
	  ,23 MOD 5 AS "나머지";

-- 비교연산자
-- MySQL, MriaDB : Boolean값을 정수형으로 처리 true=1, false=0, Null=Null
-- PostgreSQL : Boolean값을 텍스트형으로 처리 true, false
-- Oracle, MS-SQLServer : Boolean값을 반환하지 않음
SELECT 23 > 23 AS "크다"
	  ,23 < 23 AS "작다"
	  ,23 = 23 AS "같다"
	  ,23 != 23 AS "같지않다"
	  ,23 <> 23 AS "같지않다"
	  ,23 >= 23 AS "크거나 같다"
	  ,23 <= 23 AS "작거나 같다";

-- 세계무역DB 호출
USE 세계무역;

-- 담당자 직위가 영업 과장인 고객 조회
SELECT *
FROM 고객
WHERE 담당자직위 = '영업 과장';

-- 부산광역시에 사는 마일리지가 1000점 미만의 고객 조회
SELECT *
FROM 고객
WHERE 도시 = '부산광역시' 
	AND 마일리지 < 1000;

-- 연습문제
-- 서울에 사는 마일리지 15,000 이상 20,000 이하 고객
SELECT *
FROM 고객
WHERE 도시 = '서울특별시' 
	AND 마일리지 >= 15000 
	AND 마일리지 <= 20000;

-- 고객들이 사는 지역, 도시를 하나씩 조회, 지역순 동일지역은 도시순 나열
-- 컬럼이 2개 이상에 DISTINCT를 적용하면 두 컬럼을 한 쌍으로 구분
SELECT DISTINCT 지역, 도시
FROM 고객
ORDER BY 지역, 도시 ASC;

-- 고객 테이블의 지역 필드가 ''(빈문자열)로 추가되었을 때
-- ''(빈문자열)인지 ' '(공백)인지, Null인지 헷갈림으로 ''(빈문자열)을 Null로 바꿔줌
-- 값이 없을 때는 명시적으로 Null로 채우는 것이 명시적이고 오류방지에도 좋음
UPDATE 고객
SET 지역 = NULL
WHERE 지역 = '';-- UPDATE할때 WHERE절이 없으면 모들 열에 데이터가 적용됨 주의 필요

SELECT 지역
FROM 고객;

-- UNION 연산자 : 2개 이상의 SELECT 결과를 합침
-- UNION = 합집합 / UNION ALL = 합집합 + 중복 레코드까지 모두 나타냄
-- UNION = 49열 / UNION ALL = 50열
SELECT 고객번호
	, 도시
	, 마일리지
FROM 고객
WHERE 도시 = '부산광역시' -- 5열
UNION ALL
SELECT 고객번호
	, 도시
	, 마일리지
FROM 고객
WHERE 마일리지 < 1000 -- 45열
ORDER BY 고객번호;

-- UNION ALL과 UNION(Union Distinct)의 차이
-- UNION ALL : 정렬 없음 / 중복 행 포함해서 모든 행 출력
-- UINON : 정렬됨 / 중복 제거하고 행 출력

-- UNION 사용시 주의점
-- 1. 컬럼(필드) 갯수 일치
-- 2. 각 컬럼의 데이터 타입(숫자,문자,날짜)을 일치 시켜야 함

-- IS NULL 연산자 : Null 인것만 또는 아닌것만 출력
SELECT *
FROM 고객
WHERE 지역 IS NULL;

SELECT *
FROM 고객
WHERE 지역 IS NOT NULL;

-- IN() 연산자 : ~중에 하나가 있으면 true / 여러개의 OR을 대체 / 또는
SELECT 고객번호,
	담당자명,
	담당자직위
FROM 고객
WHERE 담당자직위 = '영업 과장' 
	OR 담당자직위 = '마케팅 과장'
	OR 담당자직위 = '영업 사원';

SELECT 고객번호,
	담당자명,
	담당자직위
FROM 고객
WHERE 담당자직위 IN('영업 과장', '마케팅 과장', '영업 사원');

-- BETWEEN A AND B 연산자 : A 이상 B 이하 
SELECT 담당자명,
	마일리지
FROM 고객
WHERE 10000 <= 마일리지 AND 마일리지 <= 20000;

SELECT 담당자명,
	마일리지
FROM 고객
WHERE 마일리지 
	BETWEEN 10000 AND 20000;

-- LIKE 연산자 : 문자(열)의 일부를 검사할 때 사용
-- % : 0개 이상의 문자 대체
-- _ : 1개의 문자 대체
SELECT *
FROM 고객
WHERE 도시 LIKE '%광역시' 
	AND 고객번호 LIKE '_C%';

-- 연습문제
-- 제품 테이블에서 '주스'가 들어가는 모든 제품
SELECT *
FROM 제품
WHERE 제품명 LIKE '%주스%';

-- 제품 테이블에서 단가 5,000~10,000원 사이인 '주스' 제품 
SELECT *
FROM 제품
WHERE 단가 
	BETWEEN 5000 AND 10000
	AND 제품명 LIKE '%주스%';

-- 제품 테이블에서 제품 번호가 1,2,3,4,11,20인 모든 제품
SELECT *
FROM 제품
WHERE 제품번호 IN (1,2,3,4,11,20);

-- 제품 테이블에서 재고금액이 TOP10 - 제품번호,제품명,단가,재고,재고금액(단가*재고)
SELECT 제품번호,
	제품명,
	단가,
	재고,
	단가 * 재고 AS '재고금액'
FROM 제품
ORDER BY 재고금액 DESC
LIMIT 10;












