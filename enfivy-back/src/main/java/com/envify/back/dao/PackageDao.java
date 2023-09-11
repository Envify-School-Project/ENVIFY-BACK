package com.envify.back.dao;

import com.envify.back.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDao extends JpaRepository<PackageEntity, Long> {
    PackageEntity getById(int id);
    void deleteById(int id);
}
