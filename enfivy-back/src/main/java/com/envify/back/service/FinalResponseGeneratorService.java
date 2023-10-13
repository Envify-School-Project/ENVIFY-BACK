package com.envify.back.service;

import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.dto.finaldto.FinalObjectDto;
import com.envify.back.dto.finaldto.FinalResponseConfigFileDto;
import com.envify.back.dto.finaldto.FinalResponseDto;
import com.envify.back.dto.finaldto.PackageObjectDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.entity.ConfigPackageEntity;
import com.envify.back.exception.EnvifyException;
import com.envify.back.service.configFileParser.ConfigFileParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public interface FinalResponseGeneratorService {
    FinalResponseDto generateCompletedConfig(FinalObjectDto finalObjectDto, HttpServletRequest request) throws EnvifyException, IOException ;
    ConfigEntity generateNewConfig(FinalObjectDto finalObjectDto, HttpServletRequest request);
    ScriptRequestBodyDto generateScriptBodyRequestDto(FinalObjectDto finalObjectDto, PackageObjectDto packageObjectDto);
    FinalResponseConfigFileDto generateFinalResponseConfigFileDto(ConfigFileParser configFileParser) throws IOException;
}
