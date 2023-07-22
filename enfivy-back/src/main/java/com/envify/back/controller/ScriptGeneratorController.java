package com.envify.back.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envify.back.config.Config;
import com.envify.back.config.ScriptRequestBody;
import com.envify.back.service.ScriptGeneratorService;

@RestController
@RequestMapping("/api/v1/scriptGenerator")
public class ScriptGeneratorController {

	@Autowired
	private Config config;
	
	@Autowired
	private ScriptGeneratorService scriptGeneratorService;
	
	@PostMapping("/fileContent")
	public StringBuilder getScriptFileString(@RequestBody ScriptRequestBody scriptRequestBody) throws IOException {
		
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
	
	@GetMapping(value = "/file")
	public @ResponseBody byte[] getFileInbyte() throws IOException {
	    InputStream in = getClass()
	      .getResourceAsStream(config.getScriptFilePath());
	    return IOUtils.toByteArray(in);
	}
}
