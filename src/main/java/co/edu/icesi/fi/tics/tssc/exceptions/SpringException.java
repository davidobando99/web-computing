package co.edu.icesi.fi.tics.tssc.exceptions;

/**
 * Excepción que se lanza cuando un tema tiene springs menor o igual a cero.
 * @author Douglas
 *
 */
public class SpringException extends Exception{

	public SpringException() {
		super("Spring menor a cero");
	}
}
