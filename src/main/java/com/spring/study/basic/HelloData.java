package com.spring.study.basic;

import lombok.Data;

@Data
public class HelloData {
    //외부에서 데이터를 접근하지 못 하도록 private 사용함 -> 캡슐화
    private String username;
    private int age;
}
