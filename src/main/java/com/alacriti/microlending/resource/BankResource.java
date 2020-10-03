package com.alacriti.microlending.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.alacriti.microlending.service.BankService;
import com.alacriti.microlending.vo.BankAddingVO;

@Path("/bank")
@RestController
public class BankResource {
	
	@Autowired
	BankService bankService = new BankService();
	
	@GET
	public String status(){
		return "working fine";
	}
	@GET
	@Path("/get/{bankId}")
	@Produces("application/json")
	public ResponseEntity<?> BankGetResource(@PathParam("bankId") int bankId){
		return bankService.bankGetService(bankId);
	}
	
	@POST
	@Path("/add/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> BankAddResource(BankAddingVO bankDetails, @PathParam("userId") int userId){
		return bankService.bankAddService(userId, bankDetails);
	}
}
