package com.envify.back.service.impl;

import com.envify.back.dao.ConfigPackageDao;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.service.ConfigPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ConfigPackageServiceImpl implements ConfigPackageService {

    @Autowired
    ConfigPackageDao configPackageDao;

    @Override
    public void saveConfigPackage(ConfigPackageEntity configPackageEntity) {
        configPackageDao.save(configPackageEntity);
    }
}
