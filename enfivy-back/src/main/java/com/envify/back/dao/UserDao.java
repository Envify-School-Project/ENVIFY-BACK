package com.envify.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envify.back.entity.UserEntity;

/**
 * @author semfa
 *
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	
}
