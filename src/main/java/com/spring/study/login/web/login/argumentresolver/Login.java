package com.spring.study.login.web.login.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //파라미터에만 사용
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
}
