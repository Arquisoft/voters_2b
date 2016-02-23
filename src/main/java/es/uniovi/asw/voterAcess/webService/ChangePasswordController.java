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
import es.uniovi.asw.voterAcess.ChangePassword;
import es.uniovi.asw.voterAcess.webService.responses.ChangePasswordResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;


@RestController
@Controller
public class ChangePasswordController implements ChangePassword
{
	private static final Logger log = LoggerFactory.getLogger(GetVoterInfoController.class);
	
	private final VoterRepository voterRepository;
	
	
	@Autowired
	ChangePasswordController(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}
	
	
	@RequestMapping(value="/changePassword",
			method=RequestMethod.POST, 
			headers = "Accept=application/json",
			produces="application/json")
	public String changePassword(@RequestBody ChangePasswordResponse data)
	{
		for (Voter v : this.voterRepository.findAll())
		{
			log.info(v.toString());
		}
		
		
		Voter voter = this.voterRepository.findByEmail(data.getEmail());
		
		if (voter != null)
		{
			if (data.getPassword().compareTo(voter.getPassword()) == 0)
			{
				voter.setPassword(data.getNewPassword());
				this.voterRepository.save(voter);
				
				return "{\"Resultado\":\"Contraseña cambiada correctamente\"}";
			}
			
			else 
			{
				throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.PASSWORDS_DONT_MATCH);
			}
		}
		
		else // Voter no encontrado
		{
			throw ErrorFactory.getErrorResponse(ErrorFactory.Errors.USER_NOT_FOUND);
		}
	}
	
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound()
	{
		return "{\"reason\": \""+HttpStatus.NOT_FOUND.getReasonPhrase()+"\"}";
	}
}