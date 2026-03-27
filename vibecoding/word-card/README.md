# 📚 나만의 단어장

React + Spring Boot + H2 DB로 만드는 단어 관리 앱

---

## 프로젝트 구조

```
word-card/
├── backend/       ← Spring Boot (포트: 8080)
└── frontend/      ← React + Vite (포트: 3000)
```

---

## 실행 방법

### 1단계 - 백엔드 실행 (Spring Boot)

IntelliJ IDEA에서 열기:
1. `backend` 폴더를 IntelliJ로 열기
2. Maven 의존성 다운로드 대기 (자동 진행)
3. `WordcardApplication.java` 파일 열기
4. 초록색 실행 버튼 클릭

또는 터미널에서:
```bash
cd backend
./mvnw spring-boot:run
```

백엔드가 정상 실행되면:
- API 서버: http://localhost:8080
- H2 DB 콘솔: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:wordcarddb`
  - 사용자명: `sa` / 비밀번호: (빈칸)

---

### 2단계 - 프론트엔드 실행 (React)

터미널에서:
```bash
cd frontend

# 처음 실행 시: 패키지 설치
npm install

# 개발 서버 실행
npm run dev
```

브라우저에서 http://localhost:3000 접속

---

## API 명세

| 메서드 | URL | 설명 |
|--------|-----|------|
| GET | `/api/words?search=검색어&sort=latest` | 단어 목록 조회 |
| GET | `/api/words/count` | 전체 단어 개수 |
| POST | `/api/words` | 새 단어 추가 |
| DELETE | `/api/words/{id}` | 단어 삭제 |

**정렬 옵션**
- `sort=latest` → 최신순 (기본값)
- `sort=alphabet` → 알파벳순

---

## 주요 기능

- 단어 추가 (최대 10개)
- 단어 삭제
- 단어 또는 뜻으로 검색
- 최신순 / 알파벳순 정렬
- H2 인메모리 DB (서버 재시작 시 초기화)
