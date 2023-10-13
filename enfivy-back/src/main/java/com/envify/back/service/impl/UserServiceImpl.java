package com.envify.back.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envify.back.dao.UserDao;
import com.envify.back.entity.UserEntity;
import com.envify.back.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserEntity> findAllUsers() {
		return userDao.findAll();
	}

	@Override
	public UserEntity getUserById(int id) { 
		return userDao.getById(id); 
	}

	@Override
	public void deleteUserById(int id) { 
		userDao.deleteById(id); 
	}
	
	public boolean isUserExist(UserEntity user) {
		return userDao.findByEmail(user.getEmail()) != null;
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserEntity findByRole(String role) {
		return userDao.findByRole(role);
	}

	@Override
	public void saveUser(UserEntity user) {
		userDao.save(user);
	}
}
