package com.envify.back.dto.finaldto;

import java.util.List;
import java.util.Objects;

public class FinalObject {
    private String name;
    private OperatingSystemObject operatingSystem;
    private List<PackageObject> packages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperatingSystemObject getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystemObject operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public List<PackageObject> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageObject> packages) {
        this.packages = packages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalObject that = (FinalObject) o;
        return Objects.equals(name, that.name) && Objects.equals(operatingSystem, that.operatingSystem) && Objects.equals(packages, that.packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operatingSystem, packages);
    }
}
