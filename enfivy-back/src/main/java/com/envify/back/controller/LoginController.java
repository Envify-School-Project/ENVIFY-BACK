package com.envify.back.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.dao.UserDao;
import com.envify.back.dto.AuthRequest;
import com.envify.back.dto.AuthResponse;
import com.envify.back.dto.UserDto;
import com.envify.back.entity.UserEntity;
import com.envify.back.security.JWTUtil;
import com.envify.back.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private final AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	public LoginController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

		try {
			final List<UserEntity> user = Arrays.asList(userDao.findByEmail(authRequest.getEmail().toLowerCase()))
					.stream().filter(u -> passwordEncoder.matches(authRequest.getPassword(), u.getPassword())).toList();

			if (user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

			final String token = jwtUtil.generateToken(user.get(0), "");
			final AuthResponse authResponse = new AuthResponse();
			authResponse.setToken(token);
			authResponse.setEmail(authentication.getName());

			return ResponseEntity.ok(authResponse);
		} catch (BadCredentialsException e) {
			LOGGER.error(e.toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/authout")
	public void authout(ServerHttpResponse response, Authentication authentication) {
		LOGGER.info("Start logout");

		if (authentication != null && authentication.getName() != null) {
			LOGGER.info("Authout {}", authentication.getName());
			authentication.setAuthenticated(false);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(Principal principal, @RequestBody UserDto user) {
		final UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getEmail());

		if (userService.isUserExist(userEntity)) {
			return ResponseEntity.badRequest().body("L'utilisateur existe deja dans la bdd");
		}

		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

		try {
			userService.saveUser(userEntity);
		} catch (Exception e) {
			LOGGER.error("Bad request exception");
			return ResponseEntity.badRequest().body("Bad request exeption");
		}

		return ResponseEntity.ok().body("Utilisateur crée avec succès");
	}
}
