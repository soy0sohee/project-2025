import WordCard from './WordCard'

// 단어 카드 목록 컴포넌트
function WordList({ words, onDelete }) {
  // 단어가 없을 때 안내 메시지 표시
  if (words.length === 0) {
    return (
      <div className="empty-state">
        <p>등록된 단어가 없습니다. 🔍</p>
        <p>검색어를 바꾸거나 새 단어를 추가해보세요!</p>
      </div>
    )
  }

  return (
    <div className="word-list">
      {words.map((word) => (
        <WordCard key={word.id} word={word} onDelete={onDelete} />
      ))}
    </div>
  )
}

export default WordList
