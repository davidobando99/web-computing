package co.edu.icesi.fi.tics.tssc.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TopicDelegate {

	public static final String URI = "http://localhost:8084/";
	private RestTemplate rest = new RestTemplate();
	
}
