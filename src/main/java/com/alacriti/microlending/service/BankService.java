package com.alacriti.microlending.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alacriti.microlending.dao.BankDAO;
import com.alacriti.microlending.dao.UserDAO;
import com.alacriti.microlending.vo.BankAddingVO;
import com.alacriti.microlending.vo.BankGettingVO;
import com.alacriti.microlending.vo.UserLogInOutputVO;

@Component
public class BankService {
	
	Logger logger = LoggerFactory.getLogger(BankService.class);
	@Autowired
	BankDAO bankDAO = new BankDAO();
	UserDAO userDAO = new UserDAO();
	
	public ResponseEntity<?> bankAddService(int userId, BankAddingVO bankDetails){
		try{
			return bankDAO.bankAddDAO(userId,bankDetails);
		}catch(Exception e){
			logger.debug("Exception:"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<?> bankGetService(int bankId) {
		try{
			BankGettingVO bankDetailsOutput = bankDAO.bankGetDetails(bankId);
			if(bankDetailsOutput == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else{
				return new ResponseEntity<BankGettingVO>(bankDetailsOutput, HttpStatus.OK);
			}
		}catch(Exception e){
			logger.debug("Exception:"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
