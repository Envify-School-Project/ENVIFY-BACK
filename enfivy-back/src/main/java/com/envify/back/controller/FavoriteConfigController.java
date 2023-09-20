package com.envify.back.controller;

import com.envify.back.dto.FavoriteConfigDto;
import com.envify.back.entity.FavoriteConfigEntity;
import com.envify.back.service.FavoriteConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favoriteConfig")
public class FavoriteConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavoriteConfigController.class);

    @Autowired
    private FavoriteConfigService favoriteConfigService;

    @GetMapping()
    public ResponseEntity<List<FavoriteConfigEntity>> findAllFavoriteConfigs() {
        List<FavoriteConfigEntity> favoriteConfigs = favoriteConfigService.findAllFavoriteConfigs();

        return ResponseEntity.ok().body(favoriteConfigs);
    }

    @PostMapping("/")
    public ResponseEntity<String> createFavoriteConfig(@RequestBody FavoriteConfigDto favoriteConfigDto) {
        final FavoriteConfigEntity favoriteConfigCreated = new FavoriteConfigEntity();
        favoriteConfigCreated.setUserId(favoriteConfigDto.getUserId());

        try {
            favoriteConfigService.saveFavoriteConfig(favoriteConfigCreated);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exeption");
        }

        return ResponseEntity.ok().body("Package succesfully created.");
    }

    @DeleteMapping("/{id}")
    public void deleteFavoriteConfigById(@PathVariable int id) {
        favoriteConfigService.deleteFavoriteConfigById(id);
    }
}
