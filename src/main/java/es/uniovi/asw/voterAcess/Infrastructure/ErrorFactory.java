package es.uniovi.asw.voterAcess.Infrastructure;

import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.IncorrectPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.PasswordsDontMatchErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredNewPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredPasswordErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.RequiredEmailErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UnknownErrorResponse;
import es.uniovi.asw.voterAcess.webService.responses.errors.UserNotFoundErrorResponse;


/**
 * Crea un error diferente según la causa que lo haya provocado
 *
 */
public class ErrorFactory
{
	public static enum Errors
	{
		USER_NOT_FOUND,
		
		INCORRECT_PASSWORD,
		PASSWORDS_DONT_MATCH,
		
		REQUIRED_EMAIL,
		REQUIRED_PASSWORD,
		REQUIRED_NEW_PASSWORD,
		
		UNKNOWN_ERROR
	};
	
	
	/**
	 * No tiene sentido que se creen instancias de la factoría. Su función
	 * sólo es devolver el error apropiado para la causa que se le indique.
	 * 
	 */
	private ErrorFactory()
	{
		
	}
	
	
	public static ErrorResponse getErrorResponse(Errors causaError)
	{
		switch (causaError)
		{
			case USER_NOT_FOUND:
				return new UserNotFoundErrorResponse();
			
			case INCORRECT_PASSWORD:
				return new IncorrectPasswordErrorResponse();
			
			case PASSWORDS_DONT_MATCH:
				return new PasswordsDontMatchErrorResponse();
			
			case REQUIRED_EMAIL:
				return new RequiredEmailErrorResponse();
			
			case REQUIRED_PASSWORD:
				return new RequiredPasswordErrorResponse();
			
			case REQUIRED_NEW_PASSWORD:
				return new RequiredNewPasswordErrorResponse();
			
			default:	// UNKNOWN_ERROR (Se desconoce la causa del error)
				return new UnknownErrorResponse();
		}
	}
}