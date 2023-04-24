package io.troof.bigpi.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring boot, ceci est un hello world";
	}
}
