package voterAcess.persistence;

import org.springframework.data.repository.CrudRepository;

import voterAcess.model.Voter;



public interface VoterRepository extends CrudRepository<Voter, Long>
{
	    Voter findByEmail(String email);
	    Voter findByPassword(String password);
}

