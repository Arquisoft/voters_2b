package es.uniovi.asw.voterAcess;

import org.springframework.web.bind.annotation.RequestBody;

import es.uniovi.asw.voterAcess.webService.responses.ChangePasswordResponse;

public interface ChangePassword {

	public String changePassword(@RequestBody ChangePasswordResponse data);
	
}
