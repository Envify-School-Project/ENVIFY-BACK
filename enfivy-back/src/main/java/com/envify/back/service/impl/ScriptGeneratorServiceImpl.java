package com.envify.back.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;
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

	public String buildFileHeaderString(ScriptRequestBodyDto scriptRequestBody) throws IOException {
		String fileHeaderPath = buildFilePath("header");
		String fileHeaderContent = readFileAsString(fileHeaderPath);
		
		fileHeaderContent = fileHeaderContent.replace("$package", scriptRequestBody.getConfig());
		fileHeaderContent = fileHeaderContent.replace("$version", scriptRequestBody.getRelease());
		return fileHeaderContent;
	}
	
	public void getScriptCommandAndLabelFromFile(final List<String> scriptLabels, final List<String> scriptCommand,
			String filePath) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line = reader.readLine();
			while (line != null) {
				if(line.contains("echo")) {
					String label = line.substring(5);
					scriptLabels.add(label.substring(1, label.length() - 1).replace("-", ""));
				}
				
				if(!line.isBlank() && !line.contains("echo")) {
					scriptCommand.add(line);
				}
				
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fillScriptsLinesList(final List<ScriptDto> scripts, final List<String> scriptLabels,
			final List<String> scriptCommand) {
		int i = 0;
		
		Iterator<String> scriptIterator = scriptCommand.iterator();
		
		while(scriptIterator.hasNext()) {
			final ScriptDto scriptTmp = new ScriptDto();
			
			scriptTmp.setScript(scriptCommand.get(i));
			scriptTmp.setScriptLabel(scriptLabels.get(i));
			
			scripts.add(scriptTmp);
			
			i++;
			scriptIterator.next();
		}
	}
	
}
