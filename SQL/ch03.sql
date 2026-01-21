-- 내장함수 : MySQL에서 기본적으로 지원하는 함수
-- 1.단일행 함수
-- 2.여러행 함수(집계함수)

-- 문자열 - 띄어쓰기 감지

-- 단일행 - 문자형 함수
-- CHAR_LENGTH : 글자길이(몇글자)
-- LENGTH : 바이트 수 / 한문자당 영문자-1byte, 한글-3byte
SELECT CHAR_LENGTH('HELLO'),
	LENGTH('HELLO');

SELECT CHAR_LENGTH('안녕'),
	LENGTH('안녕');

-- CONCAT : 문자열 연결
-- CONCAT_WS : 구분자로 구분 연결
SELECT CONCAT('dreams','come','true');

SELECT CONCAT_WS(' ','dreams','come','true');
SELECT CONCAT_WS('-','2026','01','01');

-- 문자열 일부 가져오기
-- LEFT(문자열,N) : 문자열 왼쪽부터 N글자 
-- RIGHT(문자열,N) : 문자열 오른쪽부터 N글자 
SELECT LEFT('SQL 완전정복', 3);
SELECT RIGHT('SQL 완전정복', 3);

-- SUBSTR(문자열,N1,N2) : 문자열 왼쪽 N1부터 N2글자 / 인덱스 1부터 시작
SELECT SUBSTR('SQL 완전정복', 3, 5);

-- SUBSTRING_INDEX(문자열,구분자,N)
-- 문자열을 구분자로 구분하고 N번째 구분자까지 가져옴
SELECT SUBSTRING_INDEX('서울시 동작구 흑성동',' ',2);
-- SUBSTRING_INDEX(문자열,구분자,-N)
-- 문자열을 구분자로 구분하고 뒤에서 N번째 구분자까지 가져옴
SELECT SUBSTRING_INDEX('서울시 동작구 흑성동',' ',-2);

-- 자릿수 채우는 함수
-- LPAD(문자열1,N,문자열2) : 문자열1을 기준으로 왼쪽에 문자열2를 N개만큼 채움
-- RPAD(문자열1,N,문자열2) : 문자열1을 기준으로 오른쪽에 문자열2를 N개만큼 채움
SELECT LPAD('SQL',10,'#');
SELECT RPAD('SQL',10,'#');

-- 공백 제거 함수
-- LTRIM(문자열) : 문자열의 왼쪽 공백 제거
-- RTRIM(문자열) : 문자열의 오른쪽 공백 제거
-- TRIM(문자열) : 문자열의 모든 공백 제거
SELECT LTRIM('    SQL    '),
	LENGTH(LTRIM('    SQL    '));

SELECT RTRIM('    SQL    '),
	LENGTH(RTRIM('    SQL    '));

SELECT TRIM('    SQL    '),
	LENGTH(TRIM('    SQL    '));

-- 양끝 문자만 제거
-- TRIM(BOTH 문자열2 FROM 문자열1) : 문자열1 양끝의 문자열2 제거
-- TRIM(LEADING 문자열2 FROM 문자열1) : 문자열1 맨앞의 문자열2 제거
-- TRIM(TRAILING 문자열2 FROM 문자열1) : 문자열1 맨뒤의 문자열2 제거
SELECT TRIM(BOTH '###' FROM '###SQL###');
SELECT TRIM(LEADING '###' FROM '###SQL###');
SELECT TRIM(TRAILING '###' FROM '###SQL###');

-- TRIM은 양끝(앞/뒤)만 처리하고 중간은 절대 건드리지 않음
SELECT TRIM(BOTH '###' FROM '&&###SQL###&&');

-- 중간 문자열 제거 응용
-- REPLACE(문자열,찾을문자열,바꿀문자열) : 문자열에서 찾을문자열을 바꿀문자열로 치환
SELECT REPLACE('SQL SQL',' ',''); -- 모든 공백 제거

