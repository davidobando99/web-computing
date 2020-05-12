package co.edu.icesi.fi.tics.tssc.exceptions;

import java.util.NoSuchElementException;

public class TopicException extends NoSuchElementException{

	public TopicException() {
		super("Topic es nulo o inexistente en la database");
	}
}
