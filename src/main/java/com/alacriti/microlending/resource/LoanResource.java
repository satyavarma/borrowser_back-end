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

import com.alacriti.microlending.service.LoanService;
import com.alacriti.microlending.vo.LoanAddingVO;

@Path("/loan")
@RestController
public class LoanResource {
	
	@Autowired
	LoanService loanService;
	
	@POST
	@Path("/add/{borrowerId}/{lenderId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> addLoanRequestResource(LoanAddingVO loan,@PathParam("borrowerId") int borrowerId,@PathParam("lenderId") int lenderId){
		return loanService.addLoanRequestService(borrowerId, lenderId, loan);
	}
	
	@GET
	@Path("/receivedRequests/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> getReceivedRequestsResource(@PathParam("userId") int userId){
		return loanService.getReceivedRequestsService(userId);
	}
	
	@GET
	@Path("/sentRequests/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> getSentRequestsResource(@PathParam("userId") int userId){
		return loanService.getSentRequestsService(userId);
	}
	
	@GET
	@Path("/notificationRequests/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> getNotificationRequestsResource(@PathParam("userId") int userId){
		return loanService.getNotificationRequestsService(userId);
	}
	
	@POST
	@Path("/changeStatus/{requestId}/{statusValue}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> updateRequestStatusResource(@PathParam("requestId") int requestId, @PathParam("statusValue") int statusValue){
		System.out.println(statusValue);
		return loanService.updateRequestStatusService(requestId,statusValue);
	}
	
	@GET
	@Path("/borrowedTransactions/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> getBorrowedTransactionsResource(@PathParam("userId") int userId){
		return loanService.getBorrowedTransactionsService(userId);
	}
	
	@GET
	@Path("/lendedTransactions/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<?> getLendedTransactionsResource(@PathParam("userId") int userId){
		return loanService.getLendedTransactionsService(userId);
	}
}
