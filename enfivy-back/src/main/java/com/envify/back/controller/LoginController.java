package com.envify.back.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.dao.UserDao;
import com.envify.back.dto.AuthRequest;
import com.envify.back.dto.AuthResponse;
import com.envify.back.dto.RequestResponse;
import com.envify.back.dto.UserDto;
import com.envify.back.entity.UserEntity;
import com.envify.back.security.JWTUtil;
import com.envify.back.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://envify.netlify.app")
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
			authResponse.setUserId(user.get(0).getId());
			
			//TODO set profil in auth response for authentication
//			authResponse.setProfil(user.get(0).getProfile);

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
	public @ResponseBody ResponseEntity<RequestResponse> createUser(@RequestBody UserDto user) {
		final UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getEmail());
		
		final RequestResponse requestResponse = new RequestResponse();

		if (userService.isUserExist(userEntity)) {
			return ResponseEntity.badRequest().body(new RequestResponse("L'utilisateur existe deja dans la bdd", 400));
		}

		mapUserDtoToUserEntity(user, userEntity);

		try {
			UserEntity userCreated = userDao.save(userEntity);
			requestResponse.setMessage("Utilisateur crée avec succès");
			requestResponse.setCode(200);
			requestResponse.setId(userCreated.getId());
		} catch (Exception e) {
			LOGGER.error("Bad request exception");
			return ResponseEntity.badRequest().body(new RequestResponse("Bad request exeption", 400));
		}
		
		return ResponseEntity.ok().body(requestResponse);
	}

	private void mapUserDtoToUserEntity(UserDto user, final UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setCompany(user.getCompany());
		userEntity.setUsername(user.getUsername());
	}
}
