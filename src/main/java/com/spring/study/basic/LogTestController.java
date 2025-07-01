package com.spring.study.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //HTTP 메세지 바디에 바로 입력됨
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";
        log.info(name);

        //로그에 레벨 설정
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //개발서버는 디버그 출력
        log.info(" info log={}", name); //운영 서버는 인포 출력
        log.warn(" warn log={}", name); //경고
        log.error("error log={}", name); //에러
        return "OK";
    }
}
