import { NextResponse } from "next/server";
import puppeteer from "puppeteer";

export interface Movie {
  rank: number;
  title: string;
  bookingRate: string;
  openDate: string;
  posterUrl: string;
  movieCode: string;
  score: string;
  likes: string;
  description: string;
  screenTypes: string[];
}

export async function GET() {
  let browser = null;

  try {
    browser = await puppeteer.launch({
      headless: true,
      args: [
        "--no-sandbox",
        "--disable-setuid-sandbox",
        "--disable-dev-shm-usage",
        "--disable-gpu",
      ],
    });

    const page = await browser.newPage();

    await page.setUserAgent(
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    );

    await page.setViewport({ width: 1280, height: 800 });

    await page.goto("https://www.megabox.co.kr/movie", {
      waitUntil: "networkidle2",
      timeout: 30000,
    });

    // 영화 목록 렌더링 대기
    await page.waitForSelector(".movie-list-info", { timeout: 15000 });
    await new Promise((resolve) => setTimeout(resolve, 2000));

    const movies: Movie[] = await page.evaluate(() => {
      const results: Movie[] = [];

      // 각 영화 항목: div.movie-list > ol.list > li
      const items = document.querySelectorAll(".movie-list .list li");

      items.forEach((item, index) => {
        if (index >= 10) return;

        // 순위: .rank 텍스트에서 숫자만 추출
        const rankEl = item.querySelector(".movie-list-info .rank");
        const rankText = rankEl?.childNodes[0]?.textContent?.trim() || String(index + 1);
        const rank = parseInt(rankText) || index + 1;

        // 제목: .tit-area .tit
        const titleEl = item.querySelector(".tit-area .tit");
        const title = titleEl?.getAttribute("title") || titleEl?.textContent?.trim() || "";

        // 포스터: img.poster
        const posterEl = item.querySelector(".movie-list-info img.poster");
        const posterUrl = posterEl?.getAttribute("src") || "";

        // 예매율: .rate-date .rate
        const rateEl = item.querySelector(".rate-date .rate");
        const bookingRate = rateEl?.textContent?.trim() || "";

        // 개봉일: .rate-date .date
        const dateEl = item.querySelector(".rate-date .date");
        const openDate = dateEl?.textContent?.trim() || "";

        // 영화 코드: a.movieBtn data-no
        const movieBtnEl = item.querySelector("a.movieBtn");
        const movieCode = movieBtnEl?.getAttribute("data-no") || "";

        // 관람평 점수: .my-score .preview .number
        const scoreEl = item.querySelector(".my-score .preview .number");
        const scoreText = scoreEl?.childNodes[0]?.textContent?.trim() || "";
        const score = scoreText;

        // 좋아요 수: .btn-like span
        const likesEl = item.querySelector(".btn-like span");
        const likes = likesEl?.textContent?.trim() || "";

        // 줄거리 요약: .summary (첫 2문장 정도만)
        const descEl = item.querySelector(".summary");
        const fullDesc = descEl?.textContent?.trim() || "";
        const description = fullDesc.split("\n").slice(0, 3).join(" ").trim().substring(0, 120);

        // 특별관 타입: MX4D, Dolby, Atmos
        const screenTypes: string[] = [];
        const mx4d = item.querySelector(".screen-type2 #mx4d");
        const dolby = item.querySelector(".screen-type2 #dolby");
        const atmos = item.querySelector(".screen-type2 #atmos");
        if (mx4d && (mx4d as HTMLElement).style.display !== "none") screenTypes.push("MX4D");
        if (dolby && (dolby as HTMLElement).style.display !== "none") screenTypes.push("Dolby Cinema");
        if (atmos && (atmos as HTMLElement).style.display !== "none") screenTypes.push("Dolby Atmos");

        if (title) {
          results.push({
            rank,
            title,
            bookingRate,
            openDate,
            posterUrl,
            movieCode,
            score,
            likes,
            description,
            screenTypes,
          });
        }
      });

      return results;
    });

    await browser.close();
    browser = null;

    if (movies.length === 0) {
      return NextResponse.json(
        {
          success: false,
          error: "영화 데이터를 찾을 수 없습니다.",
          movies: [],
        },
        { status: 404 }
      );
    }

    return NextResponse.json({
      success: true,
      scrapedAt: new Date().toISOString(),
      total: movies.length,
      movies: movies.slice(0, 10),
    });
  } catch (error) {
    if (browser) {
      await browser.close();
    }

    console.error("스크래핑 오류:", error);

    return NextResponse.json(
      {
        success: false,
        error: error instanceof Error ? error.message : "스크래핑 중 오류가 발생했습니다.",
        movies: [],
      },
      { status: 500 }
    );
  }
}
