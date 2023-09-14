package com.envify.back.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.config.Config;
import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.exception.EnvifyException;
import com.envify.back.service.ScriptGeneratorService;

/**
 * @author semfa
 *
 */
@RestController
@RequestMapping("/api/v1/scriptGenerator")
public class ScriptGeneratorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptGeneratorController.class);

	@Autowired
	private Config config;
	
	@Autowired
	private ScriptGeneratorService scriptGeneratorService;
	
	@PostMapping("/fileContent")
	public StringBuilder getScriptFileString(@RequestBody ScriptRequestBodyDto scriptRequestBody) throws IOException {
		
		StringBuilder scriptCode = new StringBuilder();
		
		// get script header
		String fileHeaderContent = scriptGeneratorService.buildFileHeaderString(scriptRequestBody);
		
		// get script footer
		String fileFooterContent = scriptGeneratorService.buildFileFooterString();
		
		// get script content
		String filePath = scriptGeneratorService.buildFilePath(scriptRequestBody.getConfig());
		String fileContent = scriptGeneratorService.readFileAsString(filePath);
		
		// concat string
		scriptCode.append(fileHeaderContent);
		scriptCode.append(fileContent);
		scriptCode.append(fileFooterContent);
		
		return scriptCode;
	}	
	
	@PostMapping("/script/lines")
	public List<ScriptDto> getScriptLines(@RequestBody ScriptRequestBodyDto scriptRequestBody) throws EnvifyException {
		final List<ScriptDto> scripts = new ArrayList<>();
		final List<String> scriptLabels = new ArrayList<>();
		final List<String> scriptCommand = new ArrayList<>();
		
		// get script path
		String filePath = scriptGeneratorService.buildFilePath(scriptRequestBody.getConfig());
		
		scriptGeneratorService.getScriptCommandAndLabelFromFile(scriptLabels, scriptCommand, filePath);
		
		if(scriptCommand.size() == scriptLabels.size()) {
			scriptGeneratorService.fillScriptsLinesList(scripts, scriptLabels, scriptCommand);
		} else {
			throw new EnvifyException("Script Templating error might be considering");
		}
		
		return scripts;
	}	
	
	@GetMapping(value = "/file/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody ResponseEntity<byte[]> getFile(@RequestBody ScriptRequestBodyDto scriptRequestBody) throws IOException {
		Path file = Paths.get(config.getScriptFilePath() + scriptRequestBody.getConfig());
        byte[] scriptFileInBytes = Files.readAllBytes(file);
        
        final org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachement; filename=script.sh");
        responseHeaders.set("Content-Lenght", String.valueOf(scriptFileInBytes.length));
        
        return ResponseEntity.ok().headers(responseHeaders).body(scriptFileInBytes);
    }
}
