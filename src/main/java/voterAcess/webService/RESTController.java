package voterAcess.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import voterAcess.model.Voter;
import voterAcess.persistence.VoterRepository;
import voterAcess.webService.responses.ChangePasswordResponse;
import voterAcess.webService.responses.ErrorResponse;
import voterAcess.webService.responses.VoterInfoResponse;

@Controller
@RestController
public class RESTController
{
	private static final Logger log = LoggerFactory.getLogger(RESTController.class);
	
	private final VoterRepository voterRepository;
	
	@Autowired
	RESTController(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}
	
	
	@RequestMapping(value="/user",
			method= RequestMethod.POST,
			headers ="Accept=application/json",
			produces = "application/json")
	public VoterInfoResponse getInfoVoter(@RequestBody Voter voter)
	{
		log.info("Datos peticion: "+voter.getEmail()+" "+voter.getPassword());
		
		Voter user = this.voterRepository.findByEmail(voter.getEmail());
		
		
		if(user!=null && user.getPassword().compareTo(voter.getPassword()) == 0)
		{
			return new VoterInfoResponse(user);
		}
		
		else
		{
			throw new ErrorResponse(); //404 exception
		}
	}
	
	/**
	 * Experimental: Si se recibe una peticion get devolver una página
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
	
	
	@RequestMapping("/")
	public String landing()
	{
		return "User Management Service";
	}
}