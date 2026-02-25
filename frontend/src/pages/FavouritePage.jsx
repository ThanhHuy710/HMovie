
import { useEffect, useState } from "react";
import Layout from "../components/layout/Layout";
import MovieCard from "../components/MovieCard";
import api from "../lib/axios";
import { toast } from "sonner";
import { useAuth } from "../contexts/AuthContext";

export default function FavouritePage() {
  const { user } = useAuth();
  const userId = user?.profileId;
  const [films, setFilms] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (userId) {
      fetchFilmsByUser();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [userId]);
  console.log("User ID:", userId);
  const fetchFilmsByUser = async () => {
    try {
      const res = await api.get(`movies/favorites/${userId}`);
      setFilms(res.data?.result || []);
      console.log("Phim yêu thích:", res.data?.result || []);
    } catch (error) {
      console.error("Lỗi:", error);
      toast.error("Không thể tải danh sách yêu thích");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Layout>
      <div className="py-4 mx-auto lg:max-w-6xl md:max-w-4xl">
        <p className="text-white text-4xl mb-5 font-bold">Danh sách yêu thích</p>
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
            <p className="no-results text-white">Không có phim yêu thích nào</p>
          )}
        </div>
      </div>
    </Layout>
  );
}