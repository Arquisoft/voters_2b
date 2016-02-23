package es.uniovi.asw.dbManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import es.uniovi.asw.dbManagement.UpdatePassword;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;

public class UpdatePasswordDB implements UpdatePassword{

private final VoterRepository voterRepository;
	
	@Autowired
	public UpdatePasswordDB(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}

	@Override
	public boolean changePassword(String password, String newPassword,Voter voter) {
		if (password.compareTo(voter.getPassword()) == 0)
		{
			voter.setPassword(newPassword);
			this.voterRepository.save(voter);
			return true;
		}
		return false;
	}


}
