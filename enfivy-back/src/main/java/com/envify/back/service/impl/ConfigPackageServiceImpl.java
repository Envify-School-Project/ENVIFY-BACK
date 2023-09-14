package com.envify.back.service.impl;
import com.envify.back.dao.ConfigPackageDao;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.service.ConfigPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConfigPackageServiceImpl implements ConfigPackageService {

    @Autowired
    private ConfigPackageDao configPackageDao;

    @Override
    public List<ConfigPackageEntity> findAllConfigPackages() {
        return configPackageDao.findAll();
    }

    @Override
    public void saveConfigPackage(ConfigPackageEntity configPackageEntity) {
        configPackageDao.save(configPackageEntity);
    }

    @Override
    public ConfigPackageEntity findConfigPackageById(int config_id) {
        return configPackageDao.getByConfigId(config_id);
    }

    @Override
    public ConfigPackageEntity updateConfigPackage(ConfigPackageEntity configPackageEntity) {
        return configPackageDao.save(configPackageEntity);
    }

    @Override
    public void deleteConfigPackageById(int config_id) {
        configPackageDao.deleteByConfigId(config_id);
    }
}
