package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "operating_system_versions")
public class OperatingSystemVersionEntity {
    private int id;
    private String versionNumber;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, operatingSystemId, versionNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatingSystemVersionEntity other = (OperatingSystemVersionEntity) obj;
		return id == other.id && operatingSystemId == other.operatingSystemId
				&& Objects.equals(versionNumber, other.versionNumber);
	}

    
    
}
