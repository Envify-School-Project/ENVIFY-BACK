package com.envify.back.service.impl;

import com.envify.back.dao.ConfigPackageFileDao;
import com.envify.back.entity.ConfigPackageFileEntity;
import com.envify.back.service.ConfigPackageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConfigPackageFileServiceImpl implements ConfigPackageFileService {

    @Autowired
    private ConfigPackageFileDao configPackageFileDao;

    @Override
    public List<ConfigPackageFileEntity> findAllConfigPackageFiles() {
        return configPackageFileDao.findAll();
    }

    @Override
    public List<ConfigPackageFileEntity> findAllConfigPackageFilesByPackageVersionIds(List<Integer> packageVersionIds) {
        return configPackageFileDao.getByPackageVersionIdIn(packageVersionIds);
    }

    @Override
    public ConfigPackageFileEntity findConfigPackageFileByPackageVersionId(int packageVersionId) {
        return configPackageFileDao.getByPackageVersionId(packageVersionId);
    }

    @Override
    public void saveConfigPackageFile(ConfigPackageFileEntity configPackageFileEntity) {
        configPackageFileDao.save(configPackageFileEntity);
    }

    @Override
    public ConfigPackageFileEntity updateConfigPackageFile(ConfigPackageFileEntity configPackageFileEntity) {
        return configPackageFileDao.save(configPackageFileEntity);
    }

    @Override
    public void deleteConfigPackageFile(int id) {
        configPackageFileDao.deleteById(id);
    }
}
