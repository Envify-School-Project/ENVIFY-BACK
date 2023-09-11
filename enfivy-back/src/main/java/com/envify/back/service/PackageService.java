package com.envify.back.service;

import com.envify.back.entity.PackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PackageService {
    List<PackageEntity> findAllPackages();
    PackageEntity createPackage(PackageEntity packageEntity);
    PackageEntity findPackageById(int id);
    void deletePackageById(int id);
}
