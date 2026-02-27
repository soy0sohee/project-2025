import React, { useState, useEffect } from "react";
// 서버와 JS에서 통신 사용
// JS : fetch() 함수
// Axios : axios() 함수
// 설치 : npm install axios

// 연습문제1: API 호출 및 데이터 로드
// 목표: 컴포넌트가 마운트될 때 API 호출을 통해 데이터를 가져와
//    화면에 표시하세요.
// 요구사항:
// 1. URL : https://jsonplaceholder.typicode.com/posts
// 2. 상위 10개의 포스트 테이터만 출력하세요.
// 3. useEffect를 사용하여 컴포넌트가 마운트될 때 데이터를 한번만 로드하세요.
// 4. 데이터를 로드한 후 상태에 저장하고 화면에 출력하세요.
// 힌트: fetch 또는 axios 모듈 사용 가능합니다.

// [
// {
//   "userId": 1,
//   "id": 1,
//   "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//   "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
// },

// JS의 fetch 함수

export function DataFetchJS() {
  const [data, setData] = useState([]);

  // async / await 구문 : JS에서 비동기적인 처리를 할 때 사용하는 키워드
  // promise / then 구문 : 구조화된 호출과 응답을 처리하는 예약어
  const fetchData = async () => {
    try {
      const response = await fetch(
        "https://jsonplaceholder.typicode.com/posts",
      );

      console.log(response);

      if (!response.ok) {
        throw new Error("네트워크 응답이 올바르지 않습니다.");
      }

      // response : HTTP통신의 결과값(HTTP 헤더+바디 data)
      // json() : JSON형태의 문자열을 json KV객체로 변환해주는 함수
      const result = await response.json();

      console.log(result.slice(0, 10));

      setData(result.slice(0, 10));
    } catch (error) {
      console.log("데이터 가져오기 실패", error);
    }
  };

  useEffect(() => {
    console.log("DidMount 확인");
    fetchData();
  }, []);

  return (
    <>
      <div>통신으로 가져온 데이터(fetch)</div>
      <ul>
        {data.map((item, index) => {
          return <li key={index}>{item.title}</li>;
        })}
      </ul>
    </>
  );
}

// Axios의 Axios 함수
import axios from "axios";
export function DataFetchAxios() {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "https://jsonplaceholder.typicode.com/posts",
      );
      console.log(response.data.slice(0, 10));
      setData(response.data.slice(0, 10));
    } catch (error) {
      throw new Error("데이터 가져오기 오류", error);
    }
  }; // 함수데이터

  useEffect(() => {
    fetchData(); // 마운트 시 한번만 호출
  }, []); // 의존성 배열이 비어있으면, 마운드/언마운트 시 호출

  return (
    <>
      <div>통신으로 가져온 데이터(axios)</div>
      <ul>
        {data.map((item, index) => {
          return <li key={index}>{item.title}</li>;
        })}
      </ul>
    </>
  );
}

// 통신응답으로 반환된 JSON형태의 문자열
// [
//  {
//   "userId": 1,
//   "id": 1,
//   "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//   "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
//  },{...},{...}
// ]

// JS 객체
// [
//  {
//   userId : 1,
//   id: 1,
//   title: "sunt aut facere repel",
//   body : "quia et suscip"
//  },{...},{...}
// ]

// 예제 = 윈도우 크기 변경 감지
// 목표 : 윈도우의 크기가 변경될 때마다
//        현재 창의 너비를 화면에 표시하세요.
// 요구사항:
//        useEffect로 이벤트 리스너를 등록하고 창 크기가 변경될 때마다 상태를 업데이트하세요.
//        언마운트 시 이벤트 리스너를 해제하세요.
//        힌트: window.addEventListener와 window.removeEventListener
export function WindowSizeTracker() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  const handleResize = () => {
    setWindowWidth(window.innerWidth);
  };

  // JS 이벤트리스너 등록 -> 마운트 시 한번
  //                 해제 -> 언마운트 시 한번
  useEffect(() => {
    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <>
      <h1>현재 창 너비 : {windowWidth}px</h1>
    </>
  );
}

// 연습예제 - 타이머 기능 (마운트 및 언마운트)
// 목표 : 컴포넌트가 마운트되면 1초마다 상태값이 증가하는 타이머를 시작하고,
//        컴포넌트가 언마운트될 때 타이머를 정리하세요.
// 요구사항 :
//        useEffect를 사용하여 마운트 시 타이머를 시작하고, 언마운트 시 타이머를 정리하세요.
//        1초마다 상태값을 증가시키세요.
//        힌트: setInterval과 clearInterval 사용
export function Timer() {
  const [seconds, setSeconds] = useState(0); // 타이머 : 0초

  useEffect(() => {
    // 마운트 시 타이머 설정
    setInterval(() => {
      // setSeconds(seconds + 1);
      setSeconds((prev) => {
        // 매개변수로 실시간 현재값을 받아야할때 클로저(화살표함수) 사용
        return prev + 1;
      });
    }, 1000); // 처음 레더링 때 한번 실행

    // 언마운트 시 타이머 제거
    return () => {
      clearInterval(timer);
    };
  }, []);

  return (
    <>
      <h1>타이머 : {seconds}초</h1>
    </>
  );
}
