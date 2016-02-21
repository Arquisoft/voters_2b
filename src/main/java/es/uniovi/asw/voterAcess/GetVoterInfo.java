package es.uniovi.asw.voterAcess;

import org.springframework.web.bind.annotation.RequestBody;


import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.voterAcess.webService.responses.VoterInfoResponse;

public interface GetVoterInfo {

	public VoterInfoResponse getInfoVoter(@RequestBody Voter voter);
	
}
