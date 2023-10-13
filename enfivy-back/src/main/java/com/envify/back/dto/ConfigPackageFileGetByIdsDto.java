package com.envify.back.dto;

import java.util.List;
import java.util.Objects;

public class ConfigPackageFileGetByIdsDto {
    private List<Integer> packageVersionIds;
    public List<Integer> getPackageVersionIds() {
        return packageVersionIds;
    }
    public void setPackageVersionIds(List<Integer> packageVersionIds) {
        this.packageVersionIds = packageVersionIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ConfigPackageFileGetByIdsDto configPackageFileGetByIdsDto = (ConfigPackageFileGetByIdsDto) obj;
        return Objects.equals(packageVersionIds, configPackageFileGetByIdsDto.packageVersionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageVersionIds);
    }
}
