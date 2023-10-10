package com.envify.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.envify.back.entity.PackageConfigFileEntity;

@Service
public interface PackageConfigFileService {
	List<PackageConfigFileEntity> findByPackageVersionId(int packageVersionId);

}
