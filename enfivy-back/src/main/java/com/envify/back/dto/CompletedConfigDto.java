package com.envify.back.dto;

import java.util.List;
import java.util.Objects;

public class CompletedConfigDto {
    private List<ScriptDto> scripts;
    private List<CompletedConfigFileDto> configFiles;

    public CompletedConfigDto(List<ScriptDto> scripts, List<CompletedConfigFileDto> configFiles) {
        this.scripts = scripts;
        this.configFiles = configFiles;
    }

    public List<ScriptDto> getScripts() {
        return scripts;
    }

    public void setScripts(List<ScriptDto> scripts) {
        this.scripts = scripts;
    }

    public List<CompletedConfigFileDto> getConfigFiles() {
        return configFiles;
    }

    public void setConfigFiles(List<CompletedConfigFileDto> configFiles) {
        this.configFiles = configFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedConfigDto that = (CompletedConfigDto) o;
        return Objects.equals(scripts, that.scripts) && Objects.equals(configFiles, that.configFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scripts, configFiles);
    }
}
