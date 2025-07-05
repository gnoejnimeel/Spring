package com.spring.study.login.domain.item;

import lombok.Data;

@Data
public class LoginItem {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public LoginItem() {
    }

    public LoginItem(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
