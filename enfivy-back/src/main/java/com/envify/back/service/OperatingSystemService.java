package com.envify.back.service;

import com.envify.back.entity.OperatingSystemEntity;
import com.envify.back.entity.PackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperatingSystemService {
    List<OperatingSystemEntity> findAllOperatingSystems();
    void saveOperatingSystem(OperatingSystemEntity operatingSystemEntity);
    OperatingSystemEntity findOperatingSystemById(int id);
    void deleteOperatingSystemById(int id);

}
