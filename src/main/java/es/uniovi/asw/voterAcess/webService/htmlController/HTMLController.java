package es.uniovi.asw.voterAcess.webService.htmlController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uniovi.asw.dbManagement.GetVoter;
import es.uniovi.asw.dbManagement.impl.GetVoterDB;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.webService.responses.errors.InvalidPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredUserErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UserNotFoundErrorResponse;


/**
 * Se utiliza para gestionar las peticiones de tipo "get" que
 * son recibidas por el servidor web
 *
 */
@Controller
public class HTMLController
{
	private final VoterRepository voterRepository;
	
	
	@Autowired
	HTMLController(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userHTMLget(Model model)
	{
		return "user";
	}
	
	
	@RequestMapping(value = "/validarse", method = RequestMethod.POST)
	public String userHTMLpost(@RequestBody String parametros, Model model)
	{
		String[] parametro = parametros.split("&");
		String email = parametro[0].split("=")[1].replace("%40", "@");
		String contraseña = parametro[1].split("=")[1];
		
		if(email.equals(""))
			throw new RequiredUserErrorResponse();
		
		else if(contraseña.equals(""))
			throw new RequiredPasswordErrorResponse();
		
		GetVoter gv = new GetVoterDB(this.voterRepository);
		Voter user = gv.getVoter(email);
		
		
		if (user != null)
		{
			if(user.getPassword().compareTo(contraseña) == 0)
			{
				model.addAttribute("email", user.getEmail());
				model.addAttribute("name", user.getName());
				model.addAttribute("nif", user.getNIF());
				model.addAttribute("polling", user.getPollingPlace());
			}
			
			else // Contraseña incorrecta
			{
				throw new InvalidPasswordErrorResponse();
			}
		}
		
		else
		{
			throw new UserNotFoundErrorResponse();
		}
		
		
		return "result";
	}
	
	
	@ExceptionHandler(UserNotFoundErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(UserNotFoundErrorResponse excep)
	{
		return "{\"reason\": \""+ excep.getMessage() + "\"}";
	}
}
