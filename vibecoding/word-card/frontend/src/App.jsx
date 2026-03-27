import { useState, useEffect } from 'react'
import SearchBar from './components/SearchBar'
import WordList from './components/WordList'
import AddWordModal from './components/AddWordModal'

function App() {
  // 단어 목록 (검색/정렬 결과)
  const [words, setWords] = useState([])
  // 전체 단어 개수 (최대 10개 제한에 사용)
  const [totalCount, setTotalCount] = useState(0)
  // 검색어
  const [search, setSearch] = useState('')
  // 정렬 방식: 'latest'(최신순) 또는 'alphabet'(알파벳순)
  const [sort, setSort] = useState('latest')
  // 새 단어 추가 모달 표시 여부
  const [showModal, setShowModal] = useState(false)
  // 로딩 상태
  const [loading, setLoading] = useState(false)
  // 데이터 새로 불러오기 트리거 (숫자가 바뀔 때마다 useEffect가 다시 실행됨)
  const [refreshKey, setRefreshKey] = useState(0)

  // 데이터 새로 불러오기 함수
  const refresh = () => setRefreshKey((prev) => prev + 1)

  // search, sort, refreshKey가 바뀔 때마다 데이터를 새로 불러옴
  useEffect(() => {
    const loadData = async () => {
      setLoading(true)
      try {
        // 검색어와 정렬 조건을 URL 파라미터로 추가
        const params = new URLSearchParams()
        if (search) params.append('search', search)
        params.append('sort', sort)

        // 단어 목록과 전체 개수를 동시에 요청
        const [wordsRes, countRes] = await Promise.all([
          fetch(`/api/words?${params}`),
          fetch('/api/words/count'),
        ])

        const wordsData = await wordsRes.json()
        const countData = await countRes.json()

        setWords(wordsData)
        setTotalCount(Number(countData.count))
      } catch (error) {
        console.error('데이터 불러오기 실패:', error)
      } finally {
        setLoading(false)
      }
    }

    loadData()
  }, [search, sort, refreshKey])

  // 새 단어 추가
  const handleAddWord = async (word, meaning) => {
    try {
      const response = await fetch('/api/words', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ word, meaning }),
      })

      if (!response.ok) {
        const error = await response.json()
        alert(error.message)
        return
      }

      setShowModal(false)
      refresh()
    } catch (error) {
      console.error('단어 추가 실패:', error)
    }
  }

  // 단어 삭제
  const handleDeleteWord = async (id) => {
    if (!confirm('이 단어를 삭제하시겠습니까?')) return

    try {
      await fetch(`/api/words/${id}`, { method: 'DELETE' })
      refresh()
    } catch (error) {
      console.error('단어 삭제 실패:', error)
    }
  }

  return (
    <div className="app">
      <header className="app-header">
        <h1>📚 나만의 단어장</h1>
        <span className="word-count-badge">{totalCount} / 10</span>
      </header>

      <main className="app-main">
        <div className="controls">
          <SearchBar
            search={search}
            onSearchChange={setSearch}
            sort={sort}
            onSortChange={setSort}
          />
          <button
            className="btn-add"
            onClick={() => setShowModal(true)}
            disabled={totalCount >= 10}
            title={totalCount >= 10 ? '단어는 최대 10개까지 추가할 수 있습니다' : '새 단어 추가'}
          >
            + 새 단어 추가
          </button>
        </div>

        {loading ? (
          <div className="loading">불러오는 중...</div>
        ) : (
          <WordList words={words} onDelete={handleDeleteWord} />
        )}
      </main>

      {showModal && (
        <AddWordModal
          onAdd={handleAddWord}
          onClose={() => setShowModal(false)}
          currentCount={totalCount}
        />
      )}
    </div>
  )
}

export default App
