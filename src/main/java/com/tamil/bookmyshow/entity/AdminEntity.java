package com.tamil.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "Admin")
public class AdminEntity {
	@Id
	@Email
	private String email;
	@NotBlank(message = "Name cannot be blank")
	@NotNull(message = "Name cannot be null")
	private String name;
	
	@NotNull(message = "password cannot be null")
	@NotBlank(message = "password cannot be null")
	@Size(min = 8,max = 10,message = "password size should be in 8 to 10 characters")
	private String password;
	@NotNull(message = "contact number cant be null")
	@Min(value = 6000000000L,message = "contact number should be greater than 6000000000")
	@Max(value = 9999999999L,message = "contact number should be less than 9999999999")
	private long contactNumber;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "AdminEntity [email=" + email + ", name=" + name + ", password=" + password + ", contactNumber="
				+ contactNumber + "]";
	}


}
