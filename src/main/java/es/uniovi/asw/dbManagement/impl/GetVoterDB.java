package es.uniovi.asw.dbManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.dbManagement.GetVoter;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;

public class GetVoterDB implements GetVoter{

private final VoterRepository voterRepository;
	
	@Autowired
	public GetVoterDB(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}

	@Override
	public Voter getVoter(String email) {
			return this.voterRepository.findByEmail(email);
	}

}
