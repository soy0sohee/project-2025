import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './reset.css'
import App from './App'

// index.html의 <div id="app"> 안에 App 컴포넌트를 렌더링합니다
createRoot(document.getElementById('app')).render(
  <StrictMode>
    <App />
  </StrictMode>
)
