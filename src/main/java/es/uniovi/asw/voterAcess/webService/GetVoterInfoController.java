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

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.GetVoterInfo;
import es.uniovi.asw.voterAcess.webService.responses.VoterInfoResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;


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
		
		Voter user = this.voterRepository.findByEmail(voter.getEmail());
		
		
		if(user!=null)
		{
			if(user.getPassword().compareTo(voter.getPassword()) == 0)
			{
				return new VoterInfoResponse(user);
			}
			
			else
			{
				throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.USER_NOT_FOUND);
			}
		}
		
		else
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.USER_NOT_FOUND);
	}
	
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound()
	{
		return "{\"reason\": \""+HttpStatus.NOT_FOUND.getReasonPhrase()+"\"}";
	}
}