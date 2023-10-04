package com.envify.back.dao;

import com.envify.back.entity.OperatingSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingSystemDao extends JpaRepository<OperatingSystemEntity, Long> {
    OperatingSystemEntity getById(int id);
    void deleteById(int id);
}
