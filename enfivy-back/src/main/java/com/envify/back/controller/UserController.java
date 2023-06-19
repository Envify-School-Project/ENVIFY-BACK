package com.envify.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@GetMapping("/helloword")
	public ResponseEntity<String> helloWord() {
		return ResponseEntity.ok("Hello Word");
	}
	
}
