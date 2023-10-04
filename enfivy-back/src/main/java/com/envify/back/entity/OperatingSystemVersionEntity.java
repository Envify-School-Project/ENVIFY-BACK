package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "operating_system_versions")
public class OperatingSystemVersionEntity {
    private int id;
    private String version_number;
    private int operatingSystemId;

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
    public String getVersion_number() {
        return version_number;
    }

    public void setVersionNumber(String version_number) {
        this.version_number = version_number;
    }

    @Column(name = "operating_system_id")
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
        OperatingSystemVersionEntity that = (OperatingSystemVersionEntity) o;
        return id == that.id && operatingSystemId == that.operatingSystemId && Objects.equals(version_number, that.version_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version_number, operatingSystemId);
    }
}
