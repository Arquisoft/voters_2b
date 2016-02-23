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
import es.uniovi.asw.dbManagement.UpdatePassword;
import es.uniovi.asw.dbManagement.GetVoter;
import es.uniovi.asw.dbManagement.impl.UpdatePasswordDB;
import es.uniovi.asw.dbManagement.impl.GetVoterDB;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.ChangePassword;
import es.uniovi.asw.voterAcess.webService.responses.ChangePasswordResponse;
import es.uniovi.asw.voterAcess.webService.responses.error.PasswordConflict;
import es.uniovi.asw.voterAcess.webService.responses.error.UserNotFound;

@RestController
@Controller
public class ChangePasswordController implements ChangePassword{

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
		log.info("Password: "+data.getPassword()+" New Password: "+data.getNewPassword());
		
		UpdatePassword cp = new UpdatePasswordDB(this.voterRepository);
		GetVoter gv = new GetVoterDB(this.voterRepository);
		Voter voter = gv.getVoter(data.getEmail());
		
		if (voter != null)
		{
			if(cp.changePassword(data.getPassword(),data.getNewPassword(), voter))
			{
				return "{\"Resultado\":\"Contraseña cambiada correctamente\"}";
			}else {
				throw new PasswordConflict();
			}
		}else // Voter no encontrado
		{
			throw new UserNotFound();
		}
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
