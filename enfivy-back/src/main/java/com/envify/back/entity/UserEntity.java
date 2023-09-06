package com.envify.back.entity;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class UserEntity {

	private int id;
	private String username, last_name, first_name, email, company;
	@JsonIgnore
	private String password;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", nullable = true)
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	@Column(name = "last_name", nullable = false)
	public String getLastName() { return last_name; }
	public void setLastName(String last_name) { this.last_name = last_name; }

	@Column(name = "first_name", nullable = false)
	public String getFirstName() { return first_name; }
	public void setFirstName(String first_name) { this.first_name = first_name; }

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "company", nullable = true)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return id == that.id && Objects.equals(username, that.username) && Objects.equals(last_name, that.last_name) && Objects.equals(first_name, that.first_name) && Objects.equals(email, that.email) && Objects.equals(company, that.company) && Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, last_name, first_name, email, company, password);
	}
}
