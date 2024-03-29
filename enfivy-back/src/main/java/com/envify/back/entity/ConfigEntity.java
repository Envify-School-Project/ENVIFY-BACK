package com.envify.back.entity;

import com.envify.back.dto.UserOwnPackageDto;

import javax.persistence.*;
import java.sql.Timestamp;
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
	private String operatingSystemName;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private List<UserOwnPackageDto> packages;

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

	@Transient
	public String getOperatingSystemName() {
		return operatingSystemName;
	}

	public void setOperatingSystemName(String operatingSystemName) {
		this.operatingSystemName = operatingSystemName;
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


	@Transient
	public List<UserOwnPackageDto> getPackages() {
		return packages;
	}

	public void setPackages(List<UserOwnPackageDto> packages) {
		this.packages = packages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConfigEntity that = (ConfigEntity) o;
		return id == that.id && userId == that.userId && operatingSystemId == that.operatingSystemId && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, name, description, operatingSystemId, createdAt, updatedAt);
	}
}
