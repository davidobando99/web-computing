package co.edu.icesi.fi.tics.tssc.exceptions;

public class BusinessValueException extends Exception {

	public BusinessValueException() {
		super("Valor de negocio menor o igual a cero");
	}
}
