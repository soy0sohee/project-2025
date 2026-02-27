import React, { useState } from "react";

// 조건부 렌더링
// 특정조건에 따라 컴퍼넌트나 엘리먼트를 화면에 렌더링하는 기법
// 자바스크립트의 조건문, 삼항연산자, 논리연산자를 활용하여
// 동적으로 UI를 제어할 수 있음

// 1. if-else
function Greeting({ isLoggedIn }) {
  if (isLoggedIn == "true") {
    return <h3>환영합니다.</h3>;
  } else {
    return <h3>로그인이 필요합니다.</h3>;
  }
}
export function Conditional1(props) {
  return (
    <div>
      <Greeting isLoggedIn={props.isLoggedIn} />
    </div>
  );
}

const styles = {
  wrapper: {
    padding: "16px",
    display: "flex",
    flexDirection: "row",
    borderBottom: "1px solid gray",
  },
  greeting: {
    margin: "8px",
    color: "green",
  },
};
function Toolbar(props) {
  const { isLoggedIn, onClickLogin, onClickLogout, userName } = props;

  return (
    <div style={styles.wrapper}>
      {/* 2. 논리연산자 && - 조건부 노출 */}
      {isLoggedIn && <span style={styles.greeting}>환영합니다.</span>}

      {/* 2. 논리연산자 || - 기본값 설정 */}
      {<span>{userName || "방문자"}</span>}

      {/* 3. 삼항연산자 */}
      {isLoggedIn ? (
        <button onClick={onClickLogout}>로그아웃</button>
      ) : (
        <button onClick={onClickLogin}>로그인</button>
      )}
    </div>
  );
}
export function LandingPage() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleClickLogin = () => {
    setIsLoggedIn(true);
  };
  const handleClickLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div>
      <Toolbar
        isLoggedIn={isLoggedIn}
        onClickLogin={handleClickLogin}
        onClickLogout={handleClickLogout}
      />
      <p>렌딩페이지입니다.</p>
    </div>
  );
}

// 자바스크립트의 Truthy 값
// 자바스크립트에서 Truthy란 불리언 값으로 변환했을 때 true로 평가되는 값

// Falsy 값 목록
// 다음 값들은 falsy입니다.
// 이 값들은 if 문이나 조건부 연산에서 거짓(false)으로 평가됩니다:
// false             // boolean false
// 0                 // 숫자 0
// -0                // 음수 0
// 0n                // BigInt 0
// ""                // 빈 문자열
// null              // null 값
// undefined         // undefined 값
// NaN               // 숫자가 아님 (Not-a-Number)
// * 위에 나열된 값 외에는 모두 truthy로 간주됩니다.

// Truthy 값의 예
// 다음과 같은 값들은 truthy로 평가됩니다:
// true              // boolean true
// "문자열"          // 비어있지 않은 문자열
// 42                // 0이 아닌 숫자
// -1                // 음수도 truthy
// 3.14              // 소수도 truthy
// []                // 빈 배열도 truthy
// {}                // 빈 객체도 truthy
// function() {}     // 함수도 truthy
// new Date()        // Date 객체
// "false"           // 문자열 "false" (불리언 false와는 다름)
