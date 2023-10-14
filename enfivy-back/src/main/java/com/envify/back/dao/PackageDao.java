package com.envify.back.dao;

import com.envify.back.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageDao extends JpaRepository<PackageEntity, Long> {
    PackageEntity getById(int id);
    List<PackageEntity> findAllByIdIn(List<Integer> ids);
    void deleteById(int id);
}
