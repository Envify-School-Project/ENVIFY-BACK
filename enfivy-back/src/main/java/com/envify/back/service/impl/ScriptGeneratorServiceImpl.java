package com.envify.back.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.envify.back.exception.EnvifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.envify.back.dto.ScriptDto;
import com.envify.back.dto.ScriptRequestBodyDto;
import com.envify.back.service.ScriptGeneratorService;

@Service
public class ScriptGeneratorServiceImpl implements ScriptGeneratorService {

	private static final String SCRIPT_SH = "_script.sh";
	private static final String FOOTER = "footer";
	private static final String PACKAGE = "$package";
	private static final String ECHO = "echo";
	private static final String VERSION = "$version";

	@Autowired
	private ResourceLoader resourceLoader;

	public String readFileAsString(String filePath) throws IOException {
		
		Resource resource = new ClassPathResource("classpath:" + filePath);
		InputStream inputStream = resource.getInputStream(); 
		byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		
//		Path path = Paths.get(filePath);
//		byte[] bytes = Files.readAllBytes(path);
//		return new String(bytes);
		return new String(bdata);
	}

	public String buildFilePath(String config, String os) throws IOException {

		StringBuilder fileName = new StringBuilder();
		fileName.append(os);
		fileName.append("_");
		fileName.append(config);
		fileName.append(SCRIPT_SH);

//		StringBuilder path = new StringBuilder("classpath:");
//		path.append(fileName);
//		Resource resource = resourceLoader.getResource(path.toString());

//		return resource.getFile().getAbsolutePath();
		return fileName.toString();
	}

	public String buildFileFooterString(ScriptRequestBodyDto scriptRequestBody) throws IOException {
		String fileFooterPath = buildFilePath(FOOTER, scriptRequestBody.getOs());

		return readFileAsString(fileFooterPath);
	}

	public String buildFileHeaderString(ScriptRequestBodyDto scriptRequestBody) throws IOException {
		String fileHeaderPath = buildFilePath("header", scriptRequestBody.getOs());
		String fileHeaderContent = readFileAsString(fileHeaderPath);

		fileHeaderContent = fileHeaderContent.replace(PACKAGE, scriptRequestBody.getConfig());
		fileHeaderContent = fileHeaderContent.replace(VERSION, scriptRequestBody.getRelease());
		return fileHeaderContent;
	}

	public void getScriptCommandAndLabelFromFile(final List<String> scriptLabels, final List<String> scriptCommand,
			String filePath, String release) throws IOException {

		Resource resource = new ClassPathResource("classpath:" + filePath);

		InputStream inputStream = resource.getInputStream();
		
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = reader.readLine();
			while (line != null) {
				if (line.contains(ECHO)) {
					String label = line.substring(5);
					scriptLabels.add(label.substring(1, label.length() - 1).replace("-", ""));
				}
				
				if (line.contains(VERSION)) {
					line = line.replace(VERSION, release);
				}
				
				if (!line.isBlank() && !line.contains(ECHO)) {
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
		
		while (scriptIterator.hasNext()) {
			final ScriptDto scriptTmp = new ScriptDto();

			scriptTmp.setScript(scriptCommand.get(i));
			scriptTmp.setScriptLabel(scriptLabels.get(i));

			scripts.add(scriptTmp);

			i++;
			scriptIterator.next();
		}
	}

	public List<ScriptDto> buildScripts(List<ScriptRequestBodyDto> scriptRequestBody) throws EnvifyException, IOException {
		final List<ScriptDto> scripts = new ArrayList<>();
		final List<String> scriptLabels = new ArrayList<>();
		final List<String> scriptCommand = new ArrayList<>();


		for (ScriptRequestBodyDto packageChoose : scriptRequestBody) {
			String filePath = buildFilePath(packageChoose.getConfig().toLowerCase(), packageChoose.getOs().toLowerCase());

			getScriptCommandAndLabelFromFile(scriptLabels, scriptCommand, filePath, packageChoose.getRelease().toLowerCase());

			if (scriptCommand.size() != scriptLabels.size()) {
				throw new EnvifyException("Script Templating error might be considering");
			}

			fillScriptsLinesList(scripts, scriptLabels, scriptCommand);
		}

		return scripts;
	}
}
