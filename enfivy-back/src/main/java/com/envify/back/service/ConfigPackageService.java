package com.envify.back.service;

import com.envify.back.entity.ConfigPackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigPackageService {
    List<ConfigPackageEntity> findAllConfigPackages();
    void saveConfigPackage(ConfigPackageEntity configPackageEntity);
    ConfigPackageEntity findConfigPackageById(int id);
    ConfigPackageEntity updateConfigPackage(ConfigPackageEntity configPackageEntity);
    void deleteConfigPackageById(int id);
}
