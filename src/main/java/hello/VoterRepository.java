package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface VoterRepository extends CrudRepository<Voter, Long>
{
	Voter findByEmail(String name);
}