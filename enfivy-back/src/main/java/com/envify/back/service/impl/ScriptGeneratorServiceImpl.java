package com.envify.back.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.envify.back.config.ScriptRequestBody;
import com.envify.back.service.ScriptGeneratorService;

@Service
public class ScriptGeneratorServiceImpl implements ScriptGeneratorService {

	@Value("${script.file.path}")
	private String scriptFilePath;
	
	public String readFileAsString(String filePath) throws IOException {
	        Path path = Paths.get(filePath);
	        byte[] bytes = Files.readAllBytes(path);
	        return new String(bytes);
    	}
	
	
	public String buildFilePath(String config) {
		StringBuilder filePath = new StringBuilder(scriptFilePath + config);
		filePath.append("_script.sh");
		return filePath.toString();
	}
	
	public String buildFileFooterString() throws IOException {
		String fileFooterPath = buildFilePath("footer");
		return readFileAsString(fileFooterPath);
	}

	public String buildFileHeaderString(ScriptRequestBody scriptRequestBody) throws IOException {
		String fileHeaderPath = buildFilePath("header");
		String fileHeaderContent = readFileAsString(fileHeaderPath);
		
		fileHeaderContent = fileHeaderContent.replace("$package", scriptRequestBody.getConfig());
		fileHeaderContent = fileHeaderContent.replace("$version", scriptRequestBody.getRelease());
		return fileHeaderContent;
	}
	
}
