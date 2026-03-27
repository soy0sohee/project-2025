import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./hooks/AuthContext.jsx";
import Main from "./page/Main.jsx";
import Login from "./page/Login.jsx";
import Signup from "./page/Signup.jsx";
import Post from "./page/Post.jsx";
import PostDetail from "./page/PostDetail.jsx";

function App() {
  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path="/" element={<Main />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/post" element={<Post />} />
            <Route path="/postDetail/:id" element={<PostDetail />} />
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  );
}

export default App;
