package co.edu.icesi.ci.injectionexample1.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Course {
	@NonNull
	private String id;
	@NonNull
	private String name;
	
	private int credits;
	private String description;
}
