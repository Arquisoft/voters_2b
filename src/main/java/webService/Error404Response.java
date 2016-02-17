package webService;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Order not found")
public class Error404Response extends RuntimeException
{
	private static final long serialVersionUID = 1L;
}