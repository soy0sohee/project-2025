"use client";

import Image from "next/image";
import { Movie } from "@/app/api/movies/route";

interface MovieCardProps {
  movie: Movie;
}

const RANK_STYLES: Record<number, string> = {
  1: "bg-yellow-400 text-black shadow-yellow-400/50",
  2: "bg-gray-300 text-black shadow-gray-300/50",
  3: "bg-amber-600 text-white shadow-amber-600/50",
};

export default function MovieCard({ movie }: MovieCardProps) {
  const rankStyle = RANK_STYLES[movie.rank] ?? "bg-[#0f3460] text-white shadow-blue-900/50";

  return (
    <div className="group relative flex flex-col bg-[#16213e] rounded-2xl overflow-hidden border border-[#0f3460] hover:border-red-700 transition-all duration-300 hover:shadow-[0_0_24px_rgba(230,0,18,0.2)] hover:-translate-y-1">
      {/* 포스터 */}
      <div className="relative w-full aspect-[2/3] bg-[#0d0d1a] overflow-hidden">
        {movie.posterUrl ? (
          <Image
            src={movie.posterUrl}
            alt={movie.title}
            fill
            sizes="(max-width: 640px) 50vw, (max-width: 1024px) 33vw, 20vw"
            className="object-cover transition-transform duration-500 group-hover:scale-105"
            unoptimized
          />
        ) : (
          <div className="w-full h-full flex items-center justify-center bg-[#503396]">
            <svg className="w-12 h-12 text-gray-600" fill="currentColor" viewBox="0 0 24 24">
              <path d="M18 3v2h-2V3H8v2H6V3H4v18h2v-2h2v2h8v-2h2v2h2V3h-2zM8 17H6v-2h2v2zm0-4H6v-2h2v2zm0-4H6V7h2v2zm10 8h-2v-2h2v2zm0-4h-2v-2h2v2zm0-4h-2V7h2v2z" />
            </svg>
          </div>
        )}

        {/* 순위 뱃지 */}
        <div
          className={`absolute top-2 left-2 w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold shadow-lg ${rankStyle}`}
        >
          {movie.rank}
        </div>

        {/* 관람평 점수 */}
        {movie.score && (
          <div className="absolute top-2 right-2 bg-black/70 backdrop-blur-sm rounded-full px-2 py-0.5 flex items-center gap-1">
            <svg className="w-3 h-3 text-yellow-400" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            <span className="text-xs text-white font-semibold">{movie.score}</span>
          </div>
        )}

        {/* 예매율 오버레이 */}
        {movie.bookingRate && (
          <div className="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/85 to-transparent px-3 py-2">
            <span className="text-[#ffffff] font-bold text-sm">{movie.bookingRate}</span>
          </div>
        )}

        {/* 호버 시 줄거리 오버레이 */}
        {movie.description && (
          <div className="absolute inset-0 bg-black/85 flex items-center justify-center p-3 opacity-0 group-hover:opacity-100 transition-opacity duration-300">
            <p className="text-xs text-gray-200 text-center leading-relaxed line-clamp-6">
              {movie.description}
            </p>
          </div>
        )}
      </div>

      {/* 정보 영역 */}
      <div className="p-3 flex flex-col gap-1.5 flex-1">
        {/* 제목 */}
        <h3 className="font-bold text-white text-sm leading-tight line-clamp-2 group-hover:text-[#ffffff] transition-colors">
          {movie.title}
        </h3>

        {/* 특별관 뱃지 */}
        {movie.screenTypes.length > 0 && (
          <div className="flex flex-wrap gap-1">
            {movie.screenTypes.map((type) => (
              <span
                key={type}
                className="text-[10px] px-1.5 py-0.5 rounded bg-[#0f3460] text-blue-300 font-medium"
              >
                {type}
              </span>
            ))}
          </div>
        )}

        {/* 개봉일 */}
        {movie.openDate && (
          <span className="text-xs text-gray-500">{movie.openDate}</span>
        )}

        {/* 좋아요 */}
        {movie.likes && (
          <span className="text-xs text-gray-500 flex items-center gap-1">
            <svg className="w-3 h-3 text-red-400" fill="currentColor" viewBox="0 0 20 20">
              <path fillRule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clipRule="evenodd" />
            </svg>
            {movie.likes}
          </span>
        )}

        {/* 예매하기 버튼 */}
        {movie.movieCode && (
          <a
            href={`https://www.megabox.co.kr/movie/detail?movieCode=${movie.movieCode}`}
            target="_blank"
            rel="noopener noreferrer"
            className="mt-auto text-center text-xs py-1.5 rounded-lg bg-[#329EB1] hover:bg-red-700 text-white font-semibold transition-colors"
          >
            자세히 보기
          </a>
        )}
      </div>
    </div>
  );
}
