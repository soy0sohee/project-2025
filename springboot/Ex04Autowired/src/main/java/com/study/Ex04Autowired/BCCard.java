package com.study.Ex04Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("bCCard")
public class BCCard implements ICard {
    @Override
    public void buy(String itemName) {
        System.out.println("BCCard.buy");
        System.out.println("itemName = " + itemName);
    }
    @Override
    public String toString() {
        return "BC카드";
    }
}
