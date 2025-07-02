package com.spring.study.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for(String code : messageCodes) {
            System.out.println(code);
            //required.item
            //required
        }
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void meddsageCodeResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        //bindingResult.rejectValue("itemName", "required"); -> codesResolver 호출함
        for(String code : messageCodes) {
            System.out.println(code);
            //순서대로 찾아오는 것이다.
            //required.item.itemName -> 가장 디테일한 것 먼저 나옴
            //required.itemName 객체명 생략
            //required.java.lang.String 타입
            //required 제일 넓은 범위
        }
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
}
