package co.edu.icesi.fi.tics.tssc.exceptions;

public class GameException extends NullPointerException{

	public GameException() {
		super("Juego no existe en la data-base");
	}
}
