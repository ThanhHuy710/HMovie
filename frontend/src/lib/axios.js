import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api",
});

// Interceptor gắn token vào mọi Request
api.interceptors.request.use(
  (config) => {
    // 1. Lấy token trực tiếp từ localStorage (Thống nhất với AuthContext và AuthPage)
    const token = localStorage.getItem("token");

    // 2. Nếu có token, nhét nó vào Header
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

// Interceptor xử lý lỗi chung (Ví dụ: Token hết hạn bị Backend trả về 401)
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    // Nếu Backend báo lỗi 401 (Unauthorized) do token hết hạn
    if (error.response && error.response.status === 401) {
      console.warn("Token hết hạn hoặc không hợp lệ!");
      
      // Xóa token cũ rác đi
      localStorage.removeItem("token");
      localStorage.removeItem("refreshToken");
      
      //  user về trang login (Dùng window.location.href để ép tải lại toàn bộ state)
      if (window.location.pathname !== '/auth') {
         window.location.href = '/auth';
      }
    }
    return Promise.reject(error);
  }
);

export default api;