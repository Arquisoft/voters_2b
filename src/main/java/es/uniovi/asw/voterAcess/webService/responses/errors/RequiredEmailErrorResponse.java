package es.uniovi.asw.voterAcess.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="The field 'Email' is required")
public class RequiredEmailErrorResponse extends ErrorResponse
{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessageJSONFormat()
	{
		return "{\"reason\": \"The field 'Email' is required\"}";
	}


	@Override
	public String getMessageStringFormat()
	{
		return "The field 'Email' is required";
	}
}