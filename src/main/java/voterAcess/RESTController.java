package voterAcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import voterAcess.dao.ChangePasswordDAO;
import voterAcess.dao.VoterDAO;
import voterAcess.model.Voter;
import voterAcess.persistence.VoterRepository;


@RestController
public class RESTController {
	
	private static final Logger log = LoggerFactory.getLogger(RESTController.class);
	
	private final VoterRepository voterRepository;
	
	@Autowired
	RESTController(VoterRepository voterRepository) {
		this.voterRepository = voterRepository;
	}
	
    @RequestMapping(value="/user",method= RequestMethod.POST,
    		headers ="Accept=application/json",
    		produces = "application/json")
    public VoterDAO getInfoVoter(@RequestBody Voter voter) {
    	
    	log.info("Datos peticion: "+voter.getEmail()+" "+voter.getPassword());
    	
    	Voter user = this.voterRepository.findByEmail(voter.getEmail());
    	
    	if(user.getPassword().compareTo(voter.getPassword()) == 0)
    		return new VoterDAO(user);
    	else
    		throw new Error404NotFound(); //404 exception
    }
    
    @RequestMapping(value="/changePassword",method= RequestMethod.POST, 
    		headers = "Accept=application/json",
    		produces = "application/json")
    public void changePassword(@RequestBody ChangePasswordDAO data) {
    	Voter voter = this.voterRepository.findByEmail(data.getEmail());
    	if(voter!=null){
    		if(data.getPassword().compareTo(data.getRepeatPassword())==0)
    			if(data.getPassword().compareTo(voter.getPassword())==0){
    				voter.setPassword(data.getNewPassword());
    				this.voterRepository.save(voter);
    			}
    	}
    	for (Voter v : this.voterRepository.findAll()) {
			log.info(v.toString());
		}
    		
    }
    
    
    @RequestMapping("/")
    public String landing() {
          return "User Management Service";
    }
    
}