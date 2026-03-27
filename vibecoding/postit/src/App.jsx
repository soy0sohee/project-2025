import { useState, useEffect, useRef } from 'react'
import { supabase } from './supabaseClient'
import Postit from './Postit'
import styles from './App.module.css'

const COLORS = ['#fef08a', '#bbf7d0', '#bfdbfe', '#fecaca', '#e9d5ff', '#fed7aa']

function getRandomColor() {
  return COLORS[Math.floor(Math.random() * COLORS.length)]
}

function App() {
  // 메모 목록 (Supabase에서 불러온 데이터)
  const [notes, setNotes] = useState([])

  // 데이터를 불러오는 중인지 여부
  const [loading, setLoading] = useState(true)

  // 저장 딜레이 타이머를 메모 ID별로 관리 (빠른 변경 시 DB 요청을 줄이기 위해)
  const saveTimers = useRef({})

  // ① 앱이 처음 열릴 때 Supabase에서 메모 목록을 불러옵니다
  useEffect(() => {
    async function fetchNotes() {
      const { data, error } = await supabase
        .from('postits')
        .select('*')
        .order('created_at', { ascending: true })

      if (error) {
        console.error('메모 불러오기 실패:', error.message)
      } else {
        setNotes(data)
      }
      setLoading(false)
    }
    fetchNotes()
  }, []) // [] : 컴포넌트가 처음 마운트될 때만 실행

  // ② 새 메모 추가: Supabase에 INSERT 후 반환된 데이터를 상태에 추가
  async function addNote(x, y) {
    const newNote = {
      text: '',
      x: x ?? Math.floor(Math.random() * (window.innerWidth - 280)) + 60,
      y: y ?? Math.floor(Math.random() * (window.innerHeight - 300)) + 100,
      color: getRandomColor(),
    }

    // .insert() → DB에 저장
    // .select().single() → 저장된 행(id, created_at 포함)을 반환받음
    const { data, error } = await supabase
      .from('postits')
      .insert(newNote)
      .select()
      .single()

    if (error) {
      console.error('메모 추가 실패:', error.message)
      return
    }

    setNotes((prev) => [...prev, data])
  }

  // ③ 메모 삭제: Supabase에서 DELETE 후 상태에서도 제거
  async function deleteNote(id) {
    const { error } = await supabase
      .from('postits')
      .delete()
      .eq('id', id) // id가 일치하는 행만 삭제

    if (error) {
      console.error('메모 삭제 실패:', error.message)
      return
    }

    setNotes((prev) => prev.filter((note) => note.id !== id))
  }

  // ④ 메모 수정: 화면은 즉시 업데이트, DB는 500ms 후 저장 (debounce)
  //   - 텍스트 입력 중이나 드래그 중에 DB 요청이 너무 많이 발생하는 것을 방지합니다
  function updateNote(id, changes) {
    // 화면 즉시 업데이트 (사용자가 바로 반응을 볼 수 있도록)
    setNotes((prev) =>
      prev.map((note) => (note.id === id ? { ...note, ...changes } : note))
    )

    // 이전에 예약된 저장 타이머가 있으면 취소
    clearTimeout(saveTimers.current[id])

    // 500ms 후에 DB 저장 (그 사이에 또 바뀌면 타이머가 다시 시작됨)
    saveTimers.current[id] = setTimeout(async () => {
      const { error } = await supabase
        .from('postits')
        .update(changes)
        .eq('id', id)

      if (error) {
        console.error('메모 수정 실패:', error.message)
      }
    }, 500)
  }

  // ⑤ 전체 삭제
  async function clearAll() {
    if (notes.length === 0) return
    if (!confirm('모든 메모를 삭제할까요?')) return

    const ids = notes.map((note) => note.id)

    const { error } = await supabase
      .from('postits')
      .delete()
      .in('id', ids) // id 목록에 포함된 행 모두 삭제

    if (error) {
      console.error('전체 삭제 실패:', error.message)
      return
    }

    setNotes([])
  }

  // 보드 빈 공간을 더블클릭하면 그 위치에 메모 추가
  function handleBoardDoubleClick(e) {
    if (e.target !== e.currentTarget) return
    addNote(e.clientX - 110, e.clientY - 20)
  }

  // 데이터를 불러오는 중일 때 로딩 화면 표시
  if (loading) {
    return (
      <div className={styles.app}>
        <div className={styles.loadingWrap}>
          <div className={styles.spinner} />
          <p className={styles.loadingText}>메모를 불러오는 중...</p>
        </div>
      </div>
    )
  }

  return (
    <div className={styles.app}>
      {/* 상단 툴바 */}
      <div className={styles.toolbar}>
        <span className={styles.title}>📝 포스트잇</span>
        <button className={styles.addBtn} onClick={() => addNote()}>
          + 새 메모
        </button>
        <button className={styles.clearBtn} onClick={clearAll}>
          전체 삭제
        </button>
      </div>

      {/* 메모가 놓이는 보드 영역 */}
      <div className={styles.board} onDoubleClick={handleBoardDoubleClick}>
        {notes.map((note) => (
          <Postit
            key={note.id}
            note={note}
            onDelete={() => deleteNote(note.id)}
            onChange={(changes) => updateNote(note.id, changes)}
          />
        ))}
      </div>

      {/* 메모가 하나도 없을 때 안내 문구 */}
      {notes.length === 0 && (
        <p className={styles.emptyText}>
          + 새 메모 버튼을 클릭하거나 보드를 더블클릭해서 메모를 추가하세요
        </p>
      )}

      <p className={styles.hint}>헤더를 드래그해서 메모를 이동하세요</p>
    </div>
  )
}

export default App
