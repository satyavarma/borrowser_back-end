package com.alacriti.microlending.service;

import java.util.List;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alacriti.microlending.dao.UserDAO;
import com.alacriti.microlending.resource.UserResource;
import com.alacriti.microlending.vo.UserLogInOutputVO;
import com.alacriti.microlending.vo.UserLogInVO;
import com.alacriti.microlending.vo.UserSignUpVO;



@Component
public class UserService {
	
	@Autowired
	UserDAO userDAO = new UserDAO();
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public ResponseEntity<?> userSignUpService(UserSignUpVO user){
		try{
			int userSearch;
			int effectedRows;
			userSearch = userDAO.userSearchDAO(user.getUsername());
			if(userSearch == 0){
				effectedRows = userDAO.userSignUPDAO(user);
				if(effectedRows == 1){
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
				else{
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}
			}
			else{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	public ResponseEntity<?> userLogInService(UserLogInVO user){
		try{
			UserLogInOutputVO userOutput = userDAO.userLogInDAO(user);
			if(userOutput == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else{
				return new ResponseEntity<UserLogInOutputVO>(userOutput, HttpStatus.OK);
			}
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<?> userGetAllUsersService(int userId){
		try{
			List<UserLogInOutputVO> users = userDAO.userGetAllUsersDAO(userId);
			if(users.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<UserLogInOutputVO>>(users, HttpStatus.OK);
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<?> getUserByIdService(int userId){
		try{
			UserLogInOutputVO user = userDAO.getUserById(userId);
			if(user == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserLogInOutputVO>(user,HttpStatus.OK);
		}
		catch(Exception e){
			logger.debug("Exception"+e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	public ResponseEntity<?> inviteByMailService(String mailId){
		try { 
		String from = "satya.alacriti@gmail.com";
		String pswd = "alacriti@";
		Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,pswd);  
         }    
        });   
           
          
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailId));    
         message.setSubject("Invite For FWB Micro Lending Web Application");
         message.setText("Hi,Make sure to SignUp");
         message.setContent("<h3>FWB Micro Lending Web Application</h3><p>Provides the middle men service for money lending and borrowing processes.</p><a href='http://localhost:4200' target='_blank'><input type='button' value='Sign-Up'></a>",
        		 "text/html");    
         Transport.send(message);    
         System.out.println("message sent successfully"); 
         return new ResponseEntity<>(HttpStatus.ACCEPTED);   
        } catch (MessagingException e) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }   
	}
}
