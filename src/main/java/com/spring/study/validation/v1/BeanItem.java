package com.spring.study.validation.v1;

import com.spring.study.validation.item.SaveCehck;
import com.spring.study.validation.item.UpdateCheck;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

@Data
public class BeanItem {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
}
