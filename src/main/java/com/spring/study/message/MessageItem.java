package com.spring.study.message;

import lombok.Data;

@Data
public class MessageItem {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public MessageItem() {
    }

    public MessageItem(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
