package com.envify.back.service.impl;

import com.envify.back.dao.ConfigDao;
import com.envify.back.dao.UserDao;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.OperatingSystemEntity;
import com.envify.back.entity.UserEntity;
import com.envify.back.service.ConfigService;
import com.envify.back.service.OperatingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OperatingSystemService operatingSystemService;

    @Override
    public List<ConfigEntity> findAllConfigs() {
        return configDao.findAll();
    }

    @Override
    public void saveConfig(ConfigEntity configEntity) {
        configDao.save(configEntity);
    }

    @Override
    public ConfigEntity findConfigById(int id) { 
        return configDao.getById(id); 
    }

    @Override
    public ConfigEntity updateConfig(ConfigEntity configEntity) {
        return configDao.save(configEntity);
    }

    @Override
    public void deleteConfigById(int id) { 
        configDao.deleteById(id); 
    }

    private void setOperatingSystemName(ConfigEntity config) {
        OperatingSystemEntity os = operatingSystemService.findOperatingSystemById(config.getOperatingSystemId());
        config.setOperatingSystemName(os.getName());
    }

    @Override
    public List<ConfigEntity> findConfigsByUserId(int userId) {
        List<ConfigEntity> configs = configDao.findConfigsByUserId(userId);

        return configs.stream().peek(this::setOperatingSystemName).toList();
    }

    @Override
    public List<ConfigEntity> findConfigsByUserRole(String userRole) {
        UserEntity user = userDao.findByRole(userRole);
        List<ConfigEntity> configs = configDao.findConfigsByUserId(user.getId());

        return configs.stream().peek(this::setOperatingSystemName).toList();
    }
}
