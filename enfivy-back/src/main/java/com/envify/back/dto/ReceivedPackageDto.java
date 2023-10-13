package com.envify.back.dto;

import java.util.List;
import java.util.Objects;

public class ReceivedPackageDto {
    private int packageId;
    private String name;
    private int versionId;
    private String versionNumber;
    private List<ReceivedPackagePropertiesDto> packageProperties;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public List<ReceivedPackagePropertiesDto> getPackageProperties() {
        return packageProperties;
    }

    public void setPackageProperties(List<ReceivedPackagePropertiesDto> packageProperties) {
        this.packageProperties = packageProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedPackageDto that = (ReceivedPackageDto) o;
        return packageId == that.packageId && versionId == that.versionId && Objects.equals(name, that.name) && Objects.equals(versionNumber, that.versionNumber) && Objects.equals(packageProperties, that.packageProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, name, versionId, versionNumber, packageProperties);
    }
}
