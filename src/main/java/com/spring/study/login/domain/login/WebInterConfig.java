package com.spring.study.login.domain.login;

import com.spring.study.login.web.interceptor.LogInterceptor;
import com.spring.study.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebInterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //인터셉터 등록
        registry.addInterceptor(new LogInterceptor())
                //순서 지정
                .order(1)
                //인터셉터를 적용할 URL
                .addPathPatterns("/**")
                //인터셉터에서 제외할 패턴 지정
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error"
                );
    }
}

