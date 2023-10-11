package com.envify.back.dto.finaldto;

import java.util.List;
import java.util.Objects;

public class PackageObject {
    private int id;
    private String name;
    private int versionId;
    private String versionNumber;
    private List<PackagePropertiesObject> packageProperties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<PackagePropertiesObject> getPackageProperties() {
        return packageProperties;
    }

    public void setPackageProperties(List<PackagePropertiesObject> packageProperties) {
        this.packageProperties = packageProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageObject that = (PackageObject) o;
        return id == that.id && versionId == that.versionId && Objects.equals(name, that.name) && Objects.equals(versionNumber, that.versionNumber) && Objects.equals(packageProperties, that.packageProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, versionId, versionNumber, packageProperties);
    }
}
