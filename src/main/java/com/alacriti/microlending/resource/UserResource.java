package com.alacriti.microlending.resource;



import java.util.function.Supplier;



import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.alacriti.microlending.service.UserService;
import com.alacriti.microlending.vo.UserLogInVO;
import com.alacriti.microlending.vo.UserSignUpVO;



@Path("/user")
public class UserResource {
	
	
	@Autowired
	UserService userService = new UserService();
	
	@GET
	public String status(){
		return "working fine";
	}
	
	
	@POST
	@Path("/signup")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> userSignUpResource(UserSignUpVO user){
		return 	userService.userSignUpService(user);	
	}
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> userLogInResource(UserLogInVO user){
		return userService.userLogInService(user);
	}
	
	@GET
	@Path("/getall/{id}")
	@Produces("application/json")
	public ResponseEntity<?> userGetAllUsersResource(@PathParam("id") int userId){
		return userService.userGetAllUsersService(userId);
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseEntity<?> getUserByIdResource(@PathParam("id") int userId){
		return userService.getUserByIdService(userId);
	}
	
	@GET
	@Path("/invite/{mailId}")
	@Produces("application/json")
	public ResponseEntity<?> inviteByMailResource(@PathParam("mailId") String mailId){
		return userService.inviteByMailService(mailId);
	}
}
