package com.GymCompany.firstApp.model;

public class LoginDTO {  //(authenticated 된 정보를 담아놓는 dto  ) 
	private String userId;
	private String userPw;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public LoginDTO(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", userPw=" + userPw + "]";
	}
	
}
