package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@FutureOrPresent
	private LocalDate date;
	
	@DateTimeFormat(iso = ISO.TIME)
	@NotNull
	private LocalTime time;
	
	@ManyToOne
	@NotNull
	private User patient;
	
	@ManyToOne
	@NotNull
	private User doctor;
}
