package com.envify.back.dao;

import com.envify.back.entity.FavoriteConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteConfigDao extends JpaRepository<FavoriteConfigEntity, Long> {
    void deleteByConfigId(int config_id);
}
