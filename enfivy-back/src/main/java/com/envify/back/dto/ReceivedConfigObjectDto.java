package com.envify.back.dto;

import java.util.List;
import java.util.Objects;

public class ReceivedConfigObjectDto {
    private String name;
    private String description;
    private ReceivedOperatingSystemDto os;
    private List<ReceivedPackageDto> packages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReceivedOperatingSystemDto getOs() {
        return os;
    }

    public void setOs(ReceivedOperatingSystemDto os) {
        this.os = os;
    }

    public List<ReceivedPackageDto> getPackages() {
        return packages;
    }

    public void setPackages(List<ReceivedPackageDto> packages) {
        this.packages = packages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedConfigObjectDto that = (ReceivedConfigObjectDto) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(os, that.os) && Objects.equals(packages, that.packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, os, packages);
    }
}