-- 문자열 인덱스 찾기
-- FIELD(기준문자열, 문자열1, 문자열2, ...) : 기준 문자열이 문자열 목록에서 몇번째인지
-- FIND_IN_SET(기준문자열, '문자열1,문자열2') : 콤마로 구분된 문자열에서 기준 문자열의 위치
-- INSTR(문자열, 찾을문자열) : 문자열 안에서 특정 문자열이 시작되는 문자 위치
-- ELT(N, 문자열1, 문자열2, ...) : N번째에 해당하는 문자열
SELECT FIELD('JAVA','SQL','JAVA','C');
SELECT FIND_IN_SET('JAVA','SQL,JAVA,C');
SELECT INSTR('SQL JAVA C','JAVA');
SELECT ELT(2,'SQL','JAVA','C');

-- 문자열 중복
-- REPEAT(문자열, N) : 문자열을 N개 반복
-- CONCAT(REPEAT(문자열, N), 문자열1) : 문자열을 N개 반복 후 문자열1과 연결
SELECT REPEAT('*', 5);
SELECT CONCAT(REPEAT('*',5),'star');
SELECT LPAD('star',9,'*');

-- 문자열 치환
-- REPLACE(문자열, 찾을문자열, 바꿀문자열) : 찾을 문자열을 바꿀 문자열로 치환
SELECT REPLACE('010.1234.4568','.','-');

-- 문자열 거꾸로
-- REVERSE(문자열) : 문자열 뒤집어서 반환 
SELECT REVERSE('OLLEH');

-- 소수점 관련 함수
-- CEILING(12.12) : 올림하여 정수, 값보다 작거나 같은 최대 정수 //13
-- FLOOR(12.12) : 버림하여 정수, 값보다 크거나 같은 최소 정수 //12
-- ROUND(12.12) : 반올림하여 정수 //12
-- ROUND(12.12, N) : 소수 N+1번째 자리에서 반올림 //12.1
-- ROUND(12.12, -N) : 정수 N번째 자리에서 반올림 //10
-- TRUNCATE(12.12, N) : 소수 N+1번째 자리에서 버림 //12.1
-- TRUNCATE(12.12, -N) : 정수 N번째 자리에서 버림 //10
SELECT CEILING(123.24);
SELECT FLOOR(123.24);
SELECT ROUND(123.24);
SELECT ROUND(123.24,1);
SELECT ROUND(123.24,2);
SELECT ROUND(2355.2455,-1);
SELECT ROUND(2355.2455,-2);
SELECT TRUNCATE(2355.2455,1);
SELECT TRUNCATE(2355.2455,2);
SELECT TRUNCATE(2355.2455,-1);
SELECT TRUNCATE(2355.2455,-2);

-- 절댓값
-- ABS(N) : 모든 숫자를 양수로 반환
SELECT ABS(-120);
SELECT ABS(120);

-- 부호
-- SIGN(N) : 음수면 -1, 양수면 1을 반환
SELECT SIGN(-120);
SELECT SIGN(120);

-- 나누기 함수
SELECT 203 % 4;
SELECT 203 MOD 4;
SELECT MOD(203,4);

-- 제곱승
-- POWER(N, n) : N의 n승의 값
SELECT POWER(2, 3);

-- 제곱근
-- SQRT(N) : N이 2의 몇승인지
SELECT SQRT(16);

-- 랜덤값
-- RAND() : 랜덤값 가져오는 JS의 MATH.RANDOM()과 같은 용어
SELECT RAND();
SELECT FLOOR(RAND()*100)+1;

-- 현재 날짜+시간 가져오기
-- NOW() : 쿼리 시작 시각 / 쿼리 안에서 여러번 호출해도 값이 동일(더 안정적)
-- SYSDATE() : 함수가 실행되는 그 시각 / 실시간 시간, 실행 시간 측정에 용이
SELECT NOW();
SELECT SYSDATE();

-- 현재 날짜 가져오기
SELECT CURDATE();

-- 현재 시간 가져오기
SELECT CURTIME();

-- 날짜 간격 가져오기
-- ex) 설날까지 남은 날짜, D-day설정
-- DATEDIFF('0000-00-00', Now()) : '0000-00-00'까지 남은 날짜
SELECT NOW()
	,DATEDIFF('2026-02-16', NOW());

