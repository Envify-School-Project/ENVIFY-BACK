package com.envify.back.dto.finaldto;

import java.util.Objects;

public class PackagePropertiesObject {
    private String label;
    private String type;
    private String defaultValue;
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagePropertiesObject that = (PackagePropertiesObject) o;
        return Objects.equals(label, that.label) && Objects.equals(type, that.type) && Objects.equals(defaultValue, that.defaultValue) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, type, defaultValue, value);
    }
}
