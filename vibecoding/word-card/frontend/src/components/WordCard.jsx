// 개별 단어 카드 컴포넌트
function WordCard({ word, onDelete }) {
  // 날짜를 'YYYY. MM. DD.' 형식으로 변환
  const formatDate = (dateString) => {
    const date = new Date(dateString)
    return date.toLocaleDateString('ko-KR', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
    })
  }

  return (
    <div className="word-card">
      <div className="word-card-header">
        <h3 className="word-title">{word.word}</h3>
        <button
          className="btn-delete"
          onClick={() => onDelete(word.id)}
          title="삭제"
        >
          ✕
        </button>
      </div>
      <p className="word-meaning">{word.meaning}</p>
      <span className="word-date">{formatDate(word.createdAt)}</span>
    </div>
  )
}

export default WordCard
