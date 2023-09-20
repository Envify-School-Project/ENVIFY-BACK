package com.envify.back.dto;

import java.util.Objects;

public class FavoriteConfigDto {
    private int configId;
    private int userId;

    public int getFavoriteConfigId() {
        return configId;
    }

    public void setFavoriteConfigId(int configId) {
        this.configId = configId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(configId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteConfigDto that = (FavoriteConfigDto) o;
        return configId == that.configId && Objects.equals(userId, that.userId);
    }
}
