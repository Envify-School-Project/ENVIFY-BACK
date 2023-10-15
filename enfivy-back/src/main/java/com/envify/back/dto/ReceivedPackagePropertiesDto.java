package com.envify.back.dto;

import java.util.List;
import java.util.Objects;

public class ReceivedPackagePropertiesDto {
    private String category;
    private String field;
    private String type;
    private String label;
    private String value;
    private List<ReceivedPackagePropertiesDto> values;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ReceivedPackagePropertiesDto> getValues() {
        return values;
    }

    public void setValues(List<ReceivedPackagePropertiesDto> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedPackagePropertiesDto that = (ReceivedPackagePropertiesDto) o;
        return Objects.equals(category, that.category) && Objects.equals(field, that.field) && Objects.equals(type, that.type) && Objects.equals(label, that.label) && Objects.equals(value, that.value) && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, field, type, label, value, values);
    }
}