-- TIMESTAMPDIFF(YEAR/MONTH/DAY, NOW(), '0000-00-00') : 연/월/일 별 남은 연/월/일
SELECT NOW()
	,TIMESTAMPDIFF(YEAR, NOW(), '2027-01-30')
	,TIMESTAMPDIFF(MONTH, NOW(), '2027-01-30')
	,TIMESTAMPDIFF(DAY, NOW(), '2027-01-30');

SELECT NOW()
	,DATEDIFF('2026-01-22 10:00', NOW()) -- 자정 기준
	,TIMESTAMPDIFF(DAY, NOW(), '2026-01-22 10:00'); -- 만 24시 기준
	
-- 몇 일 후
-- ADDDATE(NOW(), N) : 오늘로부터 N일 후
-- ADDDATE(NOW(), INTERVAL N DAT/MONTH/HOUR) : 오늘로부터 N 월/일/시 후, 음수는 전
SELECT NOW()
	,ADDDATE(NOW(), 5)
	,ADDDATE(NOW(), INTERVAL 50 DAY)
	,ADDDATE(NOW(), INTERVAL 50 MONTH)
	,ADDDATE(NOW(), INTERVAL 50 HOUR);

-- LAST_DAY(NOW()) : 이달의 마지막 일
-- DAYOFYEAR(NOW()) : 올해 1월1일 기준 오늘은 몇번째 날인가, 12월31일 365
-- MONTHNAME(NOW()) : 이달의 영어 이름
-- WEEKDAY(NOW()) : 오늘의 요일 월(0)~일(6)
SELECT NOW()
	,LAST_DAY(NOW())
	,DAYOFYEAR(NOW())
	,MONTHNAME(NOW())
	,DAYOFWEEK(NOW())
	,WEEKDAY(NOW());

-- 형 변환 함수
-- CAST(값 AS 타입) : 문자열 <-> 숫자 <-> 날짜 변환
-- CAST(값 AS UNSIGNED) : UNSIGNED 타입 = 숫자형으로 형 변환, 0 이상만 가능한 정수, 음수 불가
-- CAST(값 AS SIGNED) : SIGNED 타입 = 숫자형으로 형 변환, 음수 포함 정수
-- CAST(값 AS CHAR(n)) : CHAR(n) 타입 = 문자로 형 변환
SELECT CAST(1 AS UNSIGNED)
	,CAST(-2 AS UNSIGNED) -- 음수를 정수로 강제 전환 하면서, 이진수로 해석함
	,CAST(-2 AS SIGNED)
	,CAST(2 AS CHAR(1));

-- 
SELECT CONVERT('1', UNSIGNED)
	,CONVERT(-2, SIGNED)
	,CONVERT(2, CHAR(1));

-- CAST = ANSI 표준
-- CONVERT = MySQL 전용

-- 조건함수
-- JS의 IF문과 삼항연산자의 결합같은 역할
-- IF(조건, true반환, faluse반환)
SELECT IF(10>20, 10, 20);
SELECT IF(12500 * 450 > 500000, '초과달성', '미달성');

-- Null 체크 함수
-- IFNULL(값, 대체값) : 값이 null이면 대체값 반환 / 값이 null이 아니면 원래 값 반환
SELECT IFNULL('123', 0);
SELECT IFNULL(null, 0);
SELECT IFNULL(null, '지역명 없음');

-- NULLIF(값1, 값2) : 값1과 값2가 같으면 NULL 반환 / 같지 않으면 값1 반환
SELECT NULLIF(12 * 10, 120);
SELECT NULLIF(12 * 10, 1200);

-- CASE WHEN - THEN
-- JS의 IF-ELSE문과 비슷한 역할
-- CASE WHEN 조건 THEN 반환값 : 조건이 true이면 반환값 반환
SELECT CASE
	WHEN 20 < 20 THEN '20보다 작음'
	WHEN 20 < 30 THEN '30보다 작음'
	ELSE '그외의 수'
