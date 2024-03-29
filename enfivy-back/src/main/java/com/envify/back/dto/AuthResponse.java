package com.envify.back.dto;

import java.util.Objects;

public class AuthResponse {

	private String token;
	private String profil;
	private String email;
	private int userId;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, profil, token, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthResponse other = (AuthResponse) obj;
		return Objects.equals(email, other.email) && Objects.equals(profil, other.profil)
				&& Objects.equals(token, other.token) && userId == other.userId;
	}
}
