package com.envify.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envify.back.entity.UserEntity;

/**
 * @author semfa
 * @author pierrebrivio
 *
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	UserEntity getById(int id);
	void deleteById(int id);
	UserEntity findByEmail(String email);
}
