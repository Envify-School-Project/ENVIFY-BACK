package com.envify.back.service.impl;

import com.envify.back.dao.PackageDao;
import com.envify.back.dto.PackageDto;
import com.envify.back.entity.PackageEntity;
import com.envify.back.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageDao packageDao;

    @Override
    public List<PackageEntity> findAllPackages() {
        return packageDao.findAll();
    }

    @Override
    public void savePackage(PackageEntity packageEntity) {
        packageDao.save(packageEntity);
    }

    @Override
    public PackageEntity findPackageById(int id) {
        return packageDao.getById(id);
    }

    @Override
    public PackageEntity updatePackage(PackageEntity packageEntity) {
        return packageDao.save(packageEntity);
    }

    @Override
    public void deletePackageById(int id) {
        packageDao.deleteById(id);
    }
}
