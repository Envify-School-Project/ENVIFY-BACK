package com.envify.back.dto.finaldto;

import java.util.List;
import java.util.Objects;

public class FinalObjectDto {
    private String name;
    private String description;
    private OperatingSystemObjectDto os;
    private List<PackageObjectDto> packages;

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

    public OperatingSystemObjectDto getOs() {
        return os;
    }

    public void setOs(OperatingSystemObjectDto os) {
        this.os = os;
    }

    public List<PackageObjectDto> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageObjectDto> packages) {
        this.packages = packages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalObjectDto that = (FinalObjectDto) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(os, that.os) && Objects.equals(packages, that.packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, os, packages);
    }
}
