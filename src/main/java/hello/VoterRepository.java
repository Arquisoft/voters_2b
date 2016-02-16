package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import hello.model.Voter;


public interface VoterRepository extends CrudRepository<Voter, Long> {
	
	    Voter findByEmail(String email);
	    Voter findByPassword(String password);
	    
}
