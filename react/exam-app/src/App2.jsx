// import * as E from "./2-jsx/Jsx";
// import Library from "./2-jsx/Library";
// import * as Ex from "./2-jsx/Ex";
import * as C from "./3-component/Component";

function App() {
  // 리액트 엘리먼트는 하나일때는 바로 반환
  // 단, JSX 태그 안에서 여러개일때는 {E.E6} 형식으로 반환
  // return E.E6;
  // 함수형 컴퍼넌트는 <Library />태그형식으로 반환
  // return <Library />;
  // return (
  //   <>
  //     <Ex.Hello name="홍길동" />
  //     <Ex.Sum num1={1} num2={2} />
  //     <Ex.DrinkMachine price={1000} />
  //     <Ex.Greeting hour={1} />
  //   </>
  // );
  return (
    <>
      <C.GreetingClass name="홍길동" />
      <C.GreetingFunc name="김길동" />
    </>
  );
}

export default App;
