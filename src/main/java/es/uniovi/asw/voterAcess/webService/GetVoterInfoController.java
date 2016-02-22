package es.uniovi.asw.voterAcess.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.GetVoterInfo;
import es.uniovi.asw.voterAcess.webService.responses.ErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.VoterInfoResponse;

@RestController
@Controller
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
		
		if(user!=null && user.getPassword().compareTo(voter.getPassword()) == 0)
			return new VoterInfoResponse(user);
		else
			throw new ErrorResponse(); //404 exception
		
	}
	
	
	/**
	 * Si se recibe una peticion get, devolver una página
	 * html con un formulario para hacer una petición post
	 * 
	 * @return   documento html con un formulario para pedir los datos del votante
	 * 
	 */
	@RequestMapping(value="/user",
			method= RequestMethod.GET,
			headers ="Accept=application/json",
			produces = "application/json")
	public String getPageInfoVoter()
	{
		return null;
	}
	
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(){
		return HttpStatus.NOT_FOUND.getReasonPhrase();
	}
}