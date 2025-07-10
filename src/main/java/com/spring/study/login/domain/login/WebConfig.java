package com.spring.study.login.domain.login;

import com.spring.study.login.web.filter.LogFilter;
import com.spring.study.login.web.filter.LoginCheckFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class WebConfig {
    /**
     * 필터 등록
     *
     * @ServletComponentScan, @WebFilter로도 등록 가능하지만 순서 조절이 안됨
     */
//    public FilterRegistrationBean logFilter() {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        //등록할 필터 지정
//        filterFilterRegistrationBean.setFilter(new LogFilter());
//        //필터는 체인으로 동작하여 순서를 설정한다. 낮은 숫자일수록 먼저 동작한다.
//        filterFilterRegistrationBean.setOrder(1);
//        //필터를 적용할 URL 패턴 지정한다.
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        return filterFilterRegistrationBean;
//    }

    /**
     * 로그인 세션 유무 확인
     */
//    public FilterRegistrationBean loginCheckFilter() {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        //로그인 체크 필터 등록
//        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
//        //순서는 두번째
//        filterFilterRegistrationBean.setOrder(2);
//        //모든 요청에 필터 적용
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        return filterFilterRegistrationBean;
//    }
}
