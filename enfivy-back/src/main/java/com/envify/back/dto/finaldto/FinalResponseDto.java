package com.envify.back.dto.finaldto;

import com.envify.back.dto.ScriptDto;

import java.util.List;
import java.util.Objects;

public class FinalResponseDto {
    private List<ScriptDto> scripts;
    private List<FinalResponseConfigFileDto> configFiles;

    public FinalResponseDto(List<ScriptDto> scripts, List<FinalResponseConfigFileDto> configFiles) {
        this.scripts = scripts;
        this.configFiles = configFiles;
    }

    public List<ScriptDto> getScripts() {
        return scripts;
    }

    public void setScripts(List<ScriptDto> scripts) {
        this.scripts = scripts;
    }

    public List<FinalResponseConfigFileDto> getConfigFiles() {
        return configFiles;
    }

    public void setConfigFiles(List<FinalResponseConfigFileDto> configFiles) {
        this.configFiles = configFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalResponseDto that = (FinalResponseDto) o;
        return Objects.equals(scripts, that.scripts) && Objects.equals(configFiles, that.configFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scripts, configFiles);
    }
}
