package com.envify.back.controller;

import com.envify.back.dao.ConfigPackageFileDao;
import com.envify.back.dto.ConfigDto;
import com.envify.back.dto.ConfigPackageFileDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.ConfigPackageFileEntity;
import com.envify.back.service.ConfigPackageFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/config_package_files")
public class ConfigPackageFilesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPackageFilesController.class);

    @Autowired
    private ConfigPackageFileService configPackageFileService;

    @GetMapping("/")
    public ResponseEntity<List<ConfigPackageFileEntity>> findAllFiles() {
        List<ConfigPackageFileEntity> configPackageFiles = configPackageFileService.findAllConfigPackageFiles();

        return ResponseEntity.ok().body(configPackageFiles);
    }

    @PostMapping("/")
    public ResponseEntity<String> createConfigPackageFile(@RequestBody ConfigPackageFileDto configPackageFileDto) {
        final ConfigPackageFileEntity configPackageFileCreated = new ConfigPackageFileEntity();
        configPackageFileCreated.setDescription(configPackageFileDto.getDescription());
        configPackageFileCreated.setProperties(configPackageFileDto.getProperties());
        configPackageFileCreated.setPackageVersionId(configPackageFileCreated.getPackageVersionId());

        try {
            configPackageFileService.saveConfigPackageFile(configPackageFileCreated);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exception");
        }

        return ResponseEntity.ok().body("Config package file successfully created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigPackageFileEntity> updateConfig(@PathVariable int id, @RequestBody ConfigPackageFileDto configPackageFileDto) {
        ConfigPackageFileEntity configPackageFileUpdated = new ConfigPackageFileEntity();
        mapDtotoEntity(configPackageFileDto, configPackageFileUpdated, id);
        ConfigPackageFileEntity configPackageFileEntity = configPackageFileService.updateConfigPackageFile(configPackageFileUpdated);

        return ResponseEntity.ok().body(configPackageFileEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteConfigPackageFile(@PathVariable int id) {
        configPackageFileService.deleteConfigPackageFile(id);
    }

    private void mapDtotoEntity(ConfigPackageFileDto configPackageFileDto, ConfigPackageFileEntity configPackageFileEntity, int configPackageFileId) {
        configPackageFileEntity.setId(configPackageFileId);
        configPackageFileEntity.setDescription(configPackageFileDto.getDescription());
        configPackageFileEntity.setProperties(configPackageFileEntity.getProperties());
        configPackageFileEntity.setPackageVersionId(configPackageFileDto.getPackageVersionId());
    }
}
