package com.envify.back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.envify.back.entity.PackageConfigFileEntity;

public interface PackageConfigFileDao extends JpaRepository<PackageConfigFileEntity, Long> {
	List<PackageConfigFileEntity> getByPackageVersionId(int packageVersionId);

}
