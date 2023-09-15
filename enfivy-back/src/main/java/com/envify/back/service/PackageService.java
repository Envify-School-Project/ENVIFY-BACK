package com.envify.back.service;

import com.envify.back.dto.PackageDto;
import com.envify.back.entity.PackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PackageService {
    List<PackageEntity> findAllPackages();
    void savePackage(PackageEntity packageEntity);
    PackageEntity findPackageById(int id);
    PackageEntity updatePackage(PackageEntity packageEntity);
    void deletePackageById(int id);
}
