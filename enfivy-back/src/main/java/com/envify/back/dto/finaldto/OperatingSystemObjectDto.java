package com.envify.back.dto.finaldto;

import java.util.Objects;

public class OperatingSystemObject {
    private String name;
    private int versionId;
    private String versionNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatingSystemObject that = (OperatingSystemObject) o;
        return versionId == that.versionId && Objects.equals(name, that.name) && Objects.equals(versionNumber, that.versionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, versionId, versionNumber);
    }
}
