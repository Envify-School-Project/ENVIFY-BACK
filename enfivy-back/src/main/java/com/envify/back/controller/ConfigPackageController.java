package com.envify.back.controller;

import com.envify.back.dto.ConfigPackageDto;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.service.ConfigPackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/config-packages")
public class ConfigPackageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPackageController.class);

    @Autowired
    private ConfigPackageService configPackageService;

    @GetMapping()
    public ResponseEntity<List<ConfigPackageEntity>> findAllConfigPackages() {
        List<ConfigPackageEntity> configPackages = configPackageService.findAllConfigPackages();

        return ResponseEntity.ok().body(configPackages);
    }

    @PostMapping("/")
    public ResponseEntity<String> createConfigPackage(@RequestBody ConfigPackageDto configPackageEntityDto) {
        final ConfigPackageEntity configPackageCreated = new ConfigPackageEntity();
        configPackageCreated.setPackageVersionId(configPackageEntityDto.getPackageVersionId());

        try {
            configPackageService.saveConfigPackage(configPackageCreated);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exeption");
        }

        return ResponseEntity.ok().body("Package succesfully created.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigPackageEntity> findConfigPackageById(@PathVariable int id) {
        ConfigPackageEntity packageEntity = configPackageService.findConfigPackageById(id);

        return ResponseEntity.ok().body(packageEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigPackageEntity> updateConfigPackage(@PathVariable int id, @RequestBody ConfigPackageDto configPackageDto) {
        ConfigPackageEntity configPackageUpdated = new ConfigPackageEntity();
        mapConfigPackageDtoToConfigPackageEntity(configPackageDto, configPackageUpdated, id);
        ConfigPackageEntity packageEntity = configPackageService.updateConfigPackage(configPackageUpdated);

        return ResponseEntity.ok().body(packageEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteConfigPackageById(@PathVariable int id) {
        configPackageService.deleteConfigPackageById(id);
    }

    private void mapConfigPackageDtoToConfigPackageEntity(ConfigPackageDto packageDto, ConfigPackageEntity packageEntity, int configPackageId) {
        packageEntity.setConfigId(configPackageId);
        packageEntity.setPackageVersionId(packageDto.getPackageVersionId());
    }
}
