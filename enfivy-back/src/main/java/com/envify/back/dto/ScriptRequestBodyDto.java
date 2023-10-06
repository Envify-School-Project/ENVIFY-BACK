package com.envify.back.dto;

import java.util.Objects;

public class ScriptRequestBodyDto {
	
	private String config;
	private String release;
	private String os;
	
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
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(config, os, release);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScriptRequestBodyDto other = (ScriptRequestBodyDto) obj;
		return Objects.equals(config, other.config) && Objects.equals(os, other.os)
				&& Objects.equals(release, other.release);
	}
}
