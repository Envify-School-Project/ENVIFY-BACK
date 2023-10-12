package com.envify.back.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "configs")
public class ConfigEntity {
	private int id;
	private int userId;
	private String name; 
	private String description;
	private int operatingSystemId;
	private List<PackageEntity> packages;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "operating_system_id", nullable = false)
	public int getOperatingSystemId() {
		return operatingSystemId;
	}

	public void setOperatingSystemId(int operatingSystemId) {
		this.operatingSystemId = operatingSystemId;
	}

	@Query("""
		SELECT p
		FROM PackageEntity p
		LEFT JOIN PackageVersionEntity pv ON p.id = pv.packageId
		LEFT JOIN ConfigPackageIdEntity cpi ON pv.id = cpi.packageVersionId
		WHERE cpi.configId = :configId
	""")
	public List<PackageEntity> getPackages(@Param("configId") int configId) {
		return packages;
	}

	public void setPackages(List<PackageEntity> packages) {
		this.packages = packages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConfigEntity that = (ConfigEntity) o;
		return id == that.id
				&& userId == that.userId
				&& operatingSystemId == that.operatingSystemId
				&& Objects.equals(name, that.name)
				&& Objects.equals(description, that.description)
				&& Objects.equals(packages, that.packages);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, name, description, operatingSystemId);
	}
}
