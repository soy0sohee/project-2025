import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { useAuth } from "../hooks/AuthContext";

function Main() {
  const [list, setList] = useState([]);
  const [count, setCount] = useState(0);
  const { isLoggedIn, setIsLoggedIn } = useAuth();

  const API_BASE = "http://localhost:8080/api";
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`${API_BASE}/post`, {
      method: "GET",
    })
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        if (json) {
          console.log(json);

          setList(json);
          setCount(json.length);
        } else {
          return;
        }
      })
      .catch((error) => {
        console.log("데이터 불러오기 실패: " + error);
      });
  }, []);

  const handleLogout = () => {
    if (!confirm("로그아웃 하시겠습니까?")) {
      return;
    }
    localStorage.removeItem("JWT_TOKEN");
    setIsLoggedIn(false);
  };

  const handleLogin = () => {
    navigate("/login");
  };

  const post = () => {
    if (isLoggedIn === false) {
      alert("로그인이 필요합니다.");
      navigate("/login");
    } else {
      navigate("/post");
    }
  };

  const postDetail = (id) => {
    navigate(`/postDetail/${id}`);
  };

  return (
    <>
      <div className="wrapper">
        <header className="site-header">
          <h1 className="logo">맛집로그</h1>
          <div className="header-actions">
            <button type="button" className="btn btn-primary" onClick={post}>
              새 포스트
            </button>
            {isLoggedIn ? (
              <div className="auth-actions">
                <button className="btn btn-outline" onClick={handleLogout}>
                  로그아웃
                </button>
                <button className="btn btn-ghost">마이페이지</button>
              </div>
            ) : (
              <button className="btn btn-outline" onClick={handleLogin}>
                로그인
              </button>
            )}
          </div>
        </header>

        <section className="hero">
          <h2 className="hero__title">오늘은 어디서 먹었나요?</h2>
          <p className="hero__desc">
            직접 방문한 맛집의 솔직한 후기를 공유해보세요.
          </p>
        </section>

        <p className="review-count">총 {count}개의 리뷰</p>

        {list.length === 0 ? (
          <p className="empty-state">작성된 리뷰가 없습니다.</p>
        ) : (
          <div className="review-list">
            {list.map((item) => (
              <div
                className="review-card"
                key={item.id}
                onClick={() => postDetail(item.id)}
              >
                {item.postFile?.map((item) => (
                  <img
                    className="review-card__img"
                    key={item.id}
                    src={`http://localhost:8080/uploads/${item.fileName}`}
                    alt="이미지"
                  />
                ))}
                <div className="review-card__body">
                  <h3 className="review-card__title">{item.title}</h3>
                  <p className="review-card__date">{item.dateTime}</p>
                  <p className="review-card__rating">★ {item.rating}</p>
                  <p className="review-card__content">{item.content}</p>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </>
  );
}

export default Main;
