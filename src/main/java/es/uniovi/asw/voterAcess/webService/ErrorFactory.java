package es.uniovi.asw.voterAcess.webService;

import es.uniovi.asw.voterAcess.webService.responses.errors.InvalidPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.PasswordsDontMatchErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UnknownErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UserNotFoundErrorResponse;


/**
 * Crea un error diferente según la causa que lo haya provocado
 *
 */
public class ErrorFactory
{
	public static enum Errors {USER_NOT_FOUND, INVALID_PASSWORD, PASSWORDS_DONT_MATCH, UNKNOWN_ERROR};
	
	
	/**
	 * No tiene sentido que se creen instancias de la factoría. Su función
	 * sólo es devolver el error apropiado para la causa que se le indique.
	 * 
	 */
	private ErrorFactory() {	}
	
	
	public static RuntimeException getErrorResponse(Errors causaError)
	{
		switch (causaError)
		{
			case USER_NOT_FOUND:
				return new UserNotFoundErrorResponse();
			
			case INVALID_PASSWORD:
				return new InvalidPasswordErrorResponse();
				
			case PASSWORDS_DONT_MATCH:
				return new PasswordsDontMatchErrorResponse();
			
			default:	// UNKNOWN_ERROR (Se desconoce la causa del error)
				return new UnknownErrorResponse();
		}
	}
}