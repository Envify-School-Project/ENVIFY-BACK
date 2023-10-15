package com.envify.back.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.envify.back.entity.PackageEntity;

@Service
public interface PackageService {
    List<PackageEntity> findAllPackages();
    void savePackage(PackageEntity packageEntity);
    PackageEntity findPackageById(int id);
    PackageEntity updatePackage(PackageEntity packageEntity);
    void deletePackageById(int id);
}
