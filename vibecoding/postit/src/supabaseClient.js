import { createClient } from '@supabase/supabase-js'

// .env 파일에서 Supabase 접속 정보를 읽어옵니다
// Vite에서는 환경변수 앞에 VITE_ 를 붙여야 접근할 수 있습니다
const supabaseUrl = import.meta.env.VITE_SUPABASE_URL
const supabaseKey = import.meta.env.VITE_SUPABASE_ANON_KEY

// Supabase 클라이언트 생성 후 내보내기
// 다른 파일에서 import { supabase } from './supabaseClient' 로 가져다 씁니다
export const supabase = createClient(supabaseUrl, supabaseKey)
