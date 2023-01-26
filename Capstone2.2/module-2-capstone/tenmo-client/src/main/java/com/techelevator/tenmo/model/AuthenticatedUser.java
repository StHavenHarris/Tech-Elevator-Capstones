package com.techelevator.tenmo.model;

public class AuthenticatedUser {
	//Initialize
	
	private String token;
	private User user;

	//Getters and Setters
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
}
