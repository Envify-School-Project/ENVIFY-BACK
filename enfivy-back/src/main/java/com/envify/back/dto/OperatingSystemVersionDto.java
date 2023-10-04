package com.envify.back.dto;

import java.util.Objects;

public class OperatingSystemVersionDto {
    private int id;
    private String version_number;
    private int operatingSystemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return version_number;
    }

    public void setVersionNmber(String version_number) {
        this.version_number = version_number;
    }

    public int getOperatingSystemId() {
        return operatingSystemId;
    }

    public void setOperatingSystemId(int operatingSystemId) {
        this.operatingSystemId = operatingSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatingSystemVersionDto that = (OperatingSystemVersionDto) o;
        return id == that.id && operatingSystemId == that.operatingSystemId && Objects.equals(version_number, that.version_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version_number, operatingSystemId);
    }
}