END;

-- 연습문제
-- 1. 다음 조건에 따라 고객 테이블에서 고객회사명과 전화번호를 
--    다른 형태로 보이도록 함수를 사용해봅시다. 
-- 고객회사명2와 전화번호2를 만드는 조건은 아래와 같습니다.
-- 조건
-- 1. 고객회사명2 : 기존 고객회사명 중 앞의 두 자리를 *로 변환한다.
-- 2. 전화번호2 : 기존 전화번호의 (xxx)xxx-xxxx 형식을 xxx-xxx-xxxx형식으로 변환한다.

USE 세계무역;

SELECT REPLACE(고객회사명, LEFT(고객회사명, 2), '**') AS '고객회사명2'
	,REPLACE(REPLACE(전화번호, '(', ''), ')', '-') AS '전화번호2'
FROM 고객;

SELECT CONCAT('**', SUBSTR(고객회사명, 3)) AS '고객회사명2'
	,REPLACE(SUBSTR(전화번호, 2), ')', '-') AS '전화번호2'
FROM 고객;
	

-- 2. 다음 조건에 따라 주문 세부 테이블의 모든 컬럼과 주문금액, 할인금액, 실제 주문금액을 보이시오. 
-- 이때 모든 금액은 1의 단위에서 버림을 하고 10원 단위까지 보이시오.
-- 조건
-- 1. 주문금액: 주문수량 * 단가
-- 2. 할인금액 : 주문수량 * 단가 * 할인율
-- 3. 실주문금액 : 주문금액 - 할인금액

SELECT 주문수량 * 단가 AS '주문금액'
	,FLOOR(주문수량 * 단가 * 할인율) AS '할인금액'
	,(주문수량 * 단가) - TRUNCATE(주문수량 * 단가 * 할인율, -1) AS '실제 주문금액'
FROM 주문세부;

-- CTE(WITH절)
WITH 주문cte AS (
	SELECT *
		,주문수량 * 단가 AS '주문금액'
		,TRUNCATE(주문수량 * 단가 * 할인율, -1) AS '할인금액'
	FROM 주문세부
)
SELECT *
	,주문금액
	,할인금액
	,주문금액 - 할인금액 AS '실제 주문금액'
FROM 주문cte;

-- 3. 사원 테이블에서 전체 사원의 이름, 생일, 만나이, 입사일, 입사일수, 
-- 입사한 지 500일 후의 날짜를 보이시오.

SELECT 이름
	,생일
	,ABS(TIMESTAMPDIFF(YEAR, NOW(),생일)) AS '만나이'
	,입사일
	,DATEDIFF(NOW(),입사일) AS '입사일수'
	,ADDDATE(입사일, INTERVAL 500 DAY) AS '입사 500일 후 날짜'
FROM 사원;

-- 4. 고객 테이블에서 도시 컬럼의 데이터를 다음 조건에 따라 ‘대도시’와 ‘도시’로 구분하고, 
-- 마일리지 점수에 따라서 ‘VVIP’, ‘VIP’, ‘일반 고객’으로 구분하시오.
-- 조건
-- 1. 도시 구분: ‘특별시’나 ‘광역시’는 ‘대도시’로, 그 나머지 도시는 ‘도시’로 구분한다.
-- 2. 마일리지 구분 : 마일리지가 100,000점 이상이면 ‘VVIP고객’, 10,000점 이상이면 ‘VIP고객’, 그 나머지는 ‘일반고객’으로 구분한다.

SELECT CASE 
		WHEN 도시 LIKE '%광역시' THEN REPLACE(도시, 도시, '대도시')
		WHEN 도시 LIKE '%특별시' THEN REPLACE(도시, 도시, '대도시')
		ELSE REPLACE(도시, 도시, '도시')
	END AS '도시'
	,CASE
		WHEN 마일리지 >= 100000 THEN 'VVIP고객'
		WHEN 마일리지 >= 10000 THEN 'VIP고객'
		ELSE '일반고객'
	END AS '마일리지'
FROM 고객;











