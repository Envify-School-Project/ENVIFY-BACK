package com.envify.back.service.impl;

import com.envify.back.dao.ConfigDao;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public List<ConfigEntity> findAllConfigs() {
        return configDao.findAll();
    }

    @Override
    public ConfigEntity findConfigById(int id) { 
        return configDao.getById(id); 
    }

    @Override
    public void deleteConfigById(int id) { 
        configDao.deleteById(id); 
    }
}
