package com.alacriti.microlending.vo;

public class LoanGettingVO {
	private int requestId;
	private int borrowerId;
	private int lenderId;
	private String borrowerName;
	private String lenderName;
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getLenderName() {
		return lenderName;
	}
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	private int loanAmount;
	private int tenure;
	private String loanReason;
	private int requestStatus;
	private String loanAcceptedDate;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
	public int getLenderId() {
		return lenderId;
	}
	public void setLenderId(int lenderId) {
		this.lenderId = lenderId;
	}
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
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getLoanAcceptedDate() {
		return loanAcceptedDate;
	}
	public void setLoanAcceptedDate(String loanAcceptedDate) {
		this.loanAcceptedDate = loanAcceptedDate;
	}
	public LoanGettingVO(int requestId, int borrowerId, int lenderId, String borrowerName, String lenderName,
			int loanAmount, int tenure, String loanReason, int requestStatus, String loanAcceptedDate) {
		super();
		this.requestId = requestId;
		this.borrowerId = borrowerId;
		this.lenderId = lenderId;
		this.borrowerName = borrowerName;
		this.lenderName = lenderName;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.loanReason = loanReason;
		this.requestStatus = requestStatus;
		this.loanAcceptedDate = loanAcceptedDate;
	}
	public LoanGettingVO() {
		super();
	}
	@Override
	public String toString() {
		return "LoanGettingVO [requestId=" + requestId + ", borrowerId=" + borrowerId + ", lenderId=" + lenderId
				+ ", borrowerName=" + borrowerName + ", lenderName=" + lenderName + ", loanAmount=" + loanAmount
				+ ", tenure=" + tenure + ", loanReason=" + loanReason + ", requestStatus=" + requestStatus
				+ ", loanAcceptedDate=" + loanAcceptedDate + "]";
	}
		
}
