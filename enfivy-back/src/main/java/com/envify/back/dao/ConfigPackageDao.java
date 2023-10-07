package com.envify.back.dao;

import com.envify.back.entity.ConfigPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigPackageDao extends JpaRepository<ConfigPackageEntity, Long> {
}
