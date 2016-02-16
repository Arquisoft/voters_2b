package voterAcess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Census")
public class Voter {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String NIF;
	@Column(unique = true, nullable = false)
	private String email;
	private int pollingPlace;
	@Column(nullable = false)
	private String password;
	
	protected Voter() {}

	public Voter(String name, String NIF, String email, int pollingPlace) {
		this.name = name;
		this.NIF = NIF;
		this.email = email;
		this.pollingPlace = pollingPlace;
	}

	public Voter(String name, String NIF, String email, int pollingPlace, String password) {
		this.name = name;
		this.NIF = NIF;
		this.email = email;
		this.pollingPlace = pollingPlace;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getNIF() {
		return NIF;
	}

	public int getPollingPlace() {
		return pollingPlace;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "Voter [name=" + name + ", email=" + email + ", NIF=" + NIF + ", pollingPlace=" + pollingPlace
				+ ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NIF == null) ? 0 : NIF.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		return true;
	}
	
}
