package com.alacriti.microlending.vo;

public class BankGettingVO {
	private String accountNumber;
	private String bankName;
	private String branch;
	private String ifscCode;
	private int bankId;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public BankGettingVO(String accountNumber, String bankName, String branch, String ifscCode, int bankId) {
		super();
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.branch = branch;
		this.ifscCode = ifscCode;
		this.bankId = bankId;
	}
	public BankGettingVO() {
		super();
	}
	@Override
	public String toString() {
		return "BankGettingVO [accountNumber=" + accountNumber + ", bankName=" + bankName + ", branch=" + branch
				+ ", ifscCode=" + ifscCode + ", bankId=" + bankId + "]";
	}
	
}
