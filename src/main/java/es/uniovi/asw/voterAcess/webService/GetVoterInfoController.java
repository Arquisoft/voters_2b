package es.uniovi.asw.voterAcess.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbManagement.GetVoter;
import es.uniovi.asw.dbManagement.impl.GetVoterDB;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.GetVoterInfo;
import es.uniovi.asw.voterAcess.webService.responses.VoterInfoResponse;
import es.uniovi.asw.voterAcess.webService.responses.error.PasswordConflict;
import es.uniovi.asw.voterAcess.webService.responses.error.UserNotFound;


@RestController
public class GetVoterInfoController implements GetVoterInfo
{
	private static final Logger log = LoggerFactory.getLogger(GetVoterInfoController.class);
	
	private final VoterRepository voterRepository;
	
	@Autowired
	GetVoterInfoController(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}
	
	@RequestMapping(value="/user",
			method= RequestMethod.POST,
			headers ="Accept=application/json",
			produces={"application/json","application/xml"})
	public VoterInfoResponse getInfoVoter(@RequestBody Voter voter)
	{
		log.info("Datos peticion: "+voter.getEmail()+" "+voter.getPassword());
		
		GetVoter gv = new GetVoterDB(this.voterRepository);
		Voter user = gv.getVoter(voter.getEmail());
		if(user==null)
			throw new UserNotFound();
		if(user.getPassword().compareTo(voter.getPassword()) == 0)
			return new VoterInfoResponse(user);
		
		else
			throw new PasswordConflict();
	}
	
	
	@ExceptionHandler(UserNotFound.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound()
	{
		return "{\"reason\": \"User not found\"}";
	}
	
	@ExceptionHandler(PasswordConflict.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String passwordConflict()
	{
		return "{\"reason\": \"Password incorrect\"}";
	}
	
}