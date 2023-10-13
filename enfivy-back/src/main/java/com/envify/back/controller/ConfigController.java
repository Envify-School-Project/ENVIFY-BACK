package com.envify.back.controller;

import com.envify.back.dto.ConfigDto;
import com.envify.back.dto.ReceivedConfigObjectDto;
import com.envify.back.dto.CompletedConfigDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.exception.EnvifyException;
import com.envify.back.security.JWTUtil;
import com.envify.back.service.*;
import com.envify.back.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private CompletedConfigGeneratorService completedConfigGeneratorService;

    @GetMapping()
    public ResponseEntity<List<ConfigEntity>> findAllConfigs() {
        List<ConfigEntity> configs = configService.findAllConfigs();

        return ResponseEntity.ok().body(configs);
    }

    @PostMapping("/")
    public ResponseEntity<CompletedConfigDto> createConfig(@RequestBody ReceivedConfigObjectDto receivedConfigObjectDto, HttpServletRequest request) throws EnvifyException, IOException {
        CompletedConfigDto completedConfigDto = completedConfigGeneratorService.generateCompletedConfig(receivedConfigObjectDto, request);

        return ResponseEntity.ok().body(completedConfigDto);
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

    @GetMapping("/suggested")
    public ResponseEntity<List<ConfigEntity>> findSuggestedConfigs() {
        List<ConfigEntity> configs = configService.findConfigsByUserRole("suggested");

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
