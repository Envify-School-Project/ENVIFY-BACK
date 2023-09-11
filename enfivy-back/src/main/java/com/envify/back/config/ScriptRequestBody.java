package com.envify.back.config;

import java.util.Objects;

public class ScriptRequestBody {
	
	private String config;
	private String release;
	
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(config, release);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScriptRequestBody other = (ScriptRequestBody) obj;
		return Objects.equals(config, other.config) && Objects.equals(release, other.release);
	}
}
