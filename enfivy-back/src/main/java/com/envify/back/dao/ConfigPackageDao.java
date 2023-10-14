package com.envify.back.dao;

import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.entity.ConfigPackageIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigPackageDao extends JpaRepository<ConfigPackageEntity, Long> {}
