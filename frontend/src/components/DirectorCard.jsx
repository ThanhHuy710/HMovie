import { Link } from "react-router-dom";

export default function DirectorCard({ director }) {
  return (
    <Link
      onClick={() => window.location.href = `/search/director/?name=${director}`}
      className="shrink-0 w-1/4 sm:w-1/4 md:w-1/6 lg:w-1/8 cursor-pointer"
    >
      <div className="aspect-2/3">
        <img
          src="../../public/images/DefaultAvata.png"
          alt={director}
          className="w-full h-full object-cover rounded-lg"
        />
      </div>
      <p className="mt-2 text-xs sm:text-sm md:text-base lg:text-lg text-white font-semibold truncate">
        {director}
      </p>
    </Link>
  );
} 