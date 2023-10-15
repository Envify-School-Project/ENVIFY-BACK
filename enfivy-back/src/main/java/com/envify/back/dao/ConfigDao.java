package com.envify.back.dao;

import com.envify.back.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigDao extends JpaRepository<ConfigEntity, Long> {
    ConfigEntity getById(int id);
    void deleteById(int id);
    List<ConfigEntity> findConfigsByUserId(int userId);
    @Query("""
        SELECT p.name, pv.versionNumber, pv.id
        FROM PackageEntity p
        LEFT JOIN PackageVersionEntity pv ON p.id = pv.packageId
        LEFT JOIN ConfigPackageEntity cp ON pv.id = cp.configPackageId.packageVersionId
        WHERE cp.configPackageId.configId = :configId
    """)
    List<String> findUserOwnPackagesByConfigId(@Param("configId") int configId);
}
