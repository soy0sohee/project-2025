import java.util.*;

public class ex58 {
    public static void main(String[] args) {
        //Map가 HashMap의 부모클래스이므로 업캐스팅이 된다.
        List<Integer> list = new ArrayList<>();

        // 맵(Map) : 키와 값으로 구성된 데이터 구조
        //         : Key(문자열타입) - Value(객체,기본데이터타입(8))
        //         : JSON, XML 데이터 연결이 쉽다.
        //         : -> 클래스도 JSON/XML과 오브젝트 매핑이 된다.
        //         :  데이터 바인딩(binding) 또는 직렬화(Serialization)
        //         : 자바 객체 -> JSON/XML문자열 (직렬화)
        //         : JSON/XML문자열 -> 자바 객체 (역직렬화)

        //Map가 HashMap의 부모클래스이므로 업캐스팅이 된다.
        HashMap<String, String> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();

        map.put("username", "hong");
        map.put("password", "1234");
        System.out.println(map);

        // Key로 값을 얻어옴
        System.out.println(map.get("username"));

        // 전체 순회
        // 일반 for문은 index가 없으므로 불가
        // 향상된 for문으로 가능
        Set<String> keys = map.keySet();
        System.out.println(keys);
        for ( String key : keys) {
            System.out.println(map.get(key));
        }
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(map.get(key));
        }
    }
}
