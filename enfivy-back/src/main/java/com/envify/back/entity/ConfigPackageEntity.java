package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "config_packages")
public class ConfigPackageEntity {
    private int configId;
    private int packageVersionId;
    private String configurationScripts;

    @Column(name = "config_id")
    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    @Column(name = "package_version_id")
    public int getPackageVersionId() {
        return packageVersionId;
    }

    public void setPackageVersionId(int packageVersionId) {
        this.packageVersionId = packageVersionId;
    }

    @Column(name = "configuration_scripts")
    public String getConfigurationScripts() {
        return configurationScripts;
    }

    public void setConfigurationScripts(String configurationScripts) {
        this.configurationScripts = configurationScripts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigPackageEntity that = (ConfigPackageEntity) o;
        return configId == that.configId && packageVersionId == that.packageVersionId && Objects.equals(configurationScripts, that.configurationScripts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configId, packageVersionId, configurationScripts);
    }
}
