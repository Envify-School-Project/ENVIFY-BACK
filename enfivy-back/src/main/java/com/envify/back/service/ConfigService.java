package com.envify.back.service;

import com.envify.back.entity.ConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigService {
    List<ConfigEntity> findAllConfigs();
    void saveConfig(ConfigEntity configEntity);
    ConfigEntity findConfigById(int id);
    ConfigEntity updateConfig(ConfigEntity configEntity);
    void deleteConfigById(int id);
}
