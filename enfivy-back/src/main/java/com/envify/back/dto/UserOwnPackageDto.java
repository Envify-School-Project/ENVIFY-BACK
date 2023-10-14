package com.envify.back.dto;

import java.util.Objects;

public class UserOwnPackageDto {
    private String name;
    private String versionNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOwnPackageDto userOwnPackageDto = (UserOwnPackageDto) o;
        return Objects.equals(name, userOwnPackageDto.name) && Objects.equals(versionNumber, userOwnPackageDto.versionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, versionNumber);
    }
}
