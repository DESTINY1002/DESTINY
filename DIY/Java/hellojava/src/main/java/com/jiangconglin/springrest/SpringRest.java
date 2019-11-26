package com.jiangconglin.springrest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRest {
    @RequestMapping("/gretting")
    public String greet(){
        return "Hello, guy!";
    }
}
