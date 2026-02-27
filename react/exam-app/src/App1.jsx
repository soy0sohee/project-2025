// import "./App.css";

// import { element1 } from "./1-element/Element"; // 하나 임포트
// import { element1, element2 } from "./1-element/Element"; // 여러개 임포트
// import * as E from "./1-element/Element"; // 전체 임포트
import * as Ex from "./1-element/Ex";

// const plist = [
//   {
//     id: 1,
//     name: "노트북",
//     price: 8000,
//   },
//   { id: 2, name: "스마트폰", price: 4000 },
//   { id: 3, name: "맥미니", price: 4000 },
// ];

function App() {
  // 리액트 엘리먼트 -> {element} 중괄호로 렌더링
  // 리액트 컴퍼넌트(함수형,클래스형) -> <element /> 태그형식으로 렌더링
  // return (
  //   <>
  //     {E.element1}
  //     {element1}
  //     {element2}
  //     {E.element3}
  //     {E.element4}
  //     {E.element5}
  //     <E.Hello name="홍길동" />
  //     <E.ConfirmDialog />
  //   </>
  // );
  return (
    <>
      <Ex.Namecard />
      <Ex.GreetingProps />
      <Ex.Product />
      <Ex.ProductList />
      <Ex.ProductListProps product1={plist} />
    </>
  );
}

export default App;
