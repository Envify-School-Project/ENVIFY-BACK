package com.envify.back.controller;

import com.envify.back.dto.ConfigDto;
import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.dto.finaldto.FinalObjectDto;
import com.envify.back.dto.finaldto.FinalResponseConfigFileDto;
import com.envify.back.dto.finaldto.FinalResponseDto;
import com.envify.back.dto.finaldto.PackageObjectDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.entity.ConfigPackageIdEntity;
import com.envify.back.exception.EnvifyException;
import com.envify.back.security.JWTUtil;
import com.envify.back.service.*;
import com.envify.back.service.configFileParser.ConfigFileParser;
import com.envify.back.service.ConfigPackageService;
import com.envify.back.service.ConfigService;
import com.envify.back.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);
    Gson gson = new Gson();

    @Autowired
    private ConfigService configService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigPackageService configPackageService;
    @Autowired
    private ScriptGeneratorService scriptGeneratorService;
    @Autowired
    private FinalResponseGeneratorService finalResponseGeneratorService;

    @GetMapping()
    public ResponseEntity<List<ConfigEntity>> findAllConfigs() {
        List<ConfigEntity> configs = configService.findAllConfigs();

        return ResponseEntity.ok().body(configs);
    }

    @PostMapping("/")
    public ResponseEntity<FinalResponseDto> createConfig(@RequestBody FinalObjectDto finalObjectDto, HttpServletRequest request) throws EnvifyException, IOException {
        ConfigEntity configEntity = finalResponseGeneratorService.generateNewConfig(finalObjectDto, request);

        List<ScriptRequestBodyDto> scriptRequestBody = new ArrayList<>();
        List<FinalResponseConfigFileDto> configFiles = new ArrayList<>();

        for (PackageObjectDto packageObjectDto: finalObjectDto.getPackages()) {
            ConfigPackageEntity configPackageEntity = new ConfigPackageEntity();

            ScriptRequestBodyDto scriptRequestBodyDto = finalResponseGeneratorService.generateScriptBodyRequestDto(finalObjectDto, packageObjectDto);


            configPackageEntity.setConfigPackageId(new ConfigPackageIdEntity(configEntity.getId(), packageObjectDto.getVersionId()));

            if (packageObjectDto.getPackageProperties().size() != 0) {
                ConfigFileParser configFileParser = new ConfigFileParser(packageObjectDto.getName(), packageObjectDto);
                configPackageEntity.setConfigurationScripts(configFileParser.parseFile());

                FinalResponseConfigFileDto finalResponseConfigFileDto = finalResponseGeneratorService.generateFinalResponseConfigFileDto(configFileParser);
                configFiles.add(finalResponseConfigFileDto);
            }

            try {
                configPackageService.saveConfigPackage(configPackageEntity);
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }

            scriptRequestBody.add(scriptRequestBodyDto);
        }

        List<ScriptDto> scripts = scriptGeneratorService.buildScripts(scriptRequestBody);

        FinalResponseDto finalResponseDto = new FinalResponseDto(scripts, configFiles);
// REMOVE CODE ABOVE AND UNCOMMENT BELOW
//        FinalResponseDto finalResponseDto = finalResponseGeneratorService.generateCompletedConfig(finalObjectDto, request);

        return ResponseEntity.ok().body(finalResponseDto);

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
    public ResponseEntity<List<ConfigEntity>> findConfigByUserId(HttpServletRequest request) {
        String accessToken = jwtUtil.resolveToken(request);
        Integer userId = jwtUtil.getAllClaimsFromToken(accessToken).get("id", Integer.class);
        List<ConfigEntity> configs = configService.findConfigsByUserId(userId);

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
