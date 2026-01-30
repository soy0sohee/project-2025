// 생성자 함수이므로 첫글자는 대문자
// function Greeting() {
//   return <h1 className="greeting">Hello World!</h1>;
// }

// const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(React.createElement(Greeting));

// function Hello(props) {
//   return (
//     <>
//       안녕하세요,
//       {props.name}
//     </>
//   );
// }

// const hello = ReactDOM.createRoot(document.getElementById("hello"));
// hello.render(<Hello name="철수" />);

function MyConponent({ p1, p3 }) {
  return (
    <>
      <h1>props 객체 사용</h1>
      <p>
        {p1}, {p3}
      </p>
    </>
  );
}
function App() {
  return (
    <>
      <MyConponent p1={"HTML5"} p2={"CSS"} p3={"JavaScript"} p4={"jQuery"} />
    </>
  );
}
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
