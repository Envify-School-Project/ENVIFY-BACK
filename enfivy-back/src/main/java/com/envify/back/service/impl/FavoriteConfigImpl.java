package com.envify.back.service.impl;
import com.envify.back.dao.FavoriteConfigDao;
import com.envify.back.entity.FavoriteConfigEntity;
import com.envify.back.service.FavoriteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FavoriteConfigImpl implements FavoriteConfigService {

    @Autowired
    private FavoriteConfigDao favoriteConfigDao;

    @Override
    public List<FavoriteConfigEntity> findAllFavoriteConfigs() {
        return favoriteConfigDao.findAll();
    }

    @Override
    public void saveFavoriteConfig(FavoriteConfigEntity favoriteConfigEntity) {
        favoriteConfigDao.save(favoriteConfigEntity);
    }

    @Override
    public void deleteFavoriteConfigById(int id) {
        favoriteConfigDao.deleteByConfigId(id);
    }
}
