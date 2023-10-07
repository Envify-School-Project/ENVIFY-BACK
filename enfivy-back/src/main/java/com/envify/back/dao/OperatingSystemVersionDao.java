package com.envify.back.dao;

import com.envify.back.entity.OperatingSystemVersionEntity;
import com.envify.back.entity.PackageVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatingSystemVersionDao extends JpaRepository<OperatingSystemVersionEntity, Long> {
    OperatingSystemVersionEntity getById(int id);
    List<OperatingSystemVersionEntity> findOperatingSystemVersionEntitiesByOperatingSystemId(int operatingSystemId);
    void deleteById(int id);
}
