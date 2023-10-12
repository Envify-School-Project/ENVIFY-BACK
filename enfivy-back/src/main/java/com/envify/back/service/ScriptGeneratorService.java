package com.envify.back.service;

import java.io.IOException;
import java.util.List;

import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.exception.EnvifyException;

public interface ScriptGeneratorService {
	
	String readFileAsString(String filePath) throws IOException;
	String buildFilePath(String config, String os) throws IOException;
	String buildFileFooterString(ScriptRequestBodyDto scriptRequestBody) throws IOException;
	String buildFileHeaderString(ScriptRequestBodyDto scriptRequestBody) throws IOException;
	void getScriptCommandAndLabelFromFile(final List<String> scriptLabels, final List<String> scriptCommand, String filePath, String release) throws IOException;
	void fillScriptsLinesList(final List<ScriptDto> scripts, final List<String> scriptLabels,final List<String> scriptCommand);
	List<ScriptDto> buildScripts(List<ScriptRequestBodyDto> scriptRequestBody) throws EnvifyException;
}
