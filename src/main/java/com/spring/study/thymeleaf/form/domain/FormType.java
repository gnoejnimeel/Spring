package com.spring.study.thymeleaf.form.domain;

public enum FormType {
    BOOK("도서"),
    FOOD("음식"),
    ETC("기타");

    private final String description;

    FormType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
