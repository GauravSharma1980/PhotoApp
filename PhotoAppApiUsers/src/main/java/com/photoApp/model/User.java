package com.photoApp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class User {

	@NotBlank(message="Please enter First Name")
	private String firstName;
	@NotBlank(message="Please enter last Name")
	private String lastName;
	private String userId;
	private String address;
	@Email
	private String emailAddress;
	
	
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + ", address=" + address
				+ "]";
	}

	
}
