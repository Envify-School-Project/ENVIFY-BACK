package com.envify.back.dto;

import java.util.Objects;

public class UserOwnPackageDto {
    private String name;
    private String versionNumber;
    private Integer packageVersionId;

    public UserOwnPackageDto(String name, String versionNumber, Integer packageVersionId) {
        this.name = name;
        this.versionNumber = versionNumber;
        this.packageVersionId = packageVersionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getPackageVersionId() {
        return packageVersionId;
    }

    public void setPackageVersionId(Integer packageVersionId) {
        this.packageVersionId = packageVersionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOwnPackageDto userOwnPackageDto = (UserOwnPackageDto) o;
        return Objects.equals(name, userOwnPackageDto.name)
                && Objects.equals(versionNumber, userOwnPackageDto.versionNumber)
                && Objects.equals(packageVersionId, userOwnPackageDto.packageVersionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, versionNumber, packageVersionId);
    }
}
