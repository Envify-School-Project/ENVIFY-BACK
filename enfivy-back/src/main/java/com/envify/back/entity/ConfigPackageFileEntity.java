package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "package_config_files")
public class ConfigPackageFileEntity {
    private int id;
    private String description;
    private String properties;
    private int packageVersionId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "properties")
    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Column(name = "package_version_id")
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
        ConfigPackageFileEntity that = (ConfigPackageFileEntity) o;
        return id == that.id && packageVersionId == that.packageVersionId && Objects.equals(description, that.description) && Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, properties, packageVersionId);
    }
}
