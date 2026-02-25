import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api",
});

// Thêm interceptor để tự động gắn token vào header của mọi request
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    // Chỉ thêm token nếu có token VÀ request không có cờ skipAuth
    if (token && !config.skipAuth) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Thêm interceptor xử lý response lỗi (401)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      // Token hết hạn hoặc không hợp lệ -> Xóa token để tránh lỗi lặp lại ở các request sau
      localStorage.removeItem("token");
      localStorage.removeItem("refreshToken");
    }
    return Promise.reject(error);
  }
);

export default api;