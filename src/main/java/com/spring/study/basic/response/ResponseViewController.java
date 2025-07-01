package com.spring.study.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        return new ModelAndView("response/hello").addObject("data", "hello!");
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        //view
        return "response/hello";
    }

    //경로랑 url를 같도록 작성하면 스프링이 알아서 view 연결은 해주지만 비추천!
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) { //실행templates/response/hello.html
        model.addAttribute("data", "hello!!");
    }
}
