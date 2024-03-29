package com.envify.back.dto;

import java.util.Objects;

public class PackageVersionDto {
    private int id;
    private String versionNumber;
    private String url;
    private int versionStatusId;

    private int packageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersionStatusId() {
        return versionStatusId;
    }

    public void setVersionStatusId(int versionStatusId) {
        this.versionStatusId = versionStatusId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageVersionDto that = (PackageVersionDto) o;
        return id == that.id && versionStatusId == that.versionStatusId && packageId == that.packageId && Objects.equals(versionNumber, that.versionNumber) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, versionNumber, url, versionStatusId, packageId);
    }
}
