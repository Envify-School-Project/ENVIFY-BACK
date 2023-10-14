package com.envify.back.dao;

import com.envify.back.entity.ConfigPackageFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ConfigPackageFileDao extends JpaRepository<ConfigPackageFileEntity, Integer> {

    ConfigPackageFileEntity getById(int id);
    ConfigPackageFileEntity getByPackageVersionId(int packageVersionId);
    List<ConfigPackageFileEntity> getByPackageVersionIdIn(Collection<Integer> packageVersionIds);
    void deleteById(int id);
}
