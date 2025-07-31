package com.shiwansh.DTO;

import com.shiwansh.model.Role;
import com.shiwansh.model.User;

public class AuthResponse {
	private String token;
    private User user;
    private Role role;
    private String statusCode;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
    

}
