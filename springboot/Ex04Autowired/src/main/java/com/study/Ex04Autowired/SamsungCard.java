package com.study.Ex04Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("samsungCard")
public class SamsungCard implements ICard {
    @Override
    public void buy(String itemName) {
        System.out.println("SamsungCard.buy");
        System.out.println("itemName = " + itemName);
    }
    @Override
    public String toString() {
        return "삼성카드";
    }
}
