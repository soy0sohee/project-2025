import React, { useState, useEffect, useRef } from "react";

export function CalculatorMachine(props) {
  const wrap = {
    border: "1px solid #ddd",
    width: "400px",
    boxSizing: "boreder-box",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    gap: "20px",
    padding: "30px",
    fontSize: "16px",
  };
  const h1 = {
    margin: "0",
    fontSize: "28px",
  };
  const h2 = {
    margin: "0",
    fontSize: "18px",
  };
  const inputWrap = {
    width: "100%",
    display: "flex",
    flexDirection: "column",
    gap: "10px",
  };
  const input = {
    width: "100%",
    display: "flex",
    justifyContent: "space-between",
  };
  const inputBox = {
    width: "300px",
    height: "32px",
    textAlign: "center",
    fontWeight: "bold",
    fontSize: "15px",
    color: "#333",
    backgroundColor: "#ddd",
    border: "1px solid #ccc",
  };
  const resultBox = {
    color: "#eee",
    backgroundColor: "#666",
    border: "1px solid #666",
  };
  const buttonWrap = {
    width: "100%",
    display: "flex",
    flexDirection: "column",
    gap: "10px",
  };
  const button = {
    width: "100%",
    height: "40px",
    fontSize: "15px",
    cursor: "pointer",
    backgroundColor: "#286CD1",
    color: "#fff",
    border: "0",
  };
  const clearbutton = {
    backgroundColor: "#F13232",
  };

  const [value1, setValue1] = useState("");
  const [value2, setValue2] = useState("");
  const [resultNum, setResultNum] = useState(0);

  function sum() {
    const result = Number(value1) + Number(value2);
    setResultNum(result);
    console.log(`덧셈 : ${result}`);
  }
  function minus() {
    const result = Number(value1) - Number(value2);
    setResultNum(result);
    console.log(`뺄셈 : ${result}`);
  }
  function multi() {
    const result = Number(value1) * Number(value2);
    setResultNum(result);
    console.log(`곱셈 : ${result}`);
  }
  function divde() {
    const result = Number(value1) / Number(value2);
    setResultNum(result);
    console.log(`나눗셈 : ${result}`);
  }
  function clear() {
    setResultNum(() => {
      return 0;
    });
    console.log(`지우기 : 0`);
  }

  return (
    <div style={wrap}>
      <h1 style={h1}>React App</h1>
      <h2 style={h2}>계산기 프로그램을 작성해보자</h2>
      <div style={inputWrap}>
        <div style={input}>
          <label>숫자1</label>
          <input
            style={inputBox}
            type="number"
            value={value1}
            onChange={(e) => {
              setValue1(e.target.value);
            }}
          />
        </div>
        <div style={input}>
          <label>숫자2</label>
          <input
            style={inputBox}
            type="number"
            value={value2}
            onChange={(e) => {
              setValue2(e.target.value);
            }}
          />
        </div>
        <div style={input}>
          <label>연산 결과</label>
          <input
            style={{ ...inputBox, ...resultBox }}
            type="number"
            value={resultNum}
            readOnly
          />
        </div>
      </div>
      <div style={buttonWrap}>
        <button style={button} onClick={sum}>
          덧셈
        </button>
        <button style={button} onClick={minus}>
          뺄셈
        </button>
        <button style={button} onClick={multi}>
          곱셈
        </button>
        <button style={button} onClick={divde}>
          나눗셈
        </button>
        <button style={{ ...button, ...clearbutton }} onClick={clear}>
          지우기
        </button>
      </div>
    </div>
  );
}
