package com.project.Dao;

public class UserDto {
	
	private Integer userId;
	private String email;
	private String jobTitle;
	private String organisation;
	private String profileImages;
	private String name;
	private String userRole;
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public void setProfileImages(String profileImages) {
		this.profileImages = profileImages;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getEmail() {
		return email;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public String getOrganisation() {
		return organisation;
	}
	public String getProfileImages() {
		return profileImages;
	}
	public String getName() {
		return name;
	}
	public String getUserRole() {
		return userRole;
	}



}
