package com.alacriti.microlending.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alacriti.microlending.dao.LoanDAO;
import com.alacriti.microlending.vo.LoanAddingVO;
import com.alacriti.microlending.vo.LoanGettingVO;
import com.alacriti.microlending.vo.UserLogInOutputVO;
import com.alacriti.microlending.vo.UserSignUpVO;

@Component
public class LoanService {
	
	@Autowired
	LoanDAO loanDAO = new LoanDAO();
	
	Logger logger = LoggerFactory.getLogger(LoanService.class);
	
	public ResponseEntity<?> addLoanRequestService(int borrowerId, int lenderId, LoanAddingVO loan){
		try{
			int effectedRows;
			effectedRows = loanDAO.addLoanRequestDAO(borrowerId, lenderId, loan);
			if(effectedRows == 1){
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	public ResponseEntity<?> getReceivedRequestsService(int userId) {
		try{
			List<LoanGettingVO> loans = loanDAO.getReceivedRequestsDAO(userId);
			if(loans.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else{
				return new ResponseEntity<List<LoanGettingVO>>(loans, HttpStatus.OK);
			}
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<?> updateRequestStatusService(int requestId, int statusValue) {
		try{
			int effectedRows;
			effectedRows = loanDAO.updateRequestStatusDAO(requestId, statusValue);
			if(effectedRows == 1){
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
			
		}catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<?> getSentRequestsService(int userId) {
		try{
			List<LoanGettingVO> loans = loanDAO.getSentRequestsDAO(userId);
			if(loans.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<LoanGettingVO>>(loans, HttpStatus.OK);
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<?> getBorrowedTransactionsService(int userId) {
		try{
			List<LoanGettingVO> transactions = loanDAO.getBorrowedTransactionsDAO(userId);
			if(transactions.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<LoanGettingVO>>(transactions, HttpStatus.OK);
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<?> getLendedTransactionsService(int userId) {
		try{
			List<LoanGettingVO> transactions = loanDAO.getLendedTransactionsDAO(userId);
			if(transactions.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<LoanGettingVO>>(transactions, HttpStatus.OK);
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<?> getNotificationRequestsService(int userId) {
		try{
			List<LoanGettingVO> loans = loanDAO.getNotificationRequestsDAO(userId);
			if(loans.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else{
				return new ResponseEntity<List<LoanGettingVO>>(loans, HttpStatus.OK);
			}
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
