package es.uniovi.asw.voterAcess.webService.responses.errors;


/**
 * Sólo sirve para poder recoger las excpeciones que se puedan
 * recoger las excepciones que se producen en la respuesta
 * 
 */
public abstract class ErrorResponse extends RuntimeException
{
	protected static final long serialVersionUID = 1L;
	public abstract String getMessageJSONFormat();
}
