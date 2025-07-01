package com.spring.study.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    public String Headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {
            log.info("request={}", request);
            log.info("response={}", response);

            //HTTP 메서드 조회
            log.info("httpMethod={}", httpMethod); //GET
            log.info("locale={}", locale); //ko_KR
            log.info("headerMap={}", headerMap);
            log.info("header host={}", host); //localhost:8080
            log.info("myCookie={}", cookie); //null

            //MultiValueMap
            //MAP과 유사한데 하나의 키에 여러 값을 받을 수 있다.
        return "WOW";
    }
}
