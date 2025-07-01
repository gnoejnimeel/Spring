package com.spring.study.thymeleaf.form.domain;

import lombok.Data;

import java.util.List;

@Data
public class Form {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private Boolean open; //판매 여부
    private List<String> regions; //등록 지역
    private FormType itemType; //상품 종류
    private String deliveryCode; //배송 방식

    public Form() {
    }

    public Form(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
