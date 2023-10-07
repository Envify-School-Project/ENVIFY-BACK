package com.envify.back.service;

import com.envify.back.entity.OperatingSystemVersionEntity;
import com.envify.back.entity.PackageVersionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperatingSystemVersionService {
    List<OperatingSystemVersionEntity> findAllOperatingSystemVersions(int operatingSystemId);
    void saveOperatingSystemVersion(OperatingSystemVersionEntity operatingSystemVersionEntity);
    OperatingSystemVersionEntity findOperatingSystemVersionById(int id);
    void deleteOperatingSystemVersionById(int id);
}
