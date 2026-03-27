import { useNavigate } from "react-router-dom";
import { useState } from "react";

function Post() {
  const [title, setTitle] = useState("");
  const [rating, setRating] = useState("5");
  const [file, setFile] = useState([]);
  const [content, setContent] = useState("");

  const API_BASE = "http://localhost:8080/api";
  const navigate = useNavigate();

  const close = () => {
    navigate("/");
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!confirm("리뷰는 한번 등록하면 수정이 불가합니다.")) {
      return;
    }

    const formData = new FormData();

    const data = { title, rating, content };
    formData.append(
      "data",
      new Blob([JSON.stringify(data)], { type: "application/json" }),
    );
    formData.append("file", file);

    fetch(`${API_BASE}/create`, {
      method: "POST",
      body: formData,
    })
      .then((response) => response.text())
      .then(() => navigate("/"))
      .catch((error) => console.log("등록 실패: " + error));
  };

  return (
    <>
      <div className="post-page">
        <div className="post-header">
          <h1 className="post-header__title">맛집 정보 입력</h1>
          <button className="btn btn-icon" type="button" onClick={close}>
            ✕
          </button>
        </div>
        <form className="post-form" onSubmit={handleSubmit} encType="multipart/form-data">
          <div className="form-group">
            <label className="form-label" htmlFor="title">상호명</label>
            <input
              className="form-input"
              type="text"
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="방문한 맛집 이름을 입력하세요"
            />
          </div>
          <div className="form-group">
            <label className="form-label" htmlFor="rating">만족도</label>
            <select
              className="form-select"
              id="rating"
              value={rating}
              onChange={(e) => setRating(e.target.value)}
            >
              <option value="5">최고에요</option>
              <option value="4">추천해요</option>
              <option value="3">무난해요</option>
              <option value="2">별로에요</option>
              <option value="1">최악에요</option>
            </select>
          </div>
          <div className="form-group">
            <label className="form-label" htmlFor="file">사진 첨부</label>
            <input
              className="form-input"
              type="file"
              id="file"
              onChange={(e) => setFile(e.target.files[0])}
              multiple="multiple"
            />
          </div>
          <div className="form-group">
            <label className="form-label" htmlFor="content">느낀점</label>
            <input
              className="form-input"
              type="text"
              id="content"
              value={content}
              onChange={(e) => setContent(e.target.value)}
              placeholder="맛, 분위기, 서비스 등 솔직한 후기를 남겨주세요"
            />
          </div>
          <button className="btn btn-primary btn-full" type="submit">리뷰 등록하기</button>
        </form>
      </div>
    </>
  );
}

export default Post;
