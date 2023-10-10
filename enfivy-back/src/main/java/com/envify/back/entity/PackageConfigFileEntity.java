package com.envify.back.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "package_config_files")
public class PackageConfigFileEntity {
	
	private int id;
	private String description;
	private String properties;
	private int packageVersionId;
	private String scriptColumn;
	
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
	
	public String getScriptColumn() {
		return scriptColumn;
	}
	public void setScriptColumn(String scriptColumn) {
		this.scriptColumn = scriptColumn;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(description, id, packageVersionId, properties, scriptColumn);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageConfigFileEntity other = (PackageConfigFileEntity) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& packageVersionId == other.packageVersionId && Objects.equals(properties, other.properties)
				&& Objects.equals(scriptColumn, other.scriptColumn);
	}

}
