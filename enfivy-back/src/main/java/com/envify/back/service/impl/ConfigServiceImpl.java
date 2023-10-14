package com.envify.back.service.impl;

import com.envify.back.dao.*;
import com.envify.back.dto.UserOwnPackageDto;
import com.envify.back.entity.*;
import com.envify.back.service.ConfigService;
import com.envify.back.service.OperatingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ConfigPackageDao configPackageDao;
    @Autowired
    private PackageVersionDao packageVersionDao;
    @Autowired
    private PackageDao packageDao;
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
            List<ConfigPackageEntity> configPackages = configPackageDao.findAllByConfigId(config.getId());
            List<Integer> packagesVersionsIds = configPackages.stream().map(ConfigPackageEntity::getConfigPackageId).map(ConfigPackageIdEntity::getPackageVersionId).toList();
            List<PackageVersionEntity> packagesVersions = packageVersionDao.findAllByIdIn(packagesVersionsIds);
            List<Integer> packagesIds = packagesVersions.stream().map(PackageVersionEntity::getPackageId).toList();
            List<PackageEntity> packageEntities = packageDao.findAllByIdIn(packagesIds);
            List<UserOwnPackageDto> packages = new ArrayList<>();

            for (ConfigPackageEntity configPackage : configPackages) {
                UserOwnPackageDto userOwnPackageDto = new UserOwnPackageDto();
                userOwnPackageDto.setName(packageEntities.stream().filter(p -> p.getId() == configPackage.getConfigPackageId().getPackageVersionId()).findFirst().get().getName());
                userOwnPackageDto.setVersionNumber(packagesVersions.stream().filter(p -> p.getId() == configPackage.getConfigPackageId().getPackageVersionId()).findFirst().get().getVersionNumber());
                packages.add(userOwnPackageDto);
            }

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
