package com.packt.cardatabase.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CarDelegate {
	
	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest = new RestTemplate();
	
	
	

}
