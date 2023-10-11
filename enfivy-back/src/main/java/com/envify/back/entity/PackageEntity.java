package com.envify.back.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "packages")
public class PackageEntity {
    private int id;
    private String name;
    private List<PackageVersionEntity> versions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "packageId", cascade = CascadeType.ALL)
    public List<PackageVersionEntity> getPackageVersions() {
        return versions;
    }

    public void setPackageVersions(List<PackageVersionEntity> versions) {
        this.versions = versions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageEntity that = (PackageEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(versions, that.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, versions);
    }
}
