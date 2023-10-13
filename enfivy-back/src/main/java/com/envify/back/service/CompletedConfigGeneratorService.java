package com.envify.back.service;

import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.dto.ReceivedConfigObjectDto;
import com.envify.back.dto.CompletedConfigFileDto;
import com.envify.back.dto.CompletedConfigDto;
import com.envify.back.dto.ReceivedPackageDto;
import com.envify.back.entity.ConfigEntity;
import com.envify.back.exception.EnvifyException;
import com.envify.back.service.configFileParser.ConfigFileParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public interface CompletedConfigGeneratorService {
    CompletedConfigDto generateCompletedConfig(ReceivedConfigObjectDto receivedConfigObjectDto, HttpServletRequest request) throws EnvifyException, IOException ;
    ConfigEntity generateNewConfig(ReceivedConfigObjectDto receivedConfigObjectDto, HttpServletRequest request);
    ScriptRequestBodyDto generateScriptBodyRequestDto(ReceivedConfigObjectDto receivedConfigObjectDto, ReceivedPackageDto receivedPackageDto);
    CompletedConfigFileDto generateFinalResponseConfigFileDto(ConfigFileParser configFileParser) throws IOException;
}
