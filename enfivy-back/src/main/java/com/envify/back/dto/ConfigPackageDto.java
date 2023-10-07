package com.envify.back.dto;

import java.util.Objects;

public class ConfigPackageDto {
    private int configId;
    private int packageVersionId;
    private String configurationScripts;

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getPackageVersionId() {
        return packageVersionId;
    }

    public void setPackageVersionId(int packageVersionId) {
        this.packageVersionId = packageVersionId;
    }

    public String getConfigurationScripts() {
        return configurationScripts;
    }

    public void setConfigurationScripts(String configurationScripts) {
        this.configurationScripts = configurationScripts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigPackageDto that = (ConfigPackageDto) o;
        return configId == that.configId && packageVersionId == that.packageVersionId && Objects.equals(configurationScripts, that.configurationScripts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configId, packageVersionId, configurationScripts);
    }
}
