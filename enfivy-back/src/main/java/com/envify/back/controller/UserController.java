package com.envify.back.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.entity.UserEntity;
import com.envify.back.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public PasswordEncoder passwordEncoder;

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
