package com.envify.back.dto;

import java.util.Objects;

public class ReceivedPackagePropertiesDto {
    private String category;
    private String field;
    private String type;
    private String label;
    private String defaultValue;
    private String value;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

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
        ReceivedPackagePropertiesDto that = (ReceivedPackagePropertiesDto) o;
        return Objects.equals(category, that.category) && Objects.equals(field, that.field) && Objects.equals(type, that.type) && Objects.equals(label, that.label) && Objects.equals(defaultValue, that.defaultValue) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, field, type, label, defaultValue, value);
    }
}
