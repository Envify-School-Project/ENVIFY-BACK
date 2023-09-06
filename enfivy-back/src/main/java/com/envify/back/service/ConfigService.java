package com.envify.back.service;

import com.envify.back.entity.ConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigService {
    List<ConfigEntity> findAllConfigs();
    ConfigEntity findConfigById(int id);
    void deleteConfigById(int id);
}
