package co.edu.icesi.fi.tics.tssc.rest;

import java.io.Serializable;

public class TransactionBody<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String apiContext;
	private T body;

	public TransactionBody() {
	}

	public TransactionBody(String apiContext, T body) {
		this.apiContext = apiContext;
		this.body = body;
	}

	public String getApiContext() {
		return apiContext;
	}

	public void setApiContext(String apiContext) {
		this.apiContext = apiContext;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
