package io.troof.bigpi.Exo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam("username") String username) {
        return "Troof : say hello " + username;
    }
    //utiliser @GetMapping("/hello") et @RequestParam("username") --> localhost:8080/hello?username=Frederic
    //utiliser @GetMapping("/hello/{username}") et @PathVariable --> localhost:8080/hello/Frederic
}