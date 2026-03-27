import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";

function Signup() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordEncode, setPasswordEncode] = useState("");
  const userRole = "USER";

  const API_BASE = "http://localhost:8080/api/users";
  const navigate = useNavigate();

  const signup = () => {
    console.log(email, password, passwordEncode);

    if (password != passwordEncode) {
      alert("비밀번호가 다릅니다.");

      setEmail(email);
      setPassword(password);
      setPasswordEncode(passwordEncode);

      return;
    } else {
      setEmail("");
      setPassword("");
      setPasswordEncode("");
    }

    fetch(`${API_BASE}/signup`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password,
        userRole: userRole,
      }),
    })
      .then((response) => {
        return response.text();
      })
      .then((text) => {
        if (text == "ok") {
          navigate("/login");
        }
      })
      .catch((error) => {
        console.log("회원가입 에러: " + error);
      });
  };

  return (
    <>
      <div className="auth-top">
      <div className="auth-card">
        <h1 className="auth-logo">맛집로그</h1>
        <div className="auth-header">
          <h2 className="auth-header__title">만나서 반가워요</h2>
          <p className="auth-header__desc">계정에 로그인하고 맛집 리뷰를 시작해보세요.</p>
        </div>
        <form className="auth-form">
          <div className="form-group">
            <label className="form-label" htmlFor="email">이메일</label>
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
            <label className="form-label" htmlFor="paswword">비밀번호</label>
            <input
              className="form-input"
              type="password"
              id="paswword"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="8자 이상 입력"
            />
          </div>
          <div className="form-group">
            <label className="form-label" htmlFor="password_encode">비밀번호 확인</label>
            <input
              className="form-input"
              type="password"
              id="password_encode"
              value={passwordEncode}
              onChange={(e) => setPasswordEncode(e.target.value)}
              placeholder="비밀번호를 다시 입력하세요"
            />
          </div>
        </form>
        <div className="auth-btns">
          <button className="btn btn-primary btn-full" type="button" id="btn_signup" onClick={signup}>
            가입 완료하기
          </button>
        </div>
        <span className="auth-footer-text">
          계정이 있으신가요? <Link className="link" to="/login">로그인</Link>
        </span>
      </div>
      </div>
    </>
  );
}

export default Signup;
