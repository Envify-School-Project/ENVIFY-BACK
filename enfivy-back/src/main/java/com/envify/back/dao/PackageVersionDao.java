package com.envify.back.dao;

import com.envify.back.entity.PackageVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageVersionDao extends JpaRepository<PackageVersionEntity, Long> {
    PackageVersionEntity getById(int id);
    List<PackageVersionEntity> findPackageVersionEntitiesByPackageId(int packageId);
    List<PackageVersionEntity> findAllByIdIn(List<Integer> ids);
    void deleteById(int id);
}
