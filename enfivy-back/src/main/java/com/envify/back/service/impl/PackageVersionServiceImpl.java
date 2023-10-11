package com.envify.back.service.impl;

import com.envify.back.dao.PackageVersionDao;
import com.envify.back.entity.PackageVersionEntity;
import com.envify.back.service.PackageVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PackageVersionServiceImpl implements PackageVersionService {

    @Autowired
    private PackageVersionDao packageVersionDao;
    @Override
    public List<PackageVersionEntity> findAllPackageVersions() {
        return packageVersionDao.findAll();
    }
    @Override
    public List<PackageVersionEntity> findAllPackageVersionsById(int packageId) {
        return packageVersionDao.findPackageVersionEntitiesByPackageId(packageId);
    }

    @Override
    public void savePackageVersion(PackageVersionEntity packageVersionEntity) {
        packageVersionDao.save(packageVersionEntity);
    }

    @Override
    public PackageVersionEntity findPackageVersionById(int id) {
        return packageVersionDao.getById(id);
    }

    @Override
    public PackageVersionEntity updatePackageVersion(PackageVersionEntity packageVersionEntity) {
        return packageVersionDao.save(packageVersionEntity);
    }

    @Override
    public void deletePackageVersionById(int id) {
        packageVersionDao.deleteById(id);
    }
}
