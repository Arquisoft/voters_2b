package es.uniovi.asw.voterAcess.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Passwords don't match")
public class PasswordsDontMatchErrorResponse extends ErrorResponse
{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessageJSONFormat()
	{
		return "{\"reason\": \"Passwords don't match\"}";
	}


	@Override
	public String getMessageStringFormat() {
		return "Passwords don't match";
	}
}