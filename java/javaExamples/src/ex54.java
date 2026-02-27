import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ex54 {
    public static void main(String[] args) {
        // 위치 : GPS좌표(위도, 경도)
        //      : 행정주소(OO시 OO구 OO로 OO번지)
        //      : 구글 행정주소 API(행정주소 <-> GPS좌표)
        
        // Date클래스 : 날짜 정보를 전달하기 위해 사용
        Date date = new Date();
        System.out.println(date);

        // 밀리초
        long millis = date.getTime();
        System.out.println(millis);

        // 날짜 시간 포맷
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        System.out.println(sdf.format(date));

        // Calendar클래스 : 다양한 시간대별로 날짜와 시간을 얻을 떄 사용 / +- 2억9천만년 범위 / 윤년 계산
        Calendar cal = new GregorianCalendar(Locale.KOREA);

        // Date 객체 가져오기
        System.out.println(cal.getTime());
        System.out.println(cal.getTimeInMillis());

        // 년도.달.일.요일
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH)); // 1월 = 0, 12월 = 11
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));

        PrintTime(cal);
    }
    static void PrintTime (Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(cal.getTime());

        System.out.println(str);
    }
}
