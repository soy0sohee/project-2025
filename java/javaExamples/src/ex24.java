// 싱글톤(Singleton)
// : 프로그램 안에서 유일한 클래스 객체
// : new를 통해서 클래스 객체를 여러번 생성하는 구조이기 때문에
// : 같은 클래스의 객체가 여러개 있다면, 데이터의 유지보수가 어려워짐

// : 스프링 프레임워크에서는 싱글톤을 자동으로 유지/관리
// : Bean(Spring안에서의 객체)이 죽으면 부활시켜주고, 중복안되게 관리해줌

// 유일한 객체(싱글톤)이 필요한 이유
// : 유일한 정보를 저장/관리하기 위해

class FishBun { // 붕어빵
    int bunNo = 10;
}

// 싱글톤 생성
// 프로그램 내에서 유일한 붕어빵
class FishBunSingleton {
    int bunNo = 30;
    // private : 같은 클래스 안에서 접근 가능(캡슐화, 은닉화)
    //         : 다른 클래스에서는 getter/setter메서드(함수)를 통해서만 접근 가능
    //         : 변수나 메서드(함수)를 최대한 노출하지 않는 것(값 관리 - 접근제어)
    private static FishBunSingleton singleton; // 초기값 : null
    // getInstance메서드(함수)를 통해서 싱글톤 객체를 얻어옴
    static FishBunSingleton getInstance() {
       if (singleton == null) {
           singleton = new FishBunSingleton();
       }
       return singleton;
    }

}

public class ex24 {
    public static void main(String[] args) {
        FishBunSingleton sBun1 = FishBunSingleton.getInstance();
        FishBunSingleton sBun2 = FishBunSingleton.getInstance();
        System.out.println(sBun1); // FishBunSingleton@23fc625e : @23fc625e 주소값 동일
        System.out.println(sBun2); // FishBunSingleton@23fc625e : @23fc625e 주소값 동일
        sBun1.bunNo = 40;
        System.out.println(sBun1.bunNo); // 40
        System.out.println(sBun2.bunNo); // 40

        // 주소값이 동일하므로 일관된 데이터를 가짐
        // 공통으로 하나만 써야 하는 객체
        // 로그인,회원정보 : 계정이름, 프로필이미지, 마이페이지, QnA, FAQ, Main 등 공통영역

        FishBun bun1 = new FishBun();
        FishBun bun2 = new FishBun();
        System.out.println(bun1); // FishBun@23fc625e : @23fc625e 주소값
        System.out.println(bun2); // FishBun@3f99bd52 : @3f99bd52 주소값
        bun1.bunNo = 20;
        System.out.println(bun1.bunNo); // 20
        System.out.println(bun2.bunNo); // 10

        // 일반 객체는 일관된 데이터를 저장하기 어려움
        // non-static(dynamic) 객체는 Heap영역에 저장되고,
        // GC(Garbage Collector)가 자동으로 메모리를 회수함
    }
}
