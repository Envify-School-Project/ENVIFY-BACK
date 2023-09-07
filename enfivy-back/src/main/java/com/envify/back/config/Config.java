package com.envify.back.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Value("${envify.api.token.secret}")
	private String tokenSecretKey;
	
	@Value("${envify.api.token.expiration.time}")
	private String tokenExpirationTimeInMs;
	
	@Value("${script.file.path}")
	private String scriptFilePath;
	
	@Value("${envify.api.key.name}")
	private String apiKey;
	
	@Value("${envify.api.key.value}")
	private String apiKeyValue;

	public String getScriptFilePath() {
		return scriptFilePath;
	}
	
	public String getApiKey() {
		return apiKey;
	}

	public String getApiKeyValue() {
		return apiKeyValue;
	}
	
	public String getTokenSecretKey() {
		return tokenSecretKey;
	}
	
	public String getTokenExpirationTimeInMs() {
		return tokenExpirationTimeInMs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiKey, apiKeyValue, scriptFilePath, tokenExpirationTimeInMs, tokenSecretKey);
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
				&& Objects.equals(scriptFilePath, other.scriptFilePath)
				&& Objects.equals(tokenExpirationTimeInMs, other.tokenExpirationTimeInMs)
				&& Objects.equals(tokenSecretKey, other.tokenSecretKey);
	}
}
