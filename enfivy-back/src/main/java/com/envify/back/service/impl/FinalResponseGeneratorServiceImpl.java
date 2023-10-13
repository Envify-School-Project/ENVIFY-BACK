package com.envify.back.service.impl;

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
import com.envify.back.service.ConfigPackageService;
import com.envify.back.service.FinalResponseGeneratorService;
import com.envify.back.service.ScriptGeneratorService;
import com.envify.back.service.configFileParser.ConfigFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinalResponseGeneratorServiceImpl implements FinalResponseGeneratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FinalResponseGeneratorServiceImpl.class);
	
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private ConfigServiceImpl configService;
    @Autowired
    private ConfigPackageService configPackageService;
    @Autowired
    private ScriptGeneratorService scriptGeneratorService;
    
    @Override
    public FinalResponseDto generateCompletedConfig(FinalObjectDto finalObjectDto, HttpServletRequest request) throws EnvifyException, IOException {
        ConfigEntity configEntity = generateNewConfig(finalObjectDto, request);

        List<ScriptRequestBodyDto> scriptRequestBody = new ArrayList<>();
        List<FinalResponseConfigFileDto> configFiles = new ArrayList<>();

        for (PackageObjectDto packageObjectDto: finalObjectDto.getPackages()) {
            ConfigPackageEntity configPackageEntity = new ConfigPackageEntity();

            ScriptRequestBodyDto scriptRequestBodyDto = generateScriptBodyRequestDto(finalObjectDto, packageObjectDto);

            configPackageEntity.setConfigPackageId(new ConfigPackageIdEntity(configEntity.getId(), packageObjectDto.getVersionId()));

            if (packageObjectDto.getPackageProperties().size() != 0) {
                ConfigFileParser configFileParser = new ConfigFileParser(packageObjectDto.getName(), packageObjectDto);
                configPackageEntity.setConfigurationScripts(configFileParser.parseFile());

                FinalResponseConfigFileDto finalResponseConfigFileDto = generateFinalResponseConfigFileDto(configFileParser);
                configFiles.add(finalResponseConfigFileDto);
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

        FinalResponseDto finalResponseDto = new FinalResponseDto(scripts, configFiles);

        return finalResponseDto;
    }

    public ConfigEntity generateNewConfig(FinalObjectDto finalObjectDto, HttpServletRequest request) {
        ConfigEntity configEntity = new ConfigEntity();
//        String accessToken = jwtUtil.resolveToken(request);
//        Integer userId = jwtUtil.getAllClaimsFromToken(accessToken).get("id", Integer.class);

        configEntity.setName(finalObjectDto.getName());
        configEntity.setOperatingSystemId(finalObjectDto.getOs().getVersionId());
        configEntity.setDescription(finalObjectDto.getDescription());
//        configEntity.setUserId(userId);
        configEntity.setUserId(1);

        try {
            configService.saveConfig(configEntity);
            System.out.println("Config correctly saved id is : " + configEntity.getId());
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        return configEntity;
    }

    public ScriptRequestBodyDto generateScriptBodyRequestDto(FinalObjectDto finalObjectDto, PackageObjectDto packageObjectDto) {
        ScriptRequestBodyDto scriptRequestBodyDto = new ScriptRequestBodyDto();

        scriptRequestBodyDto.setConfig(packageObjectDto.getName());
        scriptRequestBodyDto.setRelease(packageObjectDto.getVersionNumber());
        scriptRequestBodyDto.setOs(finalObjectDto.getOs().getName());

        return scriptRequestBodyDto;
    }

    public FinalResponseConfigFileDto generateFinalResponseConfigFileDto(ConfigFileParser configFileParser) throws IOException {
        FinalResponseConfigFileDto finalResponseConfigFileDto = new FinalResponseConfigFileDto();

        finalResponseConfigFileDto.setFile(configFileParser.parseFile());
        finalResponseConfigFileDto.setFileName(configFileParser.getFirstFileFromFolder().getName());

        return finalResponseConfigFileDto;
    }
}
