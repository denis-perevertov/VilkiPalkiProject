package com.example.vilkipalki2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class VilkipalkiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VilkipalkiApplication.class, args);
	}

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "TEST";
	}
}
