package com.madao.xss.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VueLoginController {

    @GetMapping("/home/**")
    public String login() {
        return "index";
    }


}
