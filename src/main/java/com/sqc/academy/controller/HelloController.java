package com.sqc.academy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/greeting")
    public String hello(@RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "Việt Nam") String address) { // param
        return String.format("Hello %s - %s", name, address);
    }
}
