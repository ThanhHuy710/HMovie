import { useState, useEffect } from "react";
import { Play, Info } from "lucide-react";
import { useNavigate } from "react-router-dom";
import api from "../lib/axios";

export default function Banner() {
  const navigate = useNavigate();
  const [currentSlide, setCurrentSlide] = useState(0);
  const [films, setFilms] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchFilms = async () => {
      try {
        const res = await api.get("/movies", { params: { limit: 5 } });
        const filmsData = res.data.result || [];
        setFilms(filmsData);
      } catch (error) {
        console.error("Error fetching films:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchFilms();
  }, []);

  // Tự động chuyển slide sau mỗi 5 giây
  useEffect(() => {
    if (films.length === 0) return;
    const timer = setInterval(() => {
      setCurrentSlide((prev) => (prev + 1) % films.length);
    }, 5000);
    return () => clearInterval(timer);
  }, [films]);

  if (loading) {
    return (
      <div className="w-full h-[85vh] bg-black flex items-center justify-center">
        <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-orange-500"></div>
      </div>
    );
  }

  if (films.length === 0) {
    return (
      <div className="w-full h-[85vh] bg-black flex items-center justify-center">
        <p className="text-white text-lg">Không có phim nào</p>
      </div>
    );
  }

  const currentMovie = films[currentSlide];

  const handleWatchClick = async (filmId) => {
    navigate(`/watch/${filmId}`);
  };

  return (
    <div className="relative w-full bg-black overflow-hidden">
      {/* Main Banner Section */}
      <div className="relative w-full h-[85vh]">
        {/* Background Image */}
        <div className="absolute inset-0">
          <img
            src={currentMovie.posterURL || "/images/Banner.png"}
            alt={currentMovie.title}
            className="w-full h-full object-cover transition-all duration-1000"
            key={currentSlide}
          />
          
          {/* Gradient Overlays - Strong left fade */}
          <div className="absolute inset-0 bg-linear-to-r from-black via-black/70 to-transparent"></div>
          <div className="absolute inset-0 bg-linear-to-b from-black/30 via-transparent to-black/80"></div>
        </div>

        {/* Content - Left Side */}
        <div className="relative h-full max-w-7xl mx-auto px-12 flex items-center">
          <div className="max-w-2xl space-y-6 z-10">
            {/* Metadata Badge */}
            <div className="flex items-center gap-4 animate-fadeIn">
              <span className="px-3 py-1 bg-red-600/90 backdrop-blur-sm text-white text-xs font-bold rounded uppercase tracking-widest shadow-lg">
                HOT
              </span>
              <span className="text-gray-300 text-sm font-medium">{currentMovie.year || 2025}</span>
              <div className="flex items-center gap-1.5">
                <span className="text-yellow-400 text-base drop-shadow-lg">★</span>
                <span className="text-white text-sm font-semibold">{currentMovie.averageRating || "N/A"}</span>
              </div>
            </div>

            {/* Title - Premium Typography */}
            <h1 className="text-5xl md:text-7xl font-black text-white leading-[1.1] animate-slideUp uppercase tracking-tight drop-shadow-2xl">
              {currentMovie.title}
            </h1>

            {/* Genre Tags - Elegant */}
            {currentMovie.movieGenres && currentMovie.movieGenres.length > 0 && (
              <div className="flex flex-wrap gap-2 animate-fadeIn" style={{ animationDelay: '0.1s' }}>
                {currentMovie.movieGenres.slice(0, 3).map((filmGenre) => (
                  <span
                    key={filmGenre?.genres?.id || filmGenre?.id}
                    className="px-4 py-1.5 bg-white/10 backdrop-blur-md text-orange-400 text-xs font-semibold rounded-full border border-orange-500/30 hover:bg-orange-500/20 hover:border-orange-500/60 transition-all duration-300 shadow-lg"
                  >
                    {filmGenre?.genres?.name || "Thể loại"}
                  </span>
                ))}
              </div>
            )}

            {/* Description - Clean & Readable */}
            <p className="text-gray-200 text-base leading-relaxed max-w-xl animate-fadeIn line-clamp-3 drop-shadow-md" style={{ animationDelay: '0.2s' }}>
              {currentMovie.description || "Khám phá câu chuyện đầy cảm xúc và hấp dẫn."}
            </p>

            {/* Action Buttons - Modern Design */}
            <div className="flex gap-4 pt-4 animate-fadeIn" style={{ animationDelay: '0.3s' }}>
              <button 
                onClick={() => handleWatchClick(currentMovie.movieId)}
                className="group flex items-center gap-3 px-8 py-3.5 bg-white text-black text-sm font-bold rounded-lg hover:bg-gray-100 transition-all duration-300 active:scale-95 shadow-2xl hover:shadow-white/30"
              >
                <Play className="w-5 h-5 fill-black group-hover:scale-110 transition-transform duration-300" />
                <span className="tracking-wide">XEM NGAY</span>
              </button>
              <button 
                onClick={() => navigate(`/film/${currentMovie.movieId}`)}
                className="group flex items-center gap-3 px-8 py-3.5 bg-white/10 backdrop-blur-md text-white text-sm font-semibold rounded-lg border border-white/30 hover:bg-white/20 hover:border-white/50 transition-all duration-300 active:scale-95 shadow-xl"
              >
                <Info className="w-5 h-5 group-hover:rotate-12 transition-transform duration-300" />
                <span className="tracking-wide">CHI TIẾT</span>
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* Animations */}
      <style>{`
        @keyframes fadeIn {
          from {
            opacity: 0;
            transform: translateY(10px);
          }
          to {
            opacity: 1;
            transform: translateY(0);
          }
        }

        @keyframes slideUp {
          from {
            opacity: 0;
            transform: translateY(40px);
          }
          to {
            opacity: 1;
            transform: translateY(0);
          }
        }

        .animate-fadeIn {
          animation: fadeIn 0.8s ease-out forwards;
          opacity: 0;
        }

        .animate-slideUp {
          animation: slideUp 1s ease-out forwards;
          opacity: 0;
        }
      `}</style>
    </div>
  );
}