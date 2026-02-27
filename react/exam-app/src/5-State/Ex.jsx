import React, { useState } from "react";

// 연습문제 1: 버튼을 클릭할 때마다 색상 변경하기
// 설명: 버튼을 클릭할 때마다 배경 색상이 빨강, 초록, 파랑으로
//    순차적으로 변경되도록 만드세요.
function ColorChange(props) {
  const div_style = {
    width: "100%",
    height: "200px",
    margin: "0",
    padding: "40px 20px",
    boxSizing: "border-box",
    backgroundColor: props.color || "red",
  };

  return (
    <div style={div_style}>
      <h3>현재 색상 : {props.color}</h3>
      <button onClick={props.onClick}>색상 변경</button>
    </div>
  );
}

export function ColorChangeProps() {
  const [color, setColor] = useState("red");

  function change() {
    if (color == "red") {
      setColor("green");
    } else if (color == "green") {
      setColor("blue");
    } else {
      setColor("red");
    }
  }

  return <ColorChange color={color} onClick={change} />;
}

// 연습문제 2: 체크박스 상태 관리하기
// 설명: 체크박스를 클릭하면 "ON" 또는 "OFF"라는 텍스트가
//   화면에 표시되도록 만드세요.
// 힌트: onChange, checked 속성을 이용
export function CheckBox() {
  const [checked, setChecked] = useState(false);

  return (
    <div>
      <hr />
      <label>
        <input
          type="checkbox"
          checked={checked}
          onChange={(e) => {
            setChecked(e.target.checked);
          }}
        />
        {checked ? "ON" : "OFF"}
      </label>
    </div>
  );
}

// 연습문제 3: 숫자 제한 걸기
// 설명: 숫자를 증가시키되, 숫자가 10 이상이면
//   더 이상 증가하지 않도록 제한하세요.
function CountNumber(props) {
  return (
    <div>
      <hr />
      <h3>현재 숫자 : {props.number}</h3>
      <p>{props.noChange}</p>
      <button onClick={props.onClick}>1 증가</button>
    </div>
  );
}
export function CountNumberProps() {
  const [number, setNumber] = useState(0);
  const [text, setText] = useState("");

  function count() {
    if (number == 0 || number < 10) {
      setNumber(number + 1);
      // setText(number + "+1 증가");
    } else if (number == 10) {
      setNumber(number);
      setText("더 이상 증가할 수 없는 최대 숫자입니다.");
    }
  }
  return <CountNumber number={number} noChange={text} onClick={count} />;
}

// 연습문제 4: 버튼을 클릭할 때마다 리스트에 항목 추가하기
// 설명: 버튼을 클릭하면 입력 필드의 값을 리스트에 추가하고,
//   추가된 항목들을 화면에 표시하세요.
// 힌트: [], ["aaa", "bbb", "ccc"]
export function ItemList() {
  const [items, setItems] = useState([]);
  const [inputValue, setInputValue] = useState("");

  function handleAddItem() {
    setItems([...items, inputValue]);
  }

  return (
    <>
      <input
        type="text"
        value={inputValue}
        onChange={(e) => {
          setInputValue(e.target.value);
        }}
      />
      <button onClick={handleAddItem}>추가</button>
      <ul>
        {items.map((item, index) => {
          return <li key={index}>{item}</li>;
        })}
      </ul>
    </>
  );
}
