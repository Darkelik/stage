package io.troof.bigpi.HelloUserOpenAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
public class ControllerHello {

    @Operation(summary = "Say hello to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping("/hello")
    public String sayHello(@Parameter(description = "Username of the user to be greeted") @RequestParam("username") String username) {
        return "Troof : say hello " + username;
    }
}
