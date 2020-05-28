package co.edu.icesi.ci.injectionexample1.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Student {
	@NonNull
	private String id;
	private String firstName;
	private String lastName;
}
