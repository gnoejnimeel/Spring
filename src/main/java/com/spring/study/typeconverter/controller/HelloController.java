package com.spring.study.typeconverter.controller;

import com.spring.study.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        //요청 파라미터는 모두 문자로 처리된다.
        String data = request.getParameter("data");
        //자바에서 다른 타입으로 변환할 때 방법
        Integer intValue = Integer.valueOf(data);
        System.out.println(intValue);
        return "WOW";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam IpPort data) {
        //스프링이 중간에서 타입을 변환해줌
        System.out.println("IP = " + data.getIp()); //127.0.0.1
        System.out.println("IP = " + data.getPort()); //8080
        return "WOW";
    }

    @GetMapping("/hello-v3")
    public String helloV3(@ModelAttribute UserData data) {
        //이것도 스프링이 중간에서 타입을 변환해줌
        System.out.println(data);
        return "WOW";
    }

    @GetMapping("/hello-v4")
    public String helloV4(@PathVariable("userId") Integer data) {
        //URL 경로도 스프링이 변환해줌
        System.out.println(data);
        return "WOW";
    }
}

class UserData {
    Integer data;
}
