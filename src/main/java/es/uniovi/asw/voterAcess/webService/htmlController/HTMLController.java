package es.uniovi.asw.voterAcess.webService.htmlController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.dbManagement.GetVoter;
import es.uniovi.asw.dbManagement.impl.GetVoterDB;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;


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
	public String userHTMLget(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model)
	{
		model.addAttribute("name", name);
		
		return "user";
	}
	
	
	@RequestMapping(value = "/validarse", method = RequestMethod.POST)
	public String userHTMLpost(@RequestBody String parametros, Model model)
	{
		String[] parametro = parametros.split("&");
		String email = parametro[0].split("=")[1].replace("%40", "@");
		String contraseña = parametro[1].split("=")[1];

		GetVoter gv = new GetVoterDB(this.voterRepository);
		Voter user = gv.getVoter(email);	
		
		if (user != null && user.getPassword().compareTo(contraseña) == 0)
		{
			model.addAttribute("email", user.getEmail());
			model.addAttribute("name", user.getName());
			model.addAttribute("nif", user.getNIF());
			model.addAttribute("polling", user.getPollingPlace());
		}
		
		
		return "result";
	}
}
