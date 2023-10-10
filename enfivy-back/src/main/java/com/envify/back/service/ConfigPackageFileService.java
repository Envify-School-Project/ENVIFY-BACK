package com.envify.back.service;

import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.ConfigPackageFileEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface ConfigPackageFileService {
    List<ConfigPackageFileEntity> findAllConfigPackageFiles();
    ConfigPackageFileEntity findConfigPackageFileById(int id);
    ConfigPackageFileEntity findConfigPackageFileByPackageVersionId(int packageVersionId);
    void saveConfigPackageFile(ConfigPackageFileEntity configPackageFileCreated);
    ConfigPackageFileEntity updateConfigPackageFile(ConfigPackageFileEntity configPackageFileEntity);
    void deleteConfigPackageFile(int id);
}
