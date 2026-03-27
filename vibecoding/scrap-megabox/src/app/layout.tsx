import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "메가박스 박스오피스 TOP 10",
  description: "메가박스 박스오피스 순위 1위~10위 실시간 스크래핑",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="ko">
      <body>{children}</body>
    </html>
  );
}
