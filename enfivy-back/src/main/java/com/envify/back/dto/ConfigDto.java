package com.envify.back.dto;

import java.util.Objects;

public class ConfigDto {
    private int id;
    private String name;
    private String description;
    private int userId;
    private int operatingSystemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOperatingSystemId() {
        return operatingSystemId;
    }

    public void setOperatingSystemId(int operatingSystemId) {
        this.operatingSystemId = operatingSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigDto configDto = (ConfigDto) o;
        return id == configDto.id && userId == configDto.userId && operatingSystemId == configDto.operatingSystemId && Objects.equals(name, configDto.name) && Objects.equals(description, configDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, userId, operatingSystemId);
    }
}
