import { useEffect, useState } from "react";
import api from "../lib/axios";
import { toast } from "sonner";
import Layout from "../components/layout/Layout";
import Banner from "../components/Banner";
import BannerAfterLogin from "../components/BannerAfterLogin";
import MovieRow from "../components/MovieRow";
import RequireBirtdayModal from "../components/RequireBirtdayModal";
import { useAuth } from "../contexts/AuthContext";

export default function HomePage() {
  const { user, updateUser } = useAuth();
  const [hotFilms, setHotFilms] = useState([]);
  const [ratingFilms, setRatingFilms] = useState([]);
  const [recommendedFilms, setRecommendedFilms] = useState([]);
  const [favoriteFilms, setFavoriteFilms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showdobModal, setShowdobModal] = useState(false);

  useEffect(() => {
    fetchAllFilms();
  }, []);

  useEffect(() => {
    // Kiểm tra nếu user đã đăng nhập và chưa có ngày sinh
    if (user && !user.dob) {
      setShowdobModal(true);
    } else {
      setShowdobModal(false);
    }
  }, [user]);

  const handledobUpdate = (updatedUser) => {
    updateUser(updatedUser);
    setShowdobModal(false);
  };

  const fetchAllFilms = async () => {
    try {
      const [hotRes, ratingRes, recommendedRes, favoriteRes] = await Promise.all([
        api.get("/movies/top-hot", { skipAuth: true }),
        api.get("/movies/top-rating", { skipAuth: true }),
        api.get("/movies", { skipAuth: true }),
        api.get("/movies/favorite", { skipAuth: true }),
      ]);

      setHotFilms(hotRes.data?.result || []);
      setRatingFilms(ratingRes.data?.result || []);
      setRecommendedFilms(recommendedRes.data?.result || []);
      setFavoriteFilms(favoriteRes.data?.result || []);
    } catch (error) {
      console.error("Lỗi:", error);
      toast.error("Không thể tải phim");
    } finally {
      setLoading(false);
    }
  };
  console.log("Hot films:", hotFilms);
  console.log("Rating films:", ratingFilms);
  console.log("Recommended films:", recommendedFilms);
  console.log("Favorite films:", favoriteFilms);
  if (loading) {
    return (
      <Layout>
        <div className="loading-page">Đang tải...</div>
      </Layout>
    );
  }

  return (
    <Layout>
      {showdobModal && (
        <RequireBirtdayModal user={user} onUpdate={handledobUpdate} />
      )}

      {user ? <BannerAfterLogin /> : <Banner />}

      <div className="movie-sections">
        <MovieRow 
          title="Phim Hot" 
          films={hotFilms}
          viewAllLink="top-hot"
        />

        <MovieRow 
          title="Đánh giá cao" 
          films={ratingFilms}
          viewAllLink="top-rating"
        />

        <MovieRow 
          title="Dành cho bạn" 
          films={recommendedFilms}
          viewAllLink="favorite"
        />

        <MovieRow 
          title="Top yêu thích" 
          films={favoriteFilms}
          viewAllLink="favorite"
        />
      </div>
    </Layout>
  );
}
