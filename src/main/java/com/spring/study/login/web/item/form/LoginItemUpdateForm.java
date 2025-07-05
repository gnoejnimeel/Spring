package com.spring.study.login.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;



@Data
public class LoginItemUpdateForm {
    private Long id;
    private String itemName;
    @Range(min = 1000, max = 1000000)
    private Integer price;
    //수정에서는 수량은 자유롭게 변경할 수 있다.
    private Integer quantity;
}
