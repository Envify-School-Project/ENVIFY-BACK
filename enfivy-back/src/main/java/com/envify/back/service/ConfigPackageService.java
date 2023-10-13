package com.envify.back.service;

import com.envify.back.entity.ConfigPackageEntity;
import org.springframework.stereotype.Service;

@Service
public interface ConfigPackageService {
    void saveConfigPackage(ConfigPackageEntity configPackageEntity);
}
