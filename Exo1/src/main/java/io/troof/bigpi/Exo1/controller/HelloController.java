package io.troof.bigpi.Exo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/{username}")
    public String sayHello(@PathVariable String username) {
        return "Troof : say hello " + username;
    }
}
