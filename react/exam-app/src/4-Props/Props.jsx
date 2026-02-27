import React from "react";

//Props.jsx
// 리액트의 Props 개념
// 1. Props란?
//  a. Props는 **"Properties"**의 줄임말로, 리액트 컴포넌트에 데이터를 전달하는 방법입니다.
//  b. 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달할 때 사용되며, 읽기 전용입니다.
//    (자식 컴포넌트에서 직접 props를 변경할 수 없습니다.)
//  c. Props는 JSX 속성처럼 작성하며, 컴포넌트 내에서 객체 형태로 접근할 수 있습니다.
// 2. Props의 특징
//  a. 컴포넌트 간 데이터 전달 용도
//  b. 불변성 (읽기 전용, 자식에서 수정할 수 없음)
//  c. 동적 데이터 렌더링 지원(props, state가 변경되면 리렌더링됨)

function DisplayList(props) {
  return (
    <div>
      <h2>{props.title}</h2>
      <ul>
        {props.items.map((item, index) => {
          return <li key={index}>{item}</li>; // 기본 구문
        })}
        {props.items.map((item, index) => (
          <li key={index}>{item}</li> // return 생략 구문
        ))}
      </ul>
    </div>
  );
}
export function Props1() {
  return (
    <>
      <DisplayList title="과일목록" items={["사과", "바나나", "포도"]} />
    </>
  );
}

function Button(props) {
  return (
    <>
      <button onClick={props.handleClick}> 클릭하세요 </button>
    </>
  );
}
// 함수형 컴퍼넌트
export function Props2() {
  // 일반 이벤트 콜백함수
  function handleClick() {
    alert("버튼이 클릭되었습니다.");
  }
  return (
    <div>
      {/* 함수 이름(함수형 변수)만 전달 */}
      <Button onClickFunc={handleClick} />
    </div>
  );
}

// 기본 props 설정
function Greeting1({ name = "손" }) {
  return <h1>안녕하세요. {name}님!</h1>;
}
function Greeting2({ name = "손", ...props }) {
  return (
    <h1>
      안녕하세요. {name}님! 나이는 {props.age}입니다.
    </h1>
  );
}
function Greeting3(props) {
  const { name = "손" } = props;
  const { age = 20 } = props;
  return (
    <h1>
      안녕하세요. {name}님! 나이는 {age}입니다.
    </h1>
  );
}
export function Props3() {
  return (
    <div>
      <Greeting1 />
      <Greeting1 name="홍길동" />
      <Greeting2 name="홍길동" age="30" />
      <Greeting2 />
      <Greeting3 />
      <Greeting3 name="홍길동" age="30" />
    </div>
  );
}

// 객체구조분해할당으로 props 전달
function Profile({ name, age, job }) {
  return (
    <>
      <p>이름: {name}</p>
      <p>나이: {age}</p>
      <p>직업: {job}</p>
    </>
  );
}
export function Props4() {
  const user = {
    name: "홍길동",
    age: 20,
    job: "개발자",
  };
  return (
    <>
      <Profile {...user} />
    </>
  );
}

// chilren으로 props 전달하기
function Card(props) {
  return (
    <>
      <h2>{props.title}</h2>
      {/* children은 props의 기본 속성 */}
      {props.children}
    </>
  );
}
export function Props5() {
  return (
    <>
      <Card title="카드 제목">
        <p>카드내용입니다.</p>
        <button>자세히 보기</button>
      </Card>
    </>
  );
}
