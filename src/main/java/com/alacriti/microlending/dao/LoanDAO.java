package com.alacriti.microlending.dao;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alacriti.microlending.constants.LoanRequestConstants;
import com.alacriti.microlending.service.LoanService;
import com.alacriti.microlending.vo.LoanAddingVO;
import com.alacriti.microlending.vo.LoanGettingVO;
import com.alacriti.microlending.vo.UserLogInOutputVO;

@Repository
public class LoanDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	Logger logger = LoggerFactory.getLogger(LoanDAO.class);
	
	public int addLoanRequestDAO(int borrowerId, int lenderId,  LoanAddingVO loan){
		try{
			String borrowerNameSql = "select name from AL372_users where user_id =?";
			String borrowerName = jdbcTemplate.queryForObject(borrowerNameSql, new Object[]{borrowerId},String.class);
			String lenderNameSql = "select name from AL372_users where user_id =?";
			String lenderName = jdbcTemplate.queryForObject(borrowerNameSql, new Object[]{lenderId},String.class);
			String sql="insert into AL372_loan_requests(borrower_id,lender_id,borrower_name,lender_name,loan_amount,tenure,loan_reason,request_status,creation_time) values(?,?,?,?,?,?,?,?,now());";
			int effectedRows = jdbcTemplate.update(sql,borrowerId,lenderId,borrowerName,lenderName,loan.getLoanAmount(),loan.getTenure(),loan.getLoanReason(),LoanRequestConstants.PENDING);
			return effectedRows;
		}catch(Exception e){
			return 0;
		}
		
	}

	public List<LoanGettingVO> getReceivedRequestsDAO(int userId) {
		List<LoanGettingVO> loans;
		String sql = "select * from AL372_loan_requests where ( lender_id = ? and request_status=1) or (borrower_id = ? and request_status = 4)";
		loans = jdbcTemplate.query(sql, new Object[]{userId,userId},
					(rs, rowNum) -> new LoanGettingVO(
							rs.getInt("request_id"),
							rs.getInt("borrower_id"),
							rs.getInt("lender_id"),
							rs.getString("borrower_name"),
							rs.getString("lender_name"),
							rs.getInt("loan_amount"),
							rs.getInt("tenure"),
							rs.getString("loan_reason"),
							rs.getInt("request_status"),
							""+rs.getDate("loan_accepted_date")
						)
				);
		return loans;
	}

	public int updateRequestStatusDAO(int requestId, int statusValue) {
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		int effectedRows = 0;
		String sql = "";
		if(statusValue == LoanRequestConstants.ACCEPTED){
			sql = "update AL372_loan_requests set request_status = ? , loan_accepted_date = ? where request_id = ?";
			effectedRows = jdbcTemplate.update(sql,LoanRequestConstants.ACCEPTED,sqlDate,requestId);
			return effectedRows;
		}
		else if(statusValue == LoanRequestConstants.REJECTED){
			sql = "update AL372_loan_requests set request_status = ? where request_id = ?";
			effectedRows = jdbcTemplate.update(sql,LoanRequestConstants.REJECTED,requestId);
			return effectedRows;
		}
		else if(statusValue == LoanRequestConstants.REPAID){
			sql = "update AL372_loan_requests set request_status = ? where request_id = ?";
			effectedRows = jdbcTemplate.update(sql,LoanRequestConstants.REPAID,requestId);
			return effectedRows;
		}
		else if(statusValue == LoanRequestConstants.REPAYREQUESTED){
			sql = "update AL372_loan_requests set request_status = ? where request_id = ?";
			effectedRows = jdbcTemplate.update(sql,LoanRequestConstants.REPAYREQUESTED,requestId);
			return effectedRows;
		}
		return effectedRows;
	}

	public List<LoanGettingVO> getSentRequestsDAO(int userId) {
		List<LoanGettingVO> loans;
		String sql = "select * from AL372_loan_requests where borrower_id = ?";
		loans = jdbcTemplate.query(sql, new Object[]{userId},
					(rs, rowNum) -> new LoanGettingVO(
							rs.getInt("request_id"),
							rs.getInt("borrower_id"),
							rs.getInt("lender_id"),
							rs.getString("borrower_name"),
							rs.getString("lender_name"),
							rs.getInt("loan_amount"),
							rs.getInt("tenure"),
							rs.getString("loan_reason"),
							rs.getInt("request_status"),
							""+rs.getDate("loan_accepted_date")
						)
				);
		return loans;
	}

	public List<LoanGettingVO> getBorrowedTransactionsDAO(int userId) {
		List<LoanGettingVO> transactions;
		String sql = "SELECT * from AL372_loan_requests where borrower_id = ? AND (request_status = 2 or request_status = 4 or request_status = 5);";
		transactions = jdbcTemplate.query(sql, new Object[]{userId},
					(rs, rowNum) -> new LoanGettingVO(
							rs.getInt("request_id"),
							rs.getInt("borrower_id"),
							rs.getInt("lender_id"),
							rs.getString("borrower_name"),
							rs.getString("lender_name"),
							rs.getInt("loan_amount"),
							rs.getInt("tenure"),
							rs.getString("loan_reason"),
							rs.getInt("request_status"),
							""+rs.getDate("loan_accepted_date")
						)
				);
		return transactions;
	}

	public List<LoanGettingVO> getLendedTransactionsDAO(int userId) {
		List<LoanGettingVO> transactions;
		String sql = "SELECT * from AL372_loan_requests where lender_id = ? AND (request_status = 2 or request_status = 4 or request_status = 5);";
		transactions = jdbcTemplate.query(sql, new Object[]{userId},
					(rs, rowNum) -> new LoanGettingVO(
							rs.getInt("request_id"),
							rs.getInt("borrower_id"),
							rs.getInt("lender_id"),
							rs.getString("borrower_name"),
							rs.getString("lender_name"),
							rs.getInt("loan_amount"),
							rs.getInt("tenure"),
							rs.getString("loan_reason"),
							rs.getInt("request_status"),
							""+rs.getDate("loan_accepted_date")
						)
				);
		return transactions;
	}
}
