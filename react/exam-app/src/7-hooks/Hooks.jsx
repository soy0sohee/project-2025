import React, { useState, useMemo, useCallback, useRef } from "react";

// 리액트 훅 : 함수형 컴포넌트에서도 클래스 컴포넌트의 기능(오버라이드 함수)들을 사용하도록 고안된 기능
// useState, useEffect, useContext등 다양한 훅이 제공됨

// useMemo
// : 특정 연산의 결과를 메모리제이션(저장)하여 불필요한 반복적 계산을 하지 않게 해줌.
// : 컴포넌트가 렌더링 될 때마다 매번 계산하는 대신
//   -> 의존성 배열에 명시된 상태값이 변경될 때만 해당 연산을 수행
// 용도
// : 비용이 큰 연산을 최적화 할 때(통신, 큰 데이터 연산)
// : 렌더링 성능을 개선할 때

// 예제) 사용자가 입력한 값의 2배를 계산하는 함수
export function CounterMemo() {
  // inputValue가 바뀌면, useMemo는 호출됨
  const [count, setCount] = useState(0);
  // inputValue가 바뀌면, useMemo는 호출되지 않음
  const [inputValue, setInputValue] = useState("");

  // count 상태 변수가 변경되지 않는 한 doubleValue가 재연산되지 않게 함
  const doubleValue = useMemo(() => {
    console.log("두배 계산중..");
    return count + count;
  }, [count]);

  return (
    <>
      <h1>useMemo</h1>
      <h3>입력 값 : {count}</h3>
      <h3>입력 값 x2 : {doubleValue}</h3>
      <input
        type="number"
        value={count}
        onChange={(e) => {
          setCount(parseInt(e.target.value));
        }}
      />
      <br />
      <input
        type="text"
        value={inputValue}
        onChange={(e) => {
          setInputValue(e.target.value);
        }}
      />
    </>
  );
}

// useCallback
// : 메모이제이션된 콜백 함수를 반환하여 함수가 불필요하게 생성되는것을 방지하는 훅
// : useMemo = 값 반환, useCallback = 함수 반환
// : 메모이제이션 -> 한번 계산한 결과를 기억에 두고, 다시 계산하지 않는것
// 용도
// : 컴포넌트가 렌더링 될 때마다 동일한 함수를 다시 생성하는 것을 방지
// : 자식 컴포넌트에 함수를 props로 전달할 때, 불필요한 재렌더링 방지
export function ConterCallback() {
  const [count, setCount] = useState(0);
  const [inputValue, setInputValue] = useState("");

  const doubleValue = useMemo(() => {
    console.log("두배 계산중..");
    return count * 2;
  }, [count]);

  // 이벤트함수를 함수형 변수로 바인딩했을때, 이벤트객체 e를 가져올 수 있음
  const handleChange = useCallback((e) => {
    console.log("useCallback 메모이제이션");
    console.log(e.target.value);
    setCount(parseInt(e.target.value));
  }, []);

  return (
    <>
      <h1>useMemo</h1>
      <h3>입력 값 : {count}</h3>
      <h3>입력 값 x2 : {doubleValue}</h3>
      <input type="number" value={count} onChange={handleChange} />
    </>
  );
}

// child 컴포넌트
const Child = React.memo((props) => {
  console.log("Child 렌더링됨");

  return <button onClick={props.onClick}>자식 버튼</button>; // 자식버튼 클릭
});

export function Parent() {
  const [count, setCount] = useState(0);

  const childhandleClick = useCallback(() => {
    console.log("자식 버튼 클릭");
  }, []); // 이 함수는 처음 한번만 만들고, 다시 만들지 마

  const parenthandleClick = () => {
    setCount(count + 1);
  }; // 이 함수는 실행 될 때 마다 단들어줘

  return (
    <>
      <h1>{count}</h1>
      {/* 부모버튼 클릭 -> onclick타고 함수 실행 */}
      <button onClick={parenthandleClick}>부모 버튼</button>

      {/* 부모가 childhandleClick을 자식에게 하사함 */}
      <Child onClick={childhandleClick} />
    </>
  );
}

// useRef
// : 리액트에서 변경 가능한 참조 객체를 제공하는 훅
// : useRef로 생성한 객체는 컴퍼넌트가 리렌더링 되더라도 값이 유지됨
// : 화면에 보여주는 값으로는 사용 X
// 사용방법
// : DOM 요소에 접근하기 위해(포커스, 스크롤제어 등)
// : 상태값과 다르게 리렌더링 없이 값 유지가 필요한 경우(이전 값 저장, 타이머 등)
// : 성능 최적화에 유리함 -> 값이 변하더라도 불필요한 리렌더링 방지
export function CountRef() {
  // useState() = 값이 바뀌면 리렌더링 화면이 갱신됨, 화면 변경할 때는 useState
  const [count, setCount] = useState(0);
  // 일반 변수 = 값이 바뀌어도 렌더링 안 됨, 렌더링 시 값 0
  let clickCount = 0;
  // useRef() = 값이 바뀌어도 렌더링 안 됨, 렌더링 상관없이 값만 변경
  const countRef = useRef(0);

  const handleClick = () => {
    clickCount++;
    countRef.current += 1;
    setCount((prev) => {
      return prev + 1;
    });
  };

  console.log("리렌더링 = ", count);
  console.log("리렌더링 = ", clickCount);
  console.log("리렌더링 = ", countRef);

  return (
    <>
      <h2>Count : {count}</h2>
      <h2>버튼클릭횟수 : {countRef.current}</h2>
      <button onClick={handleClick}>+1 증가</button>
    </>
  );
}

export function CounterRefInput() {
  const [count, setCount] = useState(0);
  const clickCountRef = useRef(0);
  const inputRef = useRef(null);

  const handleClick = () => {
    setCount((prev) => {
      return prev + 1;
    });
    clickCountRef.current += 1;
    // 버튼 클릭시 입력창에 포커스 설정
    // getElementByID 대신 useRef로 input value에 접근
    if (inputRef.current) {
      inputRef.current.value = `현재 카운트 : ${count + 1}`;
      inputRef.current.focus();
    }
  };

  return (
    <>
      <h2>Counter : {count}</h2>
      <h2>Click Counter : {clickCountRef.current}</h2>
      <input type="text" ref={inputRef} placeholder="입력하세요" />
      <button onClick={handleClick}>증가 및 입력창 포커스</button>
    </>
  );
}

// Hook 훅의 규칙
// 1. 무조건 최상위 레벨에서만 호출해야 됨.
// 2. 반복문이나 조건문 또는 중첩된 함수 안에서 호출하면 안됨.
// 3. 컴퍼넌트가 렌더링 될때마다 매번 같은 순서로 호출되어야 함.
// 4. 리액트 함수 컴퍼넌트에서만 호출할 수 있다. 일반적인 JS함수에서 훅을 호출하면 안됨.
