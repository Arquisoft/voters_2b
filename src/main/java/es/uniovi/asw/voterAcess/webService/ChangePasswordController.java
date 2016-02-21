package es.uniovi.asw.voterAcess.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.webService.responses.ChangePasswordResponse;
import es.uniovi.asw.voterAcess.webService.responses.ErrorResponse;

@RestController
@Controller
public class ChangePasswordController {

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
			produces = "application/json")
	public void changePassword(@RequestBody ChangePasswordResponse data)
	{
		Voter voter = this.voterRepository.findByEmail(data.getEmail());
		
		if(voter!=null)
		{
			if(data.getPassword().compareTo(data.getRepeatPassword())==0)
			{
				if(data.getPassword().compareTo(voter.getPassword())==0)
				{
					voter.setPassword(data.getNewPassword());
					this.voterRepository.save(voter);
				}
			}
			
			else // Contraseña inválida
			{
				
			}
		}
		
		else // Voter no encontrado
		{
			throw new ErrorResponse();
		}
		
		for (Voter v : this.voterRepository.findAll())
		{
			log.info(v.toString());
		}
	}
}
