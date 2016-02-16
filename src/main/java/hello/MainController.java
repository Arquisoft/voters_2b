package hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController
{
	private final VoterRepository voterRepository;
	
	
	@Autowired
	MainController(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}
	
	
	@RequestMapping
			(value ="/voter",
			method = RequestMethod.POST,
			headers = "Accept=application/json",
			produces = "application/json")
	public Voter getVoter(@RequestParam("login") String email, @RequestParam("password") String password)
	{
		Voter voter = this.voterRepository.findByEmail(email);
		
		if(voter == null || !voter.getPassword().equals(password))
		{
			return null;  // No encontrado
		}
		
		return voter;
    }
	
	
	@RequestMapping("/")
	public String landing()
	{
		return "User Management Service";
	}
}