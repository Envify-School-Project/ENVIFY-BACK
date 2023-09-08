package com.envify.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.envify.back.entity.UserEntity;
import com.envify.back.service.UserService;

import com.envify.back.entity.UserEntity;
import com.envify.back.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<UserEntity>> findAllUsers() {
		List<UserEntity> users = userService.findAllUsers();
		
		return ResponseEntity.ok().body(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findUserById(@PathVariable int id) {
		UserEntity user = userService.getUserById(id);

		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
	 userService.deleteUserById(id);
	}
}
