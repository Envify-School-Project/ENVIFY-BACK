package com.envify.back.service;

import com.envify.back.entity.FavoriteConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteConfigService {
    List<FavoriteConfigEntity> findAllFavoriteConfigs();
    void saveFavoriteConfig(FavoriteConfigEntity favoriteConfigEntity);
    void deleteFavoriteConfigById(int id);
}
