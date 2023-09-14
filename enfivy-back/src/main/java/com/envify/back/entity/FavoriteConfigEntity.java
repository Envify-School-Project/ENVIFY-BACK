package com.envify.back.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favourite_configs")
public class FavoriteConfigEntity {
    private int user_id;
    private int config_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    @Column(name = "config_id", nullable = false)
    public int getConfigId() {
        return config_id;
    }

    public void setConfigId(int config_id) {
        this.config_id = config_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteConfigEntity that = (FavoriteConfigEntity) o;
        return config_id == that.config_id && Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(config_id, user_id);
    }
}
