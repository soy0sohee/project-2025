import React, { useState } from "react";

function Button(props) {
  const style_btn = {
    padding: "10px 30px",
    borderRadius: "30px",
    fontSize: "16px",
    fontWeight: "bold",
    border: "2px solid #fff",
    margin: "10px 10px 0",
    color: "white", // or연산자를 통한 조건식 렌더링
    backgroundColor: props.bgColor || "red",
  };

  return (
    <div>
      <button style={style_btn} onClick={props.onClick}>
        {props.title}
      </button>
    </div>
  );
}

export function ButtonProps() {
  const [color, setColor] = useState("red");

  const style_bg = {
    width: "400px",
    height: "600px",
    backgroundColor: color,
    display: "flex",
    flexDirection: "column",
  };

  return (
    <div style={style_bg}>
      <Button
        onClick={() => {
          setColor("red");
        }}
        title="빨강"
        bgColor="red"
      />
      <Button
        onClick={() => {
          setColor("blue");
        }}
        title="파랑"
        bgColor="blue"
      />
      <Button
        onClick={() => {
          setColor("black");
        }}
        title="검정"
        bgColor="black"
      />
    </div>
  );
}
