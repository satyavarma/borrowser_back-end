package com.alacriti.microlending.resource;



import java.util.function.Supplier;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.alacriti.microlending.service.UserService;
import com.alacriti.microlending.vo.UserLogInVO;
import com.alacriti.microlending.vo.UserSignUpVO;



@Path("/user")
public class UserResource {
	
	
	@Autowired
	UserService userService;
	Logger logger = LoggerFactory.getLogger(UserResource.class);
	
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
	
	//@CrossOrigin(origins="http://localhost:4200")
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> userLogInResource(UserLogInVO user/*, HttpServletRequest request,HttpServletResponse response*/){
		/*if(user!=null) {
			String sessionId=getRandomAlphaNumeric(10);
			Cookie cookie=new Cookie("login",sessionId);
			response.addCookie(cookie);

			logger.info("Cookie => "+cookie);
			logger.info("Response => "+response);
			
			//cookie.setMaxAge(7 * 24 * 60 * 60);
	   //   cookie.setSecure(true);
	  //    cookie.setHttpOnly(true);
			cookie.setPath("/");
		}*/
		return userService.userLogInService(user);
	}
	
		private String getRandomAlphaNumeric(int count) 
		{
			String ALPHA_NUMERIC_STRING="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
			StringBuilder builder=new StringBuilder();
			while(count-- !=0)
			{
				int character=(int) (Math.random()*ALPHA_NUMERIC_STRING.length());
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			return builder.toString();
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
	@Path("search/{id}/{searchValue}")
	@Produces("application/json")
	public ResponseEntity<?> getUserByNameResource(@PathParam("id") int userId, @PathParam("searchValue") String searchName){
		return userService.getUsersByNameService(userId,searchName);
	}
	@GET
	@Path("/invite/{mailId}")
	@Produces("application/json")
	public ResponseEntity<?> inviteByMailResource(@PathParam("mailId") String mailId){
		return userService.inviteByMailService(mailId);
	}
	
	
}
