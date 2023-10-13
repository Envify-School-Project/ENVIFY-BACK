package com.envify.back.service;

import com.envify.back.entity.ConfigPackageFileEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigPackageFileService {
    List<ConfigPackageFileEntity> findAllConfigPackageFiles();
    List<ConfigPackageFileEntity> findAllConfigPackageFilesByPackageVersionIds(List<Integer> packageVersionIds);
    ConfigPackageFileEntity findConfigPackageFileByPackageVersionId(int packageVersionId);
    void saveConfigPackageFile(ConfigPackageFileEntity configPackageFileCreated);
    ConfigPackageFileEntity updateConfigPackageFile(ConfigPackageFileEntity configPackageFileEntity);
    void deleteConfigPackageFile(int id);
}
