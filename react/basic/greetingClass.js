// class Greeting extends React.Component {
//   render() {
//     return <h1 className="greeting">Hello World!</h1>;
//   }
// }

// const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(React.createElement(Greeting));

class Hello extends React.Component {
  render() {
    return (
      <>
        안녕하세요,
        {this.props.name}
      </>
    );
  }
}

const hello = ReactDOM.createRoot(document.getElementById("hello"));
hello.render(React.createElement(Hello, { name: "영희" }));
