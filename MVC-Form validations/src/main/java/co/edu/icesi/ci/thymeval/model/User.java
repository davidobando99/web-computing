package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class User {
	
	public interface validator1 {};
	
	public interface validator2 {};

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotBlank(groups=validator2.class)
	@Size(min = 2,groups=validator2.class)
	private String name;
	
	@NotBlank(groups=validator1.class)
	@Size(min = 3,groups=validator1.class)
	private String username;
	
	@NotBlank(groups=validator1.class)
	@Size(min = 8,groups=validator1.class)
	private String password;
	
	
	@NotBlank(groups=validator2.class)
	@Email(groups=validator2.class)
	private String email;
	
	@NotNull(groups=validator2.class)
	private UserType type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(groups=validator1.class)
	@NotNull(groups=validator1.class)
	private LocalDate birthDate;
	@NotNull(groups=validator2.class) //No nulo ni vacio
	private UserGender gender;
	
//	@OneToMany
//	private List<Appointment> appointments;
}
