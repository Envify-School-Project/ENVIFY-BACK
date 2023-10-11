package com.envify.back.dto;

import java.util.Objects;

public class RequestResponse {
	
	private String message;
	private int code;
	private int id;
	
	public RequestResponse() {
	}
	
	public RequestResponse(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public RequestResponse(String message, int code, int id) {
		super();
		this.message = message;
		this.code = code;
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(code, id, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestResponse other = (RequestResponse) obj;
		return code == other.code && id == other.id && Objects.equals(message, other.message);
	}
}
