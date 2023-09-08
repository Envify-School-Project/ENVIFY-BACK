package com.envify.back.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Value("${script.file.path}")
	private String scriptFilePath;
	
	@Value("${envify.api.key.name}")
	private String apiKey;
	
	@Value("${envify.api.key.value}")
	private String apiKeyValue;

	public String getScriptFilePath() {
		return scriptFilePath;
	}
	public void setScriptFilePath(String scriptFilePath) {
		this.scriptFilePath = scriptFilePath;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKeyValue() {
		return apiKeyValue;
	}
	public void setApiKeyValue(String apiKeyValue) {
		this.apiKeyValue = apiKeyValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiKey, apiKeyValue, scriptFilePath);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		return Objects.equals(apiKey, other.apiKey) && Objects.equals(apiKeyValue, other.apiKeyValue)
				&& Objects.equals(scriptFilePath, other.scriptFilePath);
	}
}
