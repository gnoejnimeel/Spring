package com.spring.study.exception.exhandler;

import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //@Controller + @ResponseBody -> 리턴값이 그대로 JSON 응답
public class ApiExceptionV2Controller {
    @ResponseStatus(HttpStatus.BAD_REQUEST) //HTTP 상태 코드는 400 Bad Request
//    @ExceptionHandler(IllegalArgumentException.class) //해당 에러가 발생하면 이 메소드 호출됨
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage()); //JSON으로 자동 변환
    }

//    @ExceptionHandler //ExecutionControl.UserException 예외가 발생하면 호출됨 -> 해당 메소드 파라미터 예외 사용
    public ResponseEntity<ErrorResult> userExHandle(ExecutionControl.UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        //HTTP 메시지 바디에 직접 응답
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        //id 값에 따라 의도적으로 예외를 던짐
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
