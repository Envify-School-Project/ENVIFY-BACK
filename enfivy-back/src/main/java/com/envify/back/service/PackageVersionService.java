package com.envify.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.envify.back.entity.PackageVersionEntity;

@Service
public interface PackageVersionService {
    List<PackageVersionEntity> findAllPackageVersions();
    List<PackageVersionEntity> findAllPackageVersionsById(int packageId);
    void savePackageVersion(PackageVersionEntity packageVersionEntity);
    PackageVersionEntity findPackageVersionById(int id);
    PackageVersionEntity updatePackageVersion(PackageVersionEntity packageVersionEntity);
    void deletePackageVersionById(int id);
}
