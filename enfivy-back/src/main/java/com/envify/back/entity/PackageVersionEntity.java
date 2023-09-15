package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "package_versions")
public class PackageVersionEntity {
    private int id;
    private String versionNumber;
    private String url;
    private int versionStatusId;
    private int packageId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "version_number", nullable = false)
    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Column(name = "url", nullable = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "version_status_id", nullable = false)
    public int getVersionStatusId() {
        return versionStatusId;
    }

    public void setVersionStatusId(int versionStatusId) {
        this.versionStatusId = versionStatusId;
    }

    @Column(name = "package_id", nullable = false)
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
        PackageVersionEntity that = (PackageVersionEntity) o;
        return id == that.id && versionStatusId == that.versionStatusId && packageId == that.packageId && Objects.equals(versionNumber, that.versionNumber) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, versionNumber, url, versionStatusId, packageId);
    }
}
