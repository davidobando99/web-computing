package co.edu.icesi.ci.mvctutorial.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String email;
	
	private UserType type;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private LocalDate birthDate;
	
	private UserGender gender;
	
//	@OneToMany
//	private List<Appointment> appointments;
}
