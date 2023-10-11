package com.envify.back.controller;

import com.envify.back.dto.ConfigDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.UserEntity;
import com.envify.back.service.ConfigService;
import com.envify.back.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<ConfigEntity>> findAllConfigs() {
        List<ConfigEntity> configs = configService.findAllConfigs();

        return ResponseEntity.ok().body(configs);
    }

    @PostMapping("/")
    public ResponseEntity<String> createConfig(@RequestBody ConfigDto configDto) {
        final ConfigEntity configCreated = new ConfigEntity();
        configCreated.setName(configDto.getName());
        configCreated.setDescription(configDto.getDescription());
        configCreated.setUserId(configDto.getUserId());
        configCreated.setOperatingSystemId(configDto.getOperatingSystemId());

        try {
            configService.saveConfig(configCreated);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body("Bad request exception");
        }

        return ResponseEntity.ok().body("Config successfully created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigEntity> findConfigById(@PathVariable int id) {
        ConfigEntity config = configService.findConfigById(id);

        return ResponseEntity.ok().body(config);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigEntity> updateConfig(@PathVariable int id, @RequestBody ConfigDto configDto) {
        ConfigEntity configUpdated = new ConfigEntity();
        mapConfigDtoToPackageEntity(configDto, configUpdated, id);
        ConfigEntity configEntity = configService.updateConfig(configUpdated);

        return ResponseEntity.ok().body(configEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteConfigById(@PathVariable int id) {
        configService.deleteConfigById(id);
    }

    @GetMapping("/me")
    public ResponseEntity<List<ConfigEntity>> findConfigByUserId(Principal principal) {
        UserEntity user = userService.findByEmail(principal.getName());
        List<ConfigEntity> configs = configService.findConfigsByUserId(user.getId());

        return ResponseEntity.ok().body(configs);
    }

    private void mapConfigDtoToPackageEntity(ConfigDto configDto, ConfigEntity configEntity, int configId) {
        configEntity.setId(configId);
        configEntity.setName(configDto.getName());
        configEntity.setDescription(configDto.getDescription());
        configEntity.setUserId(configDto.getUserId());
        configEntity.setOperatingSystemId(configDto.getOperatingSystemId());
    }
}
