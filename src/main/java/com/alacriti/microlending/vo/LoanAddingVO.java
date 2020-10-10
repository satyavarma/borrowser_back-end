package com.alacriti.microlending.vo;

public class LoanAddingVO {
	private int loanAmount;
	private int tenure;
	private String loanReason;
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getLoanReason() {
		return loanReason;
	}
	public void setLoanReason(String loanReason) {
		this.loanReason = loanReason;
	}
	@Override
	public String toString() {
		return "LoanAddingVO [loanAmount=" + loanAmount + ", tenure=" + tenure + ", loanReason=" + loanReason + "]";
	}
	public LoanAddingVO(int loanAmount, int tenure, String loanReason) {
		super();
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.loanReason = loanReason;
	}
	public LoanAddingVO() {
		super();
	}
	
}
