package com.davidr.web;

import com.davidr.common.model.Greeting;
import com.davidr.common.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/greet")
    public Greeting greet(@RequestParam(defaultValue = "World") String name) {
        return greetingService.getGreeting(name);
    }
}
