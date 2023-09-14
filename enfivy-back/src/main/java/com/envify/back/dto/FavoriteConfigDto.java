package com.envify.back.dto;

import java.util.Objects;

public class FavoriteConfigDto {
    private int config_id;
    private int user_id;

    public int getFavoriteConfigId() {
        return config_id;
    }

    public void setFavoriteConfigId(int config_id) {
        this.config_id = config_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(config_id, user_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteConfigDto that = (FavoriteConfigDto) o;
        return config_id == that.config_id && Objects.equals(user_id, that.user_id);
    }
}
