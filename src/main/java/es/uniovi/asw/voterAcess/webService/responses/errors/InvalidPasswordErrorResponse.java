package es.uniovi.asw.voterAcess.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Password incorrect")
public class InvalidPasswordErrorResponse extends ErrorResponse
{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessageJSONFormat()
	{
		return "{\"reason\": \"Password incorrect\"}";
	}


	@Override
	public String getMessageStringFormat() {
		return "Password incorrect";
	}
}