package hello.model;

public class VoterJSON {
	private String email;
	private String name;
	private String nif;
	private int poolingState;

	public VoterJSON() {
	}
	public VoterJSON(Voter voter) {
		super();
		this.email = voter.getEmail();
		this.name = voter.getName();
		this.nif = voter.getNIF();
		this.poolingState = voter.getPollingPlace();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public int getPoolingState() {
		return poolingState;
	}
	public void setPoolingState(int poolingState) {
		this.poolingState = poolingState;
	}
	
	
}
