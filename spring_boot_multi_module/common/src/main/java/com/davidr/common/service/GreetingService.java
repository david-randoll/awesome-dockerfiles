package com.davidr.common.service;

import com.davidr.common.model.Greeting;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GreetingService {

    public Greeting getGreeting(String name) {
        String message = String.format("Hello, %s!", name);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new Greeting(message, timestamp);
    }
}
