package com.envify.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "operating_system_versions")
public class OperatingSystemVersionEntity {
    private int id;
    private String versionNumber;
    private int operatingSystemId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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

    @Column(name = "operating_system_id")
    public int getOperatingSystemId() {
        return operatingSystemId;
    }

    public void setOperatingSystemId(int operatingSystemId) {
        this.operatingSystemId = operatingSystemId;
    }

    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatingSystemVersionEntity that = (OperatingSystemVersionEntity) o;
        return id == that.id && operatingSystemId == that.operatingSystemId && Objects.equals(versionNumber, that.versionNumber) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, versionNumber, operatingSystemId, createdAt, updatedAt);
    }
}
