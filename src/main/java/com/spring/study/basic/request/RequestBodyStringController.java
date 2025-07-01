package com.spring.study.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString (HttpServletRequest request, HttpServletResponse response) throws IOException {
        //HTTP 메시지 바디의 데이터를 InputStream을 사용해서 읽음
        ServletInputStream inputStream = request.getInputStream();
        //받은 데이터를 UFT-8로 인코딩해서 텍스트로 변환
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2 (InputStream inputStream, Writer responseWriter) throws IOException {
        //InputStream를 통해 HTTP 요청 메시지 바디의 내용을 직접 조회한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3 (HttpEntity<String> httpEntity) {
        //HttpEntity를 통해 HTP Header, body 정보를 편리하게 조회한다.
        String body = httpEntity.getBody();
        log.info("messageBody={}", body);
        //HttpEntity는 응답도 가능하다. 단, view 조회는 아님
        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4 (RequestEntity<String> httpEntity) {
        String body = httpEntity.getBody();
        log.info("messageBody={}", body);
        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
    }

    /**
     * 최종 진화단계
     */
    @ResponseBody //리턴 값을 응답 바디에 그대로 넣어서 보낸다.
    @PostMapping("/request-body-string-v5")
    public String requestBodyStringV5 (@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
