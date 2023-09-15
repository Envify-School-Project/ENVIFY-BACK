package com.envify.back.service;

import com.envify.back.entity.VersionStatusEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VersionStatusService {
    List<VersionStatusEntity> findAllVersionsStatuses();

    void saveVersionStatus(VersionStatusEntity versionStatus);
}
