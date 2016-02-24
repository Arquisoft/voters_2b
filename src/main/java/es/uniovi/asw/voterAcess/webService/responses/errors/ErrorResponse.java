package es.uniovi.asw.voterAcess.webService.responses.errors;

/**
 * Sirve para recoger las excepciones que se producen en la respuesta.
 *
 */
public abstract class ErrorResponse extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public abstract String getMessageJSONFormat();
}
