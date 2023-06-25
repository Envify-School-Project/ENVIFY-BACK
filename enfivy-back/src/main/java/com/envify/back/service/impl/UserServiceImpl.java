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
}
