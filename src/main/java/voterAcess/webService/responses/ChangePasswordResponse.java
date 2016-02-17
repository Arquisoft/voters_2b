package voterAcess.webService.responses;

public class ChangePasswordResponse {
	
	private String email;
	private String password;
	private String repeatPassword;
	private String newPassword;
	
	public ChangePasswordResponse(){};
	
	public ChangePasswordResponse(String email, String password, String repeatPassword, String newPassword) {
		super();
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
