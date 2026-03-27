import { createContext, useContext, useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("JWT_TOKEN"); //앱 시작 할 때 토큰 확인
    if (token) {
      const decoded = jwtDecode(token);
      setIsLoggedIn(true);
      setUser(decoded.sub);
    }
  }, []);

  return (
    <>
      <AuthContext.Provider
        value={{ isLoggedIn, user, setIsLoggedIn, setUser }}
      >
        {children}
      </AuthContext.Provider>
    </>
  );
};

//어디서든 접근 가능 훅
export const useAuth = () => useContext(AuthContext);
