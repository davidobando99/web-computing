package co.edu.icesi.fi.tics.tssc.exceptions;

/**
 * Excepción que se lanza si un tema tiene una capacidad menor o igual a cero.
 * @author Douglas
 *
 */
public class CapacityException extends Exception{

	public CapacityException() {
		super("Capacidad menor a cero");
	}
}
