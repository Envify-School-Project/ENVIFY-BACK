package com.envify.back.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ConfigPackageIdEntity implements Serializable {
	
	private int configId;
    private int packageVersionId;
    
    
    
	public int getConfigId() {
		return configId;
	}

	public int getPackageVersionId() {
		return packageVersionId;
	}

	public ConfigPackageIdEntity() {
		
	}
	
	public ConfigPackageIdEntity(int configId, int packageVersionId) {
		super();
		this.configId = configId;
		this.packageVersionId = packageVersionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(configId, packageVersionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigPackageIdEntity other = (ConfigPackageIdEntity) obj;
		return configId == other.configId && packageVersionId == other.packageVersionId;
	}
}
