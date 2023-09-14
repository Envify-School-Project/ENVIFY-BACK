package com.envify.back.service;

import java.io.IOException;
import java.util.List;

import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;

public interface ScriptGeneratorService {
	
	String readFileAsString(String filePath) throws IOException;
	String buildFilePath(String config);
	String buildFileFooterString() throws IOException;
	String buildFileHeaderString(ScriptRequestBodyDto scriptRequestBody) throws IOException;
	void getScriptCommandAndLabelFromFile(final List<String> scriptLabels, final List<String> scriptCommand, String filePath);
	void fillScriptsLinesList(final List<ScriptDto> scripts, final List<String> scriptLabels,final List<String> scriptCommand);
}
