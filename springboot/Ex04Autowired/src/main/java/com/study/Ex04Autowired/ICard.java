package com.study.Ex04Autowired;
// 신용(체크)카드 인터페이스
// 사용이유 : 확장성, 설계와 구현 관점에서 업데이트 용이
public interface ICard {
    public void buy(String itemName); // 추상화 메서드
}
