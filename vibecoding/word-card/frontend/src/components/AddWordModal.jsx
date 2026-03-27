import { useState } from 'react'

// 새 단어 추가 모달 컴포넌트
function AddWordModal({ onAdd, onClose, currentCount }) {
  const [word, setWord] = useState('')
  const [meaning, setMeaning] = useState('')

  const handleSubmit = (e) => {
    // 폼 기본 동작(페이지 새로고침) 방지
    e.preventDefault()

    if (!word.trim() || !meaning.trim()) {
      alert('단어와 뜻을 모두 입력해주세요.')
      return
    }

    // 부모 컴포넌트(App.jsx)의 추가 함수 호출
    onAdd(word.trim(), meaning.trim())
  }

  return (
    // 모달 외부 클릭 시 닫힘
    <div className="modal-overlay" onClick={onClose}>
      {/* 모달 내부 클릭 시 이벤트 전파 방지 (모달이 닫히지 않도록) */}
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <h2>새 단어 추가</h2>
          <button className="modal-close" onClick={onClose}>
            ✕
          </button>
        </div>
        <p className="modal-count">현재 {currentCount} / 10개 등록됨</p>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="word-input">단어</label>
            <input
              id="word-input"
              type="text"
              placeholder="예: apple, 사과, 단어..."
              value={word}
              onChange={(e) => setWord(e.target.value)}
              autoFocus
            />
          </div>

          <div className="form-group">
            <label htmlFor="meaning-input">뜻 / 내용</label>
            <textarea
              id="meaning-input"
              placeholder="예: 사과, I eat an apple every day."
              value={meaning}
              onChange={(e) => setMeaning(e.target.value)}
              rows={3}
            />
          </div>

          <div className="modal-buttons">
            <button type="button" className="btn-cancel" onClick={onClose}>
              취소
            </button>
            <button type="submit" className="btn-submit">
              추가하기
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default AddWordModal
