package com.spring.study.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class BeanItem {
    private Long id;

    @NotBlank //빈값, 공백을 허용하지 않는다.
    private String itemName;

    @NotNull //null을 허용하지 않는다.
    @Range(min = 1000, max = 1000000) //범위 안의 값이어야 한다.
    private Integer price;

    @NotNull
    @Max(9999) //최대 값까지만 허용한다.
    private Integer quantity;

    public BeanItem() {
    }

    public BeanItem(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
