import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/AuthContext";
import { useState, useEffect } from "react";

function PostDetail() {
  const { id } = useParams();
  const { user } = useAuth();
  const [post, setPost] = useState(null);
  const [comment, setComment] = useState("");

  const API_BASE = "http://localhost:8080/api";
  const navigate = useNavigate();

  useEffect(() => {
    if (!id) return;

    fetch(`${API_BASE}/postDetail/${id}`, {
      method: "GET",
    })
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        console.log(json);
        setPost(json);
      })
      .catch((error) => {
        console.log("조회 에러" + error);
      });
  }, [id]);

  const save = () => {
    fetch(`${API_BASE}/commentSave/${id}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        content: comment,
      }),
    })
      .then((response) => {
        return response.text();
      })
      .then(() => {
        alert("리뷰가 등록되었습니다.");
        navigate(0);
      })
      .catch((error) => {
        console.log("리뷰등록 오류" + error);
      });
  };

  const remove = (id) => {
    if (!confirm("리뷰를 삭제하시겠습니까?")) {
      return;
    }

    fetch(`${API_BASE}/commentRemove/${id}`, {
      method: "POST",
    })
      .then((response) => {
        return response.text();
      })
      .then((text) => {
        if (text === "ok") {
          navigate(0);
        }
      })
      .catch((error) => {
        console.log("리뷰등록 오류" + error);
      });
  };

  const prev = () => {
    navigate("/");
  };

  if (!post) {
    return (
      <>
        <p>로딩중</p>
      </>
    );
  }
  return (
    <>
      <div className="wrapper">
        <div className="post-detail" key={post.id}>
          {post.postFile?.map((imgitem) => (
            <img
              className="post-detail__img"
              key={imgitem.id}
              src={`http://localhost:8080/uploads/${imgitem.fileName}`}
              alt="이미지"
            />
          ))}
          <div className="post-detail__info">
            <h3 className="post-detail__title">{post.title}</h3>
            <p className="post-detail__date">{post.dateTime}</p>
            <p className="post-detail__rating">★ {post.rating}</p>
            <p className="post-detail__content">{post.content}</p>
          </div>
        </div>

        <section className="comments-section">
          {post.comment?.map((commentitem) => (
            <div className="comment-item" key={commentitem.id}>
              <p className="comment-item__user">{user}</p>
              <p className="comment-item__content">{commentitem.content}</p>
              <p className="comment-item__date">{commentitem.dateTime}</p>
              <button
                className="btn-danger-sm"
                onClick={() => {
                  remove(commentitem.id);
                }}
              >
                삭제
              </button>
            </div>
          ))}
        </section>

        <div className="comment-form">
          <div className="form-group">
            <label className="form-label" htmlFor="comment">리뷰쓰기</label>
            <input
              className="form-input"
              type="text"
              id="comment"
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
          </div>
          <button className="btn btn-primary" onClick={save}>등록</button>
        </div>

        <button className="btn btn-back" onClick={prev}>← 이전</button>
      </div>
    </>
  );
}

export default PostDetail;
