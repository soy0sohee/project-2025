import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "megabox.co.kr",
      },
      {
        protocol: "https",
        hostname: "www.megabox.co.kr",
      },
      {
        protocol: "https",
        hostname: "img.megabox.co.kr",
      },
    ],
  },
  serverExternalPackages: ["puppeteer"],
};

export default nextConfig;
