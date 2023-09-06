package com.envify.back.service;

import com.envify.back.entity.ConfigEntity;

import java.util.List;

public interface ConfigService {
    List<ConfigEntity> findAllConfigs();
    ConfigEntity findConfigById(int id);
    void deleteConfigById(int id);
}
