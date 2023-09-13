package com.envify.back.dao;

import com.envify.back.entity.VersionStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionStatusDao extends JpaRepository<VersionStatusEntity, Long> {
}
