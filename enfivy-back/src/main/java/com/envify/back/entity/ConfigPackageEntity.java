package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "config_packages")
public class ConfigPackageEntity {
    private int config_id;
    private int package_version_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id", nullable = false)
    public int getConfigId() {
        return config_id;
    }

    public void setConfigId(int id) {
        this.config_id = id;
    }

    @Column(name = "package_version_id", nullable = false)
    public int getPackageVersionId() {
        return package_version_id;
    }

    public void setPackageVersionId(int package_version_id) {
        this.package_version_id = package_version_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigPackageEntity that = (ConfigPackageEntity) o;
        return config_id == that.config_id && Objects.equals(package_version_id, that.package_version_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(config_id, package_version_id);
    }
}
