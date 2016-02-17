package voterAcess;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Order not found")
public class Error404NotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
