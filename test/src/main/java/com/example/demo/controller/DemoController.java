package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 */
@Controller
public class DemoController {
    @ResponseBody
    @RequestMapping("/test")
    public Object test() {
        System.out.println("11111");
        return "Hello World!";
    }

    @ResponseBody
    @GetMapping("/test2")
    public Object test2() {
        System.out.println("11111");
        return "Hello World!";
    }
}
