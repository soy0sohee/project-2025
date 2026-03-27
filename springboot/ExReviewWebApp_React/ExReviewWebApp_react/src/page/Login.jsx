import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../hooks/AuthContext";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { setIsLoggedIn, setUser } = useAuth();

  const API_BASE = "http://localhost:8080/api/users";
  const navigate = useNavigate();

  const login = () => {
    if (email == "" || password == "") {
      alert("아이디/비밀번호를 다시 입력해주세요.");

      setEmail(email);
      setPassword(password);

      return;
    } else {
      setEmail("");
      setPassword("");
    }

    fetch(`${API_BASE}/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    })
      .then((response) => {
        return response.text();
      })
      .then((text) => {
        if (text == "exception") {
          alert("아이디/비밀번호를 다시 입력해주세요.");

          return;
        }
        localStorage.setItem("JWT_TOKEN", text);
        setIsLoggedIn(true);
        setUser(email);
        navigate("/");
      })
      .catch((error) => {
        console.log("로그인 오류: " + error);
      });
  };

  const googleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  return (
    <>
      <div className="auth-top">
      <div className="auth-card">
        <h1 className="auth-logo">맛집로그</h1>
        <div className="auth-header">
          <h2 className="auth-header__title">만나서 반가워요</h2>
          <p className="auth-header__desc">
            계정에 로그인하고 맛집 리뷰를 시작해보세요.
          </p>
        </div>
        <form className="auth-form" action="/api/login">
          <div className="form-group">
            <label className="form-label" htmlFor="email">
              이메일
            </label>
            <input
              className="form-input"
              type="text"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="your@email"
            />
          </div>
          <div className="form-group">
            <label className="form-label" htmlFor="pasword">
              비밀번호
            </label>
            <input
              className="form-input"
              type="password"
              id="pasword"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="********"
            />
          </div>
        </form>
        <div className="auth-btns">
          <button
            className="btn btn-primary btn-full"
            type="button"
            id="btn_login"
            onClick={login}
          >
            로그인
          </button>
          <div className="divider">또는</div>
          <button
            className="btn btn-google"
            type="button"
            id="btn_googleLogin"
            onClick={googleLogin}
          >
            Google로 계속하기
          </button>
        </div>
        <span className="auth-footer-text">
          계정이 없으신가요?{" "}
          <Link className="link" to="/signup">
            회원가입
          </Link>
        </span>
      </div>
      </div>
    </>
  );
}

export default Login;
