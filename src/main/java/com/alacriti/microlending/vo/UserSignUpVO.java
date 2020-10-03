package com.alacriti.microlending.vo;

public class UserSignUpVO {
	private String username;
	private String password;
	private String name;
	private int age;
	private String contactNumber;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContact_number(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "UserSignUpVO [username=" + username + ", password=" + password + ", name=" + name + ", age=" + age
				+ ", contactNumber=" + contactNumber + "]";
	}
	
}
