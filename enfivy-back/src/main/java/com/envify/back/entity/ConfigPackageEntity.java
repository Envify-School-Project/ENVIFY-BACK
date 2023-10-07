package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "config_packages")
public class ConfigPackageEntity {

    private String configurationScripts;
    
    @EmbeddedId
    private ConfigPackageIdEntity configPackageId;

    @Column(name = "configuration_scripts")
    public String getConfigurationScripts() {
        return configurationScripts;
    }

    public void setConfigurationScripts(String configurationScripts) {
        this.configurationScripts = configurationScripts;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigPackageEntity other = (ConfigPackageEntity) obj;
		return Objects.equals(configPackageId, other.configPackageId)
				&& Objects.equals(configurationScripts, other.configurationScripts);
	}

    @Override
	public int hashCode() {
		return Objects.hash(configPackageId, configurationScripts);
	}
}
