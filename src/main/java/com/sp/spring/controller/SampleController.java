package com.sp.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
//http://localhost:8080/api/hello
    @GetMapping("/hello")
    public String test() {
        return "hello";
    }

    @GetMapping("/welcome")
    public String getPage() {
        return "welcome is the Remo!!";
    }

}
