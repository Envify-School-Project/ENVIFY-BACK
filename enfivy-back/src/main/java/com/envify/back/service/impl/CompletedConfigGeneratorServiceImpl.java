package com.envify.back.service.impl;

import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.dto.ReceivedConfigObjectDto;
import com.envify.back.dto.CompletedConfigFileDto;
import com.envify.back.dto.CompletedConfigDto;
import com.envify.back.dto.ReceivedPackageDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.entity.ConfigPackageIdEntity;
import com.envify.back.exception.EnvifyException;
import com.envify.back.security.JWTUtil;
import com.envify.back.service.ConfigPackageService;
import com.envify.back.service.CompletedConfigGeneratorService;
import com.envify.back.service.ScriptGeneratorService;
import com.envify.back.service.configFileParser.ConfigFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompletedConfigGeneratorServiceImpl implements CompletedConfigGeneratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompletedConfigGeneratorServiceImpl.class);
	
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private ConfigServiceImpl configService;
    @Autowired
    private ConfigPackageService configPackageService;
    @Autowired
    private ScriptGeneratorService scriptGeneratorService;
    
    @Override
    public CompletedConfigDto generateCompletedConfig(ReceivedConfigObjectDto receivedConfigObjectDto, HttpServletRequest request) throws EnvifyException, IOException {
        ConfigEntity configEntity = generateNewConfig(receivedConfigObjectDto, request);

        List<ScriptRequestBodyDto> scriptRequestBody = new ArrayList<>();
        List<CompletedConfigFileDto> configFiles = new ArrayList<>();

        for (ReceivedPackageDto receivedPackageDto : receivedConfigObjectDto.getPackages()) {
            ConfigPackageEntity configPackageEntity = new ConfigPackageEntity();

            ScriptRequestBodyDto scriptRequestBodyDto = generateScriptBodyRequestDto(receivedConfigObjectDto, receivedPackageDto);

            configPackageEntity.setConfigPackageId(new ConfigPackageIdEntity(configEntity.getId(), receivedPackageDto.getVersionId()));

            if (receivedPackageDto.getPackageProperties().size() != 0) {
                ConfigFileParser configFileParser = new ConfigFileParser(receivedPackageDto.getName(), receivedPackageDto);
                configPackageEntity.setConfigurationScripts(configFileParser.parseFile());

                CompletedConfigFileDto completedConfigFileDto = generateFinalResponseConfigFileDto(configFileParser);
                configFiles.add(completedConfigFileDto);
            }

            try {
                configPackageService.saveConfigPackage(configPackageEntity);
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }

            scriptRequestBody.add(scriptRequestBodyDto);
        }

        List<ScriptDto> scripts = new ArrayList<>();

        scripts = scriptGeneratorService.buildScripts(scriptRequestBody);

        CompletedConfigDto completedConfigDto = new CompletedConfigDto(scripts, configFiles);

        return completedConfigDto;
    }

    public ConfigEntity generateNewConfig(ReceivedConfigObjectDto receivedConfigObjectDto, HttpServletRequest request) {
        ConfigEntity configEntity = new ConfigEntity();
        String accessToken = jwtUtil.resolveToken(request);
        Integer userId = jwtUtil.getAllClaimsFromToken(accessToken).get("id", Integer.class);

        configEntity.setName(receivedConfigObjectDto.getName());
        configEntity.setOperatingSystemId(receivedConfigObjectDto.getOs().getVersionId());
        configEntity.setDescription(receivedConfigObjectDto.getDescription());
        configEntity.setUserId(userId);

        try {
            configService.saveConfig(configEntity);
            System.out.println("Config correctly saved id is : " + configEntity.getId());
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        return configEntity;
    }

    public ScriptRequestBodyDto generateScriptBodyRequestDto(ReceivedConfigObjectDto receivedConfigObjectDto, ReceivedPackageDto receivedPackageDto) {
        ScriptRequestBodyDto scriptRequestBodyDto = new ScriptRequestBodyDto();

        scriptRequestBodyDto.setConfig(receivedPackageDto.getName());
        scriptRequestBodyDto.setRelease(receivedPackageDto.getVersionNumber());
        scriptRequestBodyDto.setOs(receivedConfigObjectDto.getOs().getName());

        return scriptRequestBodyDto;
    }

    public CompletedConfigFileDto generateFinalResponseConfigFileDto(ConfigFileParser configFileParser) throws IOException {
        CompletedConfigFileDto completedConfigFileDto = new CompletedConfigFileDto();

        completedConfigFileDto.setFile(configFileParser.parseFile());
        completedConfigFileDto.setFileName(configFileParser.getFirstFileFromFolder().getName());

        return completedConfigFileDto;
    }
}
