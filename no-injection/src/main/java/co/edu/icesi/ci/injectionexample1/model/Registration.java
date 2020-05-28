package co.edu.icesi.ci.injectionexample1.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Registration {
	@NonNull
	private Student student;
	@NonNull
	private Course course;
	@NonNull
	int semester;
}
