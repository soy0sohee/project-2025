// 검색창 + 정렬 셀렉트 박스 컴포넌트
function SearchBar({ search, onSearchChange, sort, onSortChange }) {
  return (
    <div className="search-bar">
      <input
        type="text"
        className="search-input"
        placeholder="단어 또는 뜻으로 검색..."
        value={search}
        onChange={(e) => onSearchChange(e.target.value)}
      />
      <select
        className="sort-select"
        value={sort}
        onChange={(e) => onSortChange(e.target.value)}
      >
        <option value="latest">최신순</option>
        <option value="alphabet">알파벳순</option>
      </select>
    </div>
  )
}

export default SearchBar
