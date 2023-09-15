package com.envify.back.service;

import com.envify.back.entity.PackageEntity;
import com.envify.back.entity.PackageVersionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PackageVersionService {
    List<PackageVersionEntity> findAllPackageVersions(int packageId);
    void savePackageVersion(PackageVersionEntity packageVersionEntity);
    PackageVersionEntity findPackageVersionById(int id);
    PackageVersionEntity updatePackageVersion(PackageVersionEntity packageVersionEntity);
    void deletePackageVersionById(int id);
}
