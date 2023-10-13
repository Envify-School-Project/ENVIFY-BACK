package com.envify.back.dao;

import com.envify.back.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigDao extends JpaRepository<ConfigEntity, Long> {
    ConfigEntity getById(int id);
    void deleteById(int id);
    List<ConfigEntity> findConfigsByUserId(int userId);
}
