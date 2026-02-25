import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "sonner";
import api from "../lib/axios";

export default function SeasonAndEpisodes({ film }) {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [seasons, setSeasons] = useState([]); // khởi tạo mảng
  const [selectedFilmId, setSelectedFilmId] = useState(film?.movieId ?? null);

  const fetchSeasons = async (filmObj) => {
    if (!filmObj?.movieId) return;
    setLoading(true);
    try {
      const res = await api.get(`/movies/season/${filmObj.movieId}`);
      const data = res?.data?.result ?? [];
      setSeasons(data);
      console.log("Phần phim:", data);
      setSelectedFilmId(data[0]?.movieId ?? filmObj.movieId);
    } catch (error) {
      console.error("Lỗi fetchSeasons:", error);
      toast.error("Không thể tải phần phim");
      setSeasons([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (!film) return;
    fetchSeasons(film);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [film?.movieId]);

  // handler chọn option (không dùng Link trong option)
  const handleChange = (e) => {
    const id = Number(e.target.value);
    setSelectedFilmId(id);
    // nếu muốn điều hướng tới trang xem ngay:
    navigate(`/film/${id}`);
  };

  // Hiển thị giao diện
  return (
    <div>
      <div className="mb-4">
        {loading ? (
          <div>Đang tải phần...</div>
        ) : seasons.length ? (
          <select
            className="px-3 py-2 bg-gray-800 rounded text-white"
            value={selectedFilmId ?? ""}
            onChange={handleChange}
          >
            {seasons.map((s) => (
              <option key={s.movieId} value={s.movieId}>
                {s.season !== 0 ? `Phần ${s.season}` : "Phim lẻ"}
              </option>
            ))}
          </select>
        ) : (
          <div>Không có phần khác</div>
        )}
        <div className="flex flex-wrap gap-5 mt-5">
          {film.episodes.map((ep) => (
            <Link
              to={`/watch/${film.movieId}?episode=${ep.episodeId}`}
              key={ep.episodeId}
              className="px-3 py-1 bg-gray-700 rounded-full text-sm"
            >
              {ep.episodeName}
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
}
