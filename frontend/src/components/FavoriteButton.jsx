import { toast } from "sonner";
import api from "../lib/axios";
import { useEffect, useState } from "react";

export default function FavoriteButton({ filmId, userId }) {
  console.log("Film ID in favorite button:", filmId, "User ID:", userId);
  const [favorite, setFavorite] = useState(null);
  const isFavorite = !!favorite;

  useEffect(() => {
    const fetchFavorite = async () => {
      try {
        // Fetch all favorites and find the one matching filmId
        const res = await api.get(`/favorites/profileAndMovie/${userId}/${filmId}`);
        const favorites = res.data?.result || [];
        setFavorite(favorites ?? null);
        console.log("Favorites:", favorites);
      } catch (error) {
        console.error("Lỗi:", error);
      }
    };
    if (filmId && userId) fetchFavorite();
  }, [filmId, userId]);

  const handlerFavourite = async () => {
    try {
      if (!favorite) {
        const res = await api.post("/favorites", {
          movieId: filmId,
          profileId: userId,
        });
        setFavorite(res.data?.result ?? res.data);
        toast.success("Đã thêm phim vào danh sách yêu thích");
      } else {
        if (favorite?.favoriteId) {
          await api.delete(`/favorites/${favorite.favoriteId}`);
        } else {
          await api.delete("/favorites", {
            data: { film_id: filmId, user_id: userId },
          });
        }
        setFavorite(null);
        toast.success("Đã xóa phim khỏi danh sách yêu thích");
      }
    } catch (error) {
      console.error("Lỗi:", error);
      toast.error(!isFavorite ? "Không thể thêm" : "Không thể xóa");
    }
  };

  return (
    <button
      onClick={handlerFavourite}
      className="flex flex-col items-center cursor-pointer hover:text-blue-400"
    >
      <img
        src="../../public/images/AddToList.png"
        alt="Favorite"
        className="w-6 h-6"
      />
      <p className="text-sm">{isFavorite ? "Đã yêu thích" : "Yêu thích"}</p>
    </button>
  );
}