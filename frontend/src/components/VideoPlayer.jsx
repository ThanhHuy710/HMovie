import { useEffect, useRef } from "react";
import Hls from "hls.js";
import api from "../lib/axios";

export default function VideoPlayer({ videoUrl, filmId, userId }) {
  const videoRef = useRef(null);
  
  const isViewPosted = useRef(false);

  useEffect(() => {
    const video = videoRef.current;
    if (!video || !videoUrl) return;

    let hls;

    // Kiểm tra trình duyệt có hỗ trợ Hls.js không (Chrome, Edge, Android...)
    if (Hls.isSupported()) {
      hls = new Hls();
      hls.loadSource(videoUrl);
      hls.attachMedia(video);
    } 
    else if (video.canPlayType("application/vnd.apple.mpegurl")) {
      video.src = videoUrl;
    }

    // Cleanup khi chuyển phim khác hoặc tắt trang
    return () => {
      if (hls) {
        hls.destroy();
      }
    };
  }, [videoUrl]);

  const handleTimeUpdate = async () => {
    const video = videoRef.current;
    if (!video) return;

    if (video.currentTime >= 10 && !isViewPosted.current) {
      
      isViewPosted.current = true; 
      try {
        console.log("Đã xem 10s! Ghi nhận view cho phim:", filmId);
        await api.post(`/views`, { 
          movieId: filmId, 
          profileId: userId, 
          progress: 10 
        });
      } catch (err) {
        console.error("Lỗi cập nhật view:", err);
        // Nếu lỗi mạng, có thể mở khóa lại để cho phép thử lại sau
        // isViewPosted.current = false; 
      }
    }
  };

  return (
    <video
      ref={videoRef}
      autoPlay={false}
      controls={true}
      width="100%"
      height="auto"
      onTimeUpdate={handleTimeUpdate}
    />
  );
}