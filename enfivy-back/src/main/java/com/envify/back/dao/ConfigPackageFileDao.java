package com.envify.back.dao;

import com.envify.back.entity.ConfigPackageFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigPackageFileDao extends JpaRepository<ConfigPackageFileEntity, Integer> {

    ConfigPackageFileEntity getById(int id);
    ConfigPackageFileEntity getByPackageVersionId(int packageVersionId);
    void deleteById(int id);
}
