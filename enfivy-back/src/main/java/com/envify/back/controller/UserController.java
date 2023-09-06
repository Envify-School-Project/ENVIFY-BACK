package com.envify.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.entity.UserEntity;
import com.envify.back.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> helloWord() {
		List<UserEntity> users = userService.findAllUsers();
		
		return ResponseEntity.ok().body(users);
	}
	
}
