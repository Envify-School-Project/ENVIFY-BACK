package com.envify.back.service;

import java.io.IOException;

import com.envify.back.config.ScriptRequestBody;

public interface ScriptGeneratorService {
	
	String readFileAsString(String filePath) throws IOException;
	String buildFilePath(String config);
	String buildFileFooterString() throws IOException;
	String buildFileHeaderString(ScriptRequestBody scriptRequestBody) throws IOException;
}
