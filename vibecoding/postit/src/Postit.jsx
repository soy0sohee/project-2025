import { useRef } from 'react'
import styles from './Postit.module.css'

// 선택할 수 있는 색상 목록
const COLORS = ['#fef08a', '#bbf7d0', '#bfdbfe', '#fecaca', '#e9d5ff', '#fed7aa']

// 색상을 조금 어둡게 만드는 함수 (헤더 배경색에 사용)
function darken(hex) {
  const num = parseInt(hex.slice(1), 16)
  const r = Math.max(0, (num >> 16) - 25)
  const g = Math.max(0, ((num >> 8) & 0xff) - 25)
  const b = Math.max(0, (num & 0xff) - 25)
  return `#${((r << 16) | (g << 8) | b).toString(16).padStart(6, '0')}`
}

// Props 설명:
//   note   - 이 메모의 데이터 (id, text, x, y, color)
//   onDelete - 삭제 버튼을 눌렀을 때 실행할 함수
//   onChange - 메모 내용이 바뀔 때 실행할 함수
function Postit({ note, onDelete, onChange }) {
  // 드래그 시작 시 마우스와 메모 사이의 거리를 기억하기 위해 useRef 사용
  // useRef는 값이 바뀌어도 화면이 다시 그려지지 않아 드래그에 적합합니다
  const dragOffset = useRef({ x: 0, y: 0 })

  // 헤더에서 마우스를 누를 때 드래그 시작
  function handleMouseDown(e) {
    // 삭제 버튼 위에서 누른 경우 드래그 하지 않음
    if (e.target.closest(`.${styles.deleteBtn}`)) return

    // 마우스 위치에서 메모 위치를 빼면 → 메모 내에서의 클릭 오프셋
    dragOffset.current = {
      x: e.clientX - note.x,
      y: e.clientY - note.y,
    }

    // 마우스가 움직일 때마다 메모 위치를 업데이트
    function onMouseMove(e) {
      onChange({
        x: e.clientX - dragOffset.current.x,
        y: e.clientY - dragOffset.current.y,
      })
    }

    // 마우스를 떼면 이벤트 리스너를 제거해서 드래그 종료
    function onMouseUp() {
      document.removeEventListener('mousemove', onMouseMove)
      document.removeEventListener('mouseup', onMouseUp)
    }

    document.addEventListener('mousemove', onMouseMove)
    document.addEventListener('mouseup', onMouseUp)
  }

  return (
    <div
      className={styles.card}
      style={{
        left: note.x,
        top: note.y,
        backgroundColor: note.color,
      }}
    >
      {/* 드래그 핸들 역할을 하는 헤더 */}
      <div
        className={styles.header}
        style={{ backgroundColor: darken(note.color) }}
        onMouseDown={handleMouseDown}
      >
        {/* 색상 선택 버튼들 */}
        <div className={styles.colorPicker}>
          {COLORS.map((color) => (
            <button
              key={color}
              className={`${styles.colorDot} ${note.color === color ? styles.colorDotActive : ''}`}
              style={{ backgroundColor: color }}
              onClick={() => onChange({ color })}
            />
          ))}
        </div>

        {/* 삭제 버튼 */}
        <button className={styles.deleteBtn} onClick={onDelete}>
          &times;
        </button>
      </div>

      {/* 텍스트 입력 영역 */}
      <textarea
        className={styles.textarea}
        style={{ backgroundColor: note.color }}
        value={note.text}
        placeholder="메모를 입력하세요..."
        onChange={(e) => onChange({ text: e.target.value })}
      />
    </div>
  )
}

export default Postit
