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