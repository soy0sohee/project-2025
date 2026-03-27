import type { Config } from "tailwindcss";

const config: Config = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        megabox: {
          red: "#e60012",
          dark: "#1a1a2e",
          card: "#16213e",
          border: "#0f3460",
        },
      },
    },
  },
  plugins: [],
};

export default config;
