package com.envify.back.dto;

import java.util.Objects;

public class ScriptDto {

	private String scriptLabel;
	private String script;
	
	public String getScriptLabel() {
		return scriptLabel;
	}
	public void setScriptLabel(String scriptLabel) {
		this.scriptLabel = scriptLabel;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(script, scriptLabel);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScriptDto other = (ScriptDto) obj;
		return Objects.equals(script, other.script) && Objects.equals(scriptLabel, other.scriptLabel);
	}
}
