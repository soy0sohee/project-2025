//Ex.jsx
// 연습문제1: 마우스 오버와 마우스 아웃 이벤트
// 목표: onMouseEnter와 onMouseLeave 이벤트를 사용하여
// 마우스가 특정 영역에 들어오거나 나갈 때 상태를 변경하는 방법을 학습합니다.
// 요구사항: 다음 조건에 맞는 컴포넌트를 작성하세요.
// 마우스가 박스 위에 올라가면 배경색이 변경됩니다.
// 마우스가 박스를 벗어나면 원래 배경색으로 돌아옵니다.
// 상태로 관리되는 메시지를 화면에 출력하세요.
// (예: "마우스가 들어왔습니다", "마우스가 나갔습니다")
import React, { useState } from "react";

export function Ex1() {
  const [message, setMessage] = useState("박스에 마우스를 올려보세요.");
  const [color, setColor] = useState("orange");

  const handleMouseEnter = () => {
    setMessage("마우스가 들어왔습니다.");
    setColor("coral");
  };
  const handleMouseLeave = () => {
    setMessage("마우스가 나갔습니다.");
    setColor("orange");
  };

  const style = {
    height: "100px",
    backgroundColor: color,
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: "5px",
    transition: "0.3s",
    fontSize: "16px",
  };

  return (
    <div>
      <h3>박스에 마우스를 올려보세요.</h3>
      <div
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        style={style}
      >
        {message}
      </div>
    </div>
  );
}

// 연습문제2: 폼 제출 이벤트 처리하기
// 목표: onSubmit 이벤트를 통해 폼 제출을 처리하고 기본 동작을 방지하는 방법
// 요구사항 - 다음 조건에 맞는 컴포넌트를 작성하세요.
// 1.사용자 이름과 나이를 입력하는 폼을 만드세요.
// 2.폼이 제출되면 입력값을 콘솔에 출력하고 입력 필드를 비웁니다.
// 3.기본 폼 제출 동작을 방지하세요 (e.preventDefault() 사용).
export function Ex2() {
  const [inputname, setInputname] = useState("");
  const [inputage, setInputage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (inputname === "") {
      alert("이름을 입력해주세요.");
    } else if (inputage === "") {
      alert("나이를 입력해주세요.");
    } else {
      console.log("이름 : " + inputname + ", 나이 : " + inputage);
      alert("제출되었습니다.");
      setInputname("");
      setInputage("");
    }
  };

  const style = {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    gap: "8px",
  };
  const inputStyle = {
    height: "36px",
    fontSize: "15px",
  };
  const buttonStyle = {
    height: "48px",
    backgroundColor: "#333",
    border: "0",
    color: "#fff",
    fontSize: "15px",
    cursor: "pointer",
  };

  return (
    <div>
      <h3>폼 제출 예제</h3>
      <form action="http://myserver.com" onSubmit={handleSubmit} style={style}>
        <input
          style={inputStyle}
          type="text"
          placeholder="이름"
          value={inputname}
          onChange={(e) => {
            setInputname(e.target.value);
          }}
        />
        <input
          style={inputStyle}
          type="number"
          placeholder="나이"
          value={inputage}
          onChange={(e) => {
            setInputage(e.target.value);
          }}
        />
        <button type="submit" style={buttonStyle}>
          제출하기
        </button>
      </form>
    </div>
  );
}
export function Ex2_1() {
  const [inputvalue, setInputvalue] = useState({ name: "", age: "" });

  const handleChange = (e) => {
    // input태그의 name속성과 value속성을 가져옴
    const { name, value } = e.target;
    setInputvalue((prev) => {
      return { ...prev, [name]: value };
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (inputvalue.name === "") {
      alert("이름을 입력해주세요.");
    } else if (inputvalue.age === "") {
      alert("나이를 입력해주세요.");
    } else {
      console.log("이름 : " + inputvalue.name + ", 나이 : " + inputvalue.age);
      alert("제출되었습니다.");
      setInputvalue({ name: "", age: "" });
    }
  };

  const style = {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    gap: "8px",
  };
  const inputStyle = {
    height: "36px",
    fontSize: "15px",
  };
  const buttonStyle = {
    height: "48px",
    backgroundColor: "#333",
    border: "0",
    color: "#fff",
    fontSize: "15px",
    cursor: "pointer",
  };

  return (
    <div>
      <h3>폼 제출 예제</h3>
      <form action="http://myserver.com" onSubmit={handleSubmit} style={style}>
        <input
          name="name"
          type="text"
          placeholder="이름"
          value={inputvalue.name}
          onChange={handleChange}
          style={inputStyle}
        />
        <input
          name="age"
          type="number"
          placeholder="나이"
          value={inputvalue.age}
          onChange={handleChange}
          style={inputStyle}
        />
        <button type="submit" style={buttonStyle}>
          제출하기
        </button>
      </form>
    </div>
  );
}

// 연습문제 3: 입력 필드에서 글자 수 제한하기
// 목표: 입력 필드의 입력값을 상태로 관리하고 글자 수 제한하는 방법을 학습합니다.
// 요구사항: 다음 조건에 맞는 컴포넌트를 작성하세요.
// 1.사용자가 텍스트를 입력할 수 있는 입력 필드가 있습니다.
// 2.입력값은 최대 10자까지만 허용됩니다.
// 3.입력값의 길이에 따라 남은 글자 수를 화면에 표시하세요.
export function Ex3() {
  const [inputvalue, setInputvalue] = useState("");
  const [inputlength, setInputlength] = useState("");

  const handleOnChage = (e) => {
    if (e.target.value.length <= 10) {
      setInputvalue(e.target.value);
      setInputlength(e.target.value.length);
    }
  };

  const style = {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    gap: "8px",
  };
  const inputStyle = {
    height: "36px",
    fontSize: "15px",
  };

  return (
    <div>
      <h3>글자 수 제한 입력 필드</h3>
      <div style={style}>
        <input
          type="text"
          min={0}
          max={10}
          value={inputvalue}
          onChange={handleOnChage}
          style={inputStyle}
        />
        <p>남은글자 : {10 - inputlength}</p>
      </div>
    </div>
  );
}
