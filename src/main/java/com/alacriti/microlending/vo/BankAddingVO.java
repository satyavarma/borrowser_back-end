package com.alacriti.microlending.vo;

public class BankAddingVO {
	private String accountNumber;
	private String bankName;
	private String branch;
	private String ifscCode;
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
	@Override
	public String toString() {
		return "BankAddingVO [accountNumber=" + accountNumber + ", bankName=" + bankName + ", branch=" + branch
				+ ", ifscCode=" + ifscCode + "]";
	}
	public BankAddingVO(String accountNumber, String bankName, String branch, String ifscCode) {
		super();
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.branch = branch;
		this.ifscCode = ifscCode;
	}
	public BankAddingVO() {
		super();
	}
}
