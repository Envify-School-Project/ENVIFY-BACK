package com.envify.back.controller;

import com.envify.back.dto.ConfigPackageFileDto;
import com.envify.back.entity.ConfigPackageFileEntity;
import com.envify.back.service.ConfigPackageFileService;
import com.google.gson.Gson;
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
    Gson gson = new Gson();

    @Autowired
    private ConfigPackageFileService configPackageFileService;

    @GetMapping("/")
    public ResponseEntity<List<ConfigPackageFileEntity>> findAllFiles() {
        List<ConfigPackageFileEntity> configPackageFiles = configPackageFileService.findAllConfigPackageFiles();

        return ResponseEntity.ok().body(configPackageFiles);
    }

    @PostMapping("/by_package_version_ids")
    public ResponseEntity<List<ConfigPackageFileEntity>> findAllFilesByPackageVersionIds(@RequestBody List<Integer> packageVersionIds) {
        List<ConfigPackageFileEntity> configPackageFiles = configPackageFileService.findAllConfigPackageFilesByPackageVersionIds(packageVersionIds);

        return ResponseEntity.ok().body(configPackageFiles);
    }

    @PostMapping("/")
    public ResponseEntity<String> createConfigPackageFile(@RequestBody ConfigPackageFileDto configPackageFileDto) {
        final ConfigPackageFileEntity configPackageFileCreated = new ConfigPackageFileEntity();
        configPackageFileCreated.setDescription(configPackageFileDto.getDescription());
        configPackageFileCreated.setProperties(gson.toJson(configPackageFileDto.getProperties()));
        configPackageFileCreated.setPackageVersionId(configPackageFileDto.getPackageVersionId());

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
        mapDtoToEntity(configPackageFileDto, configPackageFileUpdated, id);
        ConfigPackageFileEntity configPackageFileEntity = configPackageFileService.updateConfigPackageFile(configPackageFileUpdated);

        return ResponseEntity.ok().body(configPackageFileEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteConfigPackageFile(@PathVariable int id) {
        configPackageFileService.deleteConfigPackageFile(id);
    }

    private void mapDtoToEntity(ConfigPackageFileDto configPackageFileDto, ConfigPackageFileEntity configPackageFileEntity, int configPackageFileId) {
        configPackageFileEntity.setId(configPackageFileId);
        configPackageFileEntity.setDescription(configPackageFileDto.getDescription());
        configPackageFileEntity.setProperties(gson.toJson(configPackageFileDto.getProperties()));
        configPackageFileEntity.setPackageVersionId(configPackageFileDto.getPackageVersionId());
    }
}
