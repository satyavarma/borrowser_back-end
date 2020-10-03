package com.alacriti.microlending.vo;

public class UserLogInOutputVO {
	private int userId;
	private String username;
	private String password;
	private String name;
	private String contactNumber;
	private int bankId;
	private int age;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserLogInOutputVO [userId=" + userId + ", username=" + username + ", password=" + password + ", name="
				+ name + ", contactNumber=" + contactNumber + ", bankId=" + bankId + ", age=" + age + "]";
	}
	public UserLogInOutputVO(int userId, String username, String password, String name, String contactNumber,
			int bankId, int age) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.contactNumber = contactNumber;
		this.bankId = bankId;
		this.age = age;
	}
	public UserLogInOutputVO() {
		super();
	}
	
		
}
