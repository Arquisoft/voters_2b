package es.uniovi.asw.voterAcess.webService.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import es.uniovi.asw.dbManagement.model.Voter;

@XmlRootElement(name = "VoterInfoResponse")
public class VoterInfoResponse {
	private String email;
	private String name;
	private String nif;
	private int poolingState;

	public VoterInfoResponse() {
	}
	public VoterInfoResponse(Voter voter) {
		super();
		setEmail(voter.getEmail());
		setName(voter.getName());
		setNif(voter.getNIF());
		setPoolingState(voter.getPollingPlace());
	}
	public String getEmail() {
		return email;
	}
	
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getNif() {
		return nif;
	}
	
	@XmlElement
	public void setNif(String nif) {
		this.nif = nif;
	}
	public int getPoolingState() {
		return poolingState;
	}
	
	 @XmlElement
	public void setPoolingState(int poolingState) {
		this.poolingState = poolingState;
	}
	
	
}
