package com.envify.back.dto;

import java.util.Objects;

public class ConfigPackageFileDto {
    private int id;
    private String description;
    private String properties;
    private int packageVersionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public int getPackageVersionId() {
        return packageVersionId;
    }

    public void setPackageVersionId(int packageVersionId) {
        this.packageVersionId = packageVersionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigPackageFileDto that = (ConfigPackageFileDto) o;
        return id == that.id && packageVersionId == that.packageVersionId && Objects.equals(description, that.description) && Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, properties, packageVersionId);
    }
}
