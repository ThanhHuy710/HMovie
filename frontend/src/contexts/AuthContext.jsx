import { createContext, useContext, useState, useEffect } from "react";
import api from "../lib/axios";
import { jwtDecode } from "jwt-decode";
const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [isAdmin, setIsAdmin] = useState(false);
  // Kiểm tra đăng nhập khi tải lại trang (F5)
  useEffect(() => {
    const initAuth = async () => {
      const token = localStorage.getItem("token");
      if (token) {
        setIsAdmin(() => checkAdminStatus(token)); // Cập nhật trạng thái admin
        try {
          // Gọi API lấy thông tin user từ Spring Boot bằng token đang có
          // Endpoint này cần khớp với backend
          const res = await api.get("/profile/my-profile"); 
          setUser(res.data.result || res.data);
        } catch (error) {
          console.error("Token hết hạn hoặc không hợp lệ:", error);
          localStorage.removeItem("token");
          localStorage.removeItem("refreshToken");
          setUser(null);
          setIsAdmin(false);
        }
      }
      setLoading(false);
    };

    initAuth();
  }, []);

  const login = (userData, token) => {
    // Nếu có truyền token thì lưu lại (trường hợp login mới)
    if (token) {
      localStorage.setItem("token", token);
      setIsAdmin(() => checkAdminStatus(token)); // Cập nhật trạng thái admin sau khi login
    }
    setUser(userData);
  };

  const updateUser = (userData) => {
    // Cập nhật state user (ví dụ sau khi đổi avatar/tên)
    setUser((prev) => ({ ...prev, ...userData }));
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken");
    setUser(null);
    setIsAdmin(false);
  };
  const checkAdminStatus = (token) => {
    if (!token) return false;
    try {
      const decoded = jwtDecode(token);
      const roles = decoded?.realm_access?.roles || []; 
      return roles.includes("ADMIN");
    } catch (error) {
      console.error("Lỗi khi giải mã token:", error);
      return false;
    }
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, updateUser, loading , isAdmin}}>
      {children}
    </AuthContext.Provider>
  );
}

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return context;
};
