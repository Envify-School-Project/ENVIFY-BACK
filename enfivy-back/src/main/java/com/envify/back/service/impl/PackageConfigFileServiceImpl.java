package com.envify.back.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envify.back.dao.PackageConfigFileDao;
import com.envify.back.entity.PackageConfigFileEntity;
import com.envify.back.service.PackageConfigFileService;

@Service
@Transactional
public class PackageConfigFileServiceImpl implements PackageConfigFileService{
	
	@Autowired
	private PackageConfigFileDao packageConfigFileDao;

	@Override
	public List<PackageConfigFileEntity> findByPackageVersionId(int packageVersionId) {
		
		return packageConfigFileDao.getByPackageVersionId(packageVersionId);
	}
}
