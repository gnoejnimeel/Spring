package com.spring.study.basic.request;

import com.spring.study.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //http://localhost:8080/request-param-v1?username=wow&age=100
        //http://localhost:8080/basic/hello-form.html 여기서 작성해서도 보내도 들어옴
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("wow");
    }

    @ResponseBody //view 조회를 무시하고 HTTP message body에 직접 해당 내용 입력됨
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {
        //http://localhost:8080/request-param-v2?username=wow&age=100
        log.info("username={}, age={}", username, age);
        return "wow";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        //요청 파라미터를 다 받고 싶을 때 Map 사용
        //파라미터의 값이 1개로 확실하면 Map 사용, 그렇지 않다면 MultiValueMap 사용
        //http://localhost:8080/request-param-map?username=wow&age=100
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        //http://localhost:8080/model-attribute-v1?username=wow&age=100
        //HelloData 객체를 생성하고 요청 파라미터의 값이 모두 들어간다.
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
