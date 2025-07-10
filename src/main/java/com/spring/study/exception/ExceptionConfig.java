package com.spring.study.exception;

import com.spring.study.exception.filter.ExceptionLogFilter;
import com.spring.study.exception.interceptor.ExceptionLogInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExceptionConfig implements WebMvcConfigurer {
//    @Bean
//    public FilterRegistrationBean logFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new ExceptionLogFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/*");
//        //클라이언트 요청과 오류 페이지 요청에 필터가 호출됨
//        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
//        return filterRegistrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExceptionLogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                //제외 경로
                .excludePathPatterns(
                    "/css/**", "/*.ico",
                    "/error", "/error-page/**" //오류 페이지 경로
            );
    }
}
