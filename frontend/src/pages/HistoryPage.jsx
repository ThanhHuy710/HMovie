import { useEffect, useState } from "react";
import Layout from "../components/layout/Layout";
import MovieCard from "../components/MovieCard";
import api from "../lib/axios";
import { toast } from "sonner";
import { useAuth } from "../contexts/AuthContext";

export default function HistoryPage() {
  const { user } = useAuth();
  const userId = user?.profileId;
  const [films, setFilms] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (userId) {
      fetchFilmsByUser();
    }
  }, [userId]);
    
  const fetchFilmsByUser = async () => {
    try {
      console.log("Fetching films for userId:", userId);
      const res = await api.get(`/movies/histories/${userId}`);
      setFilms(res.data?.result || []);
    } catch (error) {
      console.error("Lỗi:", error);
      toast.error("Không thể tải danh sách lịch sử xem");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Layout>
      <div className="py-4 mx-auto lg:max-w-6xl md:max-w-4xl">
        <p className="text-white text-4xl mb-5 font-bold">Lịch sử xem</p>
        <div className="grid grid-cols-3 sm:grid-cols-4 lg:grid-cols-6 gap-4 sm:gap-6">
          {loading ? (
            <div className="loading">Đang tải...</div>
          ) : films.length > 0 ? (
            films.map((film) => (
              <div key={film.movieId}>
                <MovieCard film={film} />
              </div>
            ))
          ) : (
            <p className="no-results text-white">Chưa có lịch sử xem nào</p>
          )}
        </div>
      </div>
    </Layout>
  );
}