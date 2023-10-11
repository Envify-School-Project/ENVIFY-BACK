package com.envify.back.controller;

import com.envify.back.dto.ConfigDto;
import com.envify.back.dto.finaldto.FinalObjectDto;
import com.envify.back.dto.finaldto.PackageObjectDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.entity.ConfigPackageIdEntity;
import com.envify.back.entity.UserEntity;
import com.envify.back.service.ConfigPackageService;
import com.envify.back.service.ConfigService;
import com.envify.back.service.UserService;
import com.google.gson.Gson;
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
    Gson gson = new Gson();

    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigPackageService configPackageService;

    @GetMapping()
    public ResponseEntity<List<ConfigEntity>> findAllConfigs() {
        List<ConfigEntity> configs = configService.findAllConfigs();

        return ResponseEntity.ok().body(configs);
    }

    @PostMapping("/")
    public ResponseEntity<FinalObjectDto> createConfig(@RequestBody FinalObjectDto finalObjectDto, Principal principal) {

        final ConfigEntity configEntity = new ConfigEntity();
        UserEntity currentUser = userService.findByEmail(principal.getName());

        configEntity.setName(finalObjectDto.getName());
        configEntity.setOperatingSystemId(finalObjectDto.getOs().getVersionId());
        configEntity.setDescription(finalObjectDto.getDescription());
        configEntity.setUserId(1);

//        UNCOMMENT WHEN DONE
//        configEntity.setUserId(currentUser.getId());

         try {
            configService.saveConfig(configEntity);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        for (PackageObjectDto packageObjectDto: finalObjectDto.getPackages()) {
            ConfigPackageEntity configPackageEntity = new ConfigPackageEntity();

            configPackageEntity.setConfigPackageId(new ConfigPackageIdEntity(configEntity.getId(), packageObjectDto.getVersionId()));
            configPackageEntity.setConfigurationScripts(gson.toJson(packageObjectDto.getPackageProperties()));

            try {
                configPackageService.saveConfigPackage(configPackageEntity);
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
        return ResponseEntity.ok().body(finalObjectDto);
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

    private void mapConfigDtoToPackageEntity(ConfigDto configDto, ConfigEntity configEntity, int configId) {
        configEntity.setId(configId);
        configEntity.setName(configDto.getName());
        configEntity.setDescription(configDto.getDescription());
        configEntity.setUserId(configDto.getUserId());
        configEntity.setOperatingSystemId(configDto.getOperatingSystemId());
    }
}
