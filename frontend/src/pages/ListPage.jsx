import { useState, useEffect } from "react";
import { toast } from "sonner";
import api from "../lib/axios";
import Layout from "../components/layout/Layout";
import MovieCard from "../components/MovieCard";
import { useParams, useSearchParams } from "react-router"; 
import ResultNameForListPage from "../components/ResultNameForListPage";
import AvataCard from "../components/AvataCard";
import DirectorCard from "../components/DirectorCard";
import AdvancedSearch from "../components/AdvancedSearch";

export default function ListPage() {
  const { type } = useParams();
  const [searchParams] = useSearchParams();
  const name = searchParams.get("name");

  const [film, setFilm] = useState([]);
  const [loading, setLoading] = useState(true);
  const [actors, setActors] = useState([]);
  const [directors, setDirectors] = useState([]);
  const [viewMode, setViewMode] = useState("movies");
  const [activeAvSearch, setActiveAvSearch] = useState(false);
  console.log("Type:", type, "Name:", name);
  const fetchFilm = async () => {
    try {
      setLoading(true);
      if (type === "titleoractor" && name) {
        const [resTitle, resActor, resDirector] = await Promise.all([
          api.get(`/movies/title/${name}`),
          api.get(`/movies/actors/${name}`),
          api.get(`/movies/directors/${name}`),
        ]);
        const fetchedMovies = resTitle.data?.result || [];
        const fetchedActors = resActor.data?.result || [];
        const fetchedDirectors = resDirector.data?.result || [];

        setFilm(fetchedMovies);
        setActors(fetchedActors);
        setDirectors(fetchedDirectors);

        // Auto set viewMode
        if (fetchedMovies.length > 0) setViewMode("movies");
        else if (fetchedActors.length > 0) setViewMode("actors");
        else if (fetchedDirectors.length > 0) setViewMode("directors");

      } else if (type === "criteria") {
        
        const params = Object.fromEntries([...searchParams]);
        const res = await api.get("/movies/criteria", { params });
        setFilm(res.data?.result || []);
      } else if (name) {
        const res = await api.get(`/movies/${type}/${name}`);
        setFilm(res.data?.result || []);
        console.log("Fetched films:", res.data?.result);

      } else {
        const res = await api.get(`/movies/${type}`);
        setFilm(res.data?.result || []);
      }
    } catch (error) {
      console.error("Lỗi:", error);
      toast.error("Không thể tải phim");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchFilm();
  }, [type, name, searchParams]);

  return (
    <Layout>
      <div className="py-4 mx-auto lg:max-w-6xl md:max-w-4xl">
        <h2 className="text-2xl sm:text-3xl font-bold text-white mb-6 sm:mb-8">
          <ResultNameForListPage type={type} name={name} />
        </h2>
        
        <button
          onClick={() => setActiveAvSearch(!activeAvSearch)}
          className="p-2 mb-5 bg-gray-700 rounded hover:bg-gray-600"
        >
          <img src="/images/funnel.png" alt="Filter" className="w-6 h-6 object-contain" />
        </button>

        {activeAvSearch && <AdvancedSearch AvSearch={() => setActiveAvSearch(false)} />}

        {/* Nút chọn chế độ hiển thị */}
        {type === "titleoractor" && (
          <div className="flex gap-4 mb-6">
            {['movies', 'actors', 'directors'].map((mode, idx) => (
              <button
                key={idx}
                onClick={() => setViewMode(mode)}
                className={`px-4 py-2 rounded capitalize ${
                  viewMode === mode ? "bg-blue-600 text-white" : "bg-gray-700 text-gray-200"
                }`}
              >
                {mode === 'movies' ? 'Phim' : mode === 'actors' ? 'Diễn viên' : 'Đạo diễn'}
              </button>
            ))}
          </div>
        )}

        {/* Trạng thái Loading chung cho cả 3 View */}
        {loading ? (
          <div className="text-white text-xl animate-pulse">Đang tìm kiếm...</div>
        ) : (
          <>
            {/* View Phim */}
            {viewMode === "movies" && (
              <div className="grid grid-cols-3 sm:grid-cols-4 lg:grid-cols-6 gap-4 sm:gap-6">
                {film.length > 0 ? (
                  film.map((f, index) => (               
                    <div key={f.movieId || index}>
                      <MovieCard film={f} />
                    </div>
                  ))
                ) : (
                  <p className="no-results text-white text-nowrap col-span-full">Không tìm thấy phim nào</p>
                )}
              </div>
            )}

            {/* View Diễn viên */
            console.log("Actors:", actors)}
            
            {viewMode === "actors" && (
              <div className="grid grid-cols-3 sm:grid-cols-4 lg:grid-cols-6 gap-4 sm:gap-6">
                {actors.length > 0 ? (
                  actors.map((actor, index) => (
                    <div key={actor || index}>
                      <AvataCard actor={actor} />
                    </div>
                  ))
                ) : (
                  <p className="no-results text-white text-nowrap">Không tìm thấy diễn viên nào</p>
                )}
              </div>
            )}

            {/* View Đạo diễn */}
            {viewMode === "directors" && (
              <div className="grid grid-cols-3 sm:grid-cols-4 lg:grid-cols-6 gap-4 sm:gap-6">
                {directors.length > 0 ? (
                  directors.map((director, index) => (
                    <div key={director || index}>
                      <DirectorCard director={director} />
                    </div>
                  ))
                ) : (
                  <p className="no-results text-white text-nowrap">Không tìm thấy đạo diễn nào</p>
                )}
              </div>
            )}
          </>
        )}
      </div>
    </Layout>
  );
}