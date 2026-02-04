//연습문제 - 싱글톤 만들기
//클래스 이름 : TossAccount
//  필드(private) : 계좌번호(1234), 고객이름(홍길동), 잔액(1000), 이자율(년3%)
//  메소드 : 입금(+100), 출금(-100), 이자계산(1년후 잔액), 잔액조회
class TossAccount {
    // 필드 생성
    private String account = "1234"; // 계산 안하니까 String
    private String name = "홍길동";
    private double balance = 1000; // 실수값 고려 double
    private double interestRate = 0.03;

    private static TossAccount singleton;

    public static TossAccount getInstance() {
        if (singleton == null) {
            singleton = new TossAccount();
        }
        return singleton;
    }

    // Getter/Setter 메서드 자동화 생성
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // 메서드 생성
    public void deposit(long money){
        this.balance += money;
    }

    public void withdrawal(long money){
        this.balance -= money;
    }

    public void rate() {
        this.balance = this.balance * (1 + this.interestRate);
    }
}

public class ex25 {
    public static void main(String[] args) {
        //싱글톤을 호출후
        TossAccount account = TossAccount.getInstance();

        //1. 입금 메소드 호출
        account.deposit(100);

        //2. 출금 메소드 호출
        account.withdrawal(100);

        //3. 이자계산은 이자율을 읽어서 참조한다.
        //   이자계산후 잔액 증가한다.
        account.rate();

        //4. 최종 잔액을 출력한다.
        System.out.println(account.getBalance());

    }
}