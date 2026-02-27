// 연습문제1: 동적 표현식 렌더링
// 목표: props로 받은 이름을 화면에 출력하는 간단한 컴포넌트 연습
// 요구사항
// 1. Namecard 함수형 컴포넌트를 만들고, user 객체의 정보(이름,나이)를 JSX에 넣어서
//   표현한다.
// 2. props는 사용안함.
import React from "react";

const user = {
  name: "홍길동",
  age: 25,
};

export function Namecard() {
  return (
    <>
      <h3>이름 : {user.name}</h3>
      <h3>나이 : {user.age}</h3>
    </>
  );
}

//연습문제2: props를 이용해 이름과 나이 출력하기
// 목표: props로 전달받은 이름과 나이를 사용자 정의 컴퍼넌트에 출력한다.
// 요구사항
// 1. Greeting 함수형 컴퍼넌트를 만들고, props로 전달받은 name과 age를
//   출력한다.
// 2. 출력양식 : 안녕하세요, 홍길동님! 당신의 나이는 25살입니다.
export function Greeting(props) {
  return (
    <>
      <h3>
        안녕하세요, {props.name}님! 당신의 나이는 {props.age}살입니다.
      </h3>
    </>
  );
}

export function GreetingProps() {
  return (
    <>
      <Greeting name="홍길동" age="25" />
    </>
  );
}

//연습문제3: 다음의 객체배열의 데이터를 출력하시오.
//목표: props로 전달받은 객체 배열의 데이터를 조작할 수 있다.
//요구사항
// 1. ProductListProps라는 함수형 컴퍼넌트 설계
// 2. 상품이름 - 가격(원)으로 출력한다.
// 출력 예) 1. 노트북 - 8000원
//          2. 스마트폰 - 4000원

// // 배열로 풀기
// const products = [
//   {
//     id: 1,
//     product: "노트북",
//     price: 8000,
//   },
//   { id: 2, product: "스마트폰", price: 4000 },
//   { id: 3, product: "맥미니", price: 4000 },
// ];

// export function Product() {
//   return (
//     <>
//       <ol>
//         {products.map((item) => {
//           return (
//             <li key={item.id}>
//               {item.product} - {item.price}원
//             </li>
//           );
//         })}
//       </ol>
//     </>
//   );
// }

// // 프롭스로 풀기
// export const ProductList = (props) => {
//   return (
//     <>
//       <span>
//         {props.name} - {props.price}원
//       </span>
//       <br />
//     </>
//   );
// };
// // 함수형 컴퍼넌트
// export function ProductList() {
//   return (
//     <>
//       <ProductList name="노트북" price="8000" />
//       <ProductList name="스마트폰" price="4000" />
//       <ProductList name="맥미" price="4000" />
//     </>
//   );
// }

// 프롭스 배열로 풀기
// 함수형 변수(익명 함수) = 화살표 함수
export const ProductListProps = ({ product1 }) => {
  // return <>{products[0].name}</>;
  // key는 유니크하면됨
  return (
    <ul>
      {product1.map((item, index) => {
        return (
          <li key={index}>
            {item.id}. {item.name} - {item.price}
          </li>
        );
      })}
    </ul>
  );
};
