package com.spring.study.login.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;


@Data
public class LoginItemSaveForm {
    private String itemName;
    @Range(min = 1000, max = 1000000)
    private Integer price;
    private Integer quantity;

}
