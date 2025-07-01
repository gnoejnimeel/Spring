package com.spring.study.mvc.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price; //가격이 없을 수도 있기때문에 Integer 사용
    private Integer quantity;

    //아무것도 없는 생성자
    public Item() {
    }

    //아이디 제외한 생성자
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
