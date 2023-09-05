package com.envify.back.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.envify.back.entity.UserEntity;

@Service
public interface UserService {
	List<UserEntity> findAllUsers();
	UserEntity getUserById(int id);
	void deleteUserById(int id);
}
