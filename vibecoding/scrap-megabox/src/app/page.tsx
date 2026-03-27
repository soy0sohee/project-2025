"use client";

import { useEffect, useState, useCallback } from "react";
import MovieCard from "@/components/MovieCard";
import SkeletonCard from "@/components/SkeletonCard";
import type { Movie } from "@/app/api/movies/route";

interface ApiResponse {
  success: boolean;
  scrapedAt?: string;
  total?: number;
  movies: Movie[];
  error?: string;
}

export default function Home() {
  const [data, setData] = useState<ApiResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchMovies = useCallback(async () => {
    setLoading(true);
    setError(null);
    setData(null);
    try {
      const res = await fetch("/api/movies");
      const json: ApiResponse = await res.json();
      if (!json.success) throw new Error(json.error || "스크래핑 실패");
      setData(json);
    } catch (e) {
      setError(e instanceof Error ? e.message : "알 수 없는 오류");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchMovies();
  }, [fetchMovies]);

  const scrapedTime = data?.scrapedAt
    ? new Date(data.scrapedAt).toLocaleString("ko-KR", { timeZone: "Asia/Seoul" })
    : null;

  return (
    <main className="min-h-screen bg-[#0d0d1a] px-4 py-10">
      {/* 헤더 */}
      <div className="max-w-6xl mx-auto mb-10">
        <div className="flex flex-wrap items-center justify-between gap-4">
          <div>
            <div className="flex items-center gap-3 mb-1">
              <span className="text-3xl">🎬</span>
              <h1 className="text-3xl font-extrabold text-white tracking-tight">
                메가박스{" "}
                <span className="text-[#59BEC9]">박스오피스</span>{" "}
                <span className="text-gray-300">TOP 10</span>
              </h1>
            </div>
            <p className="text-gray-500 text-sm ml-12">
              메가박스 영화 페이지 실시간 스크래핑 &nbsp;·&nbsp;{" "}
              {scrapedTime ? (
                <span className="text-gray-400">🕐 {scrapedTime} 기준</span>
              ) : (
                <span>로딩 중...</span>
              )}
            </p>
          </div>

          <button
            onClick={fetchMovies}
            disabled={loading}
            className="flex items-center gap-2 px-5 py-2.5 rounded-full border border-[#0f3460] hover:border-[#e60012] text-gray-300 hover:text-white text-sm transition-all disabled:opacity-40 disabled:cursor-not-allowed"
          >
            <svg
              className={`w-4 h-4 ${loading ? "animate-spin" : ""}`}
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"
              />
            </svg>
            {loading ? "스크래핑 중..." : "새로고침"}
          </button>
        </div>
      </div>

      {/* 영화 그리드 */}
      <div className="max-w-6xl mx-auto">
        {error ? (
          <div className="flex flex-col items-center justify-center py-24 gap-4">
            <div className="text-5xl">⚠️</div>
            <p className="text-red-400 text-lg font-semibold">스크래핑 오류</p>
            <p className="text-gray-400 text-sm text-center max-w-md">{error}</p>
            <button
              onClick={fetchMovies}
              className="mt-2 px-6 py-2 rounded-full bg-[#e60012] text-white text-sm hover:bg-red-700 transition-colors"
            >
              다시 시도
            </button>
          </div>
        ) : (
          <>
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
              {loading
                ? Array.from({ length: 10 }).map((_, i) => <SkeletonCard key={i} />)
                : data?.movies.map((movie) => (
                    <MovieCard key={movie.rank} movie={movie} />
                  ))}
            </div>

            {/* 로딩 중 안내 */}
            {loading && (
              <p className="text-center text-sm text-gray-500 mt-6 animate-pulse">
                Puppeteer로 메가박스 페이지를 렌더링하는 중입니다. 약 10~20초 소요됩니다...
              </p>
            )}
          </>
        )}

        {/* 출처 */}
        {!loading && !error && data && (
          <p className="text-center text-xs text-gray-700 mt-10">
            데이터 출처:{" "}
            <a
              href="https://www.megabox.co.kr/movie"
              target="_blank"
              rel="noopener noreferrer"
              className="text-gray-500 hover:text-[#e60012] underline transition-colors"
            >
              www.megabox.co.kr/movie
            </a>
          </p>
        )}
      </div>
    </main>
  );
}
