package com.envify.back.service.impl;

import com.envify.back.dao.*;
import com.envify.back.dto.UserOwnPackageDto;
import com.envify.back.entity.*;
import com.envify.back.service.ConfigService;
import com.envify.back.service.OperatingSystemService;
import com.fasterxml.jackson.databind.ObjectMapper;
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



    private void setOperatingSystemName(ConfigEntity config) {
        OperatingSystemEntity os = operatingSystemService.findOperatingSystemById(config.getOperatingSystemId());
        config.setOperatingSystemName(os.getName());
    }

    @Override
    public List<ConfigEntity> findAllConfigs() {
        List<ConfigEntity> configs = configDao.findAll();

        return configs.stream().peek(this::setOperatingSystemName).toList();
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

    @Override
    public List<ConfigEntity> findConfigsByUserId(int userId) {
        List<ConfigEntity> configs = configDao.findConfigsByUserId(userId);

        for (ConfigEntity config : configs) {
            List<String> packages = configDao.findUserOwnPackagesByConfigId(config.getId());
            if (!packages.isEmpty()) {
                System.out.println("......... " + packages.get(0));
            }
            //UserOwnPackageDto userOwnPackageDto = new UserOwnPackageDto(packages.get(0), packages.get(1));
            config.setPackages(packages);
        }

        return configs.stream().peek(this::setOperatingSystemName).toList();
    }

    @Override
    public List<ConfigEntity> findConfigsByUserRole(String userRole) {
        UserEntity user = userDao.findByRole(userRole);
        List<ConfigEntity> configs = configDao.findConfigsByUserId(user.getId());

        return configs.stream().peek(this::setOperatingSystemName).toList();
    }
}
