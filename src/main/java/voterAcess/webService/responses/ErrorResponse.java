package voterAcess.webService.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")
public class ErrorResponse extends RuntimeException
{
	private static final long serialVersionUID = 1L;
}