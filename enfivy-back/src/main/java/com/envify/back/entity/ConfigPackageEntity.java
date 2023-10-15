package com.envify.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "config_packages")
public class ConfigPackageEntity {

    private String configurationScripts;

	@EmbeddedId
    private ConfigPackageIdEntity configPackageId;

	private Timestamp createdAt;
	private Timestamp updatedAt;

	public ConfigPackageIdEntity getConfigPackageId() {
		return configPackageId;
	}

	public void setConfigPackageId(ConfigPackageIdEntity configPackageId) {
		this.configPackageId = configPackageId;
	}

    @Column(name = "configuration_scripts")
    public String getConfigurationScripts() {
        return configurationScripts;
    }

    public void setConfigurationScripts(String configurationScripts) {
        this.configurationScripts = configurationScripts;
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
		ConfigPackageEntity that = (ConfigPackageEntity) o;
		return Objects.equals(configurationScripts, that.configurationScripts) && Objects.equals(configPackageId, that.configPackageId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(configurationScripts, configPackageId, createdAt, updatedAt);
	}
}
