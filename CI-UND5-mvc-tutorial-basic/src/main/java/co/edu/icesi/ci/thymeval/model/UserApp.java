package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class UserApp {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Size(min=2, groups= {Validation2.class,Validation3.class})
	@NotBlank(groups= {Validation2.class,Validation3.class})	
	private String name;
	
	@NotBlank(groups= {Validation1.class,Validation3.class})
	@Size(min = 2, groups= {Validation1.class,Validation3.class})
	private String username;
	
	@NotBlank(groups= {Validation2.class, Validation3.class})
	@Email(groups= {Validation2.class, Validation3.class})
	private String email;
	
	@NotBlank(groups= Validation1.class)
	@Size(min = 8, groups=Validation1.class)
	private String password;
	
	@NotNull(groups= {Validation2.class, Validation3.class})
	private UserType type;
	
	@Past(groups= {Validation1.class, Validation3.class})
	@NotNull(groups= {Validation1.class, Validation3.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	
	@NotNull(groups= {Validation2.class, Validation3.class})
	private UserGender gender;                          
	
//	@OneToMany
//	private List<Appointment> appointments;
}
