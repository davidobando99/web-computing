package co.edu.icesi.ci.thymeval.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.thymeval.model.Appointment;


public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	List<Appointment> findByPatient(String patient);

	List<Appointment> findByDoctor(String doctor);

}
