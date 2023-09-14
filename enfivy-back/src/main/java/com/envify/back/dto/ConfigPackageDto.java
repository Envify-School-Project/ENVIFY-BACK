package com.envify.back.dto;

import java.util.Objects;

public class ConfigPackageDto {
    private int config_id;
    private int package_version_id;

    public int getConfigId() {
        return config_id;
    }

    public void setConfigId(int config_id) {
        this.config_id = config_id;
    }

    public int getPackageVersionId() {
        return package_version_id;
    }

    public void setPackageVersionId(int package_version_id) {
        this.package_version_id = package_version_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(config_id, package_version_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigPackageDto that = (ConfigPackageDto) o;
        return config_id == that.config_id && Objects.equals(package_version_id, that.package_version_id);
    }
}
