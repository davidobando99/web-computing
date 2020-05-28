package co.edu.icesi.ci.mvctutorial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.mvctutorial.model.Appointment;
import co.edu.icesi.ci.mvctutorial.repository.AppointmentRepository;

@Service
public class AppointmentService implements AppointmentServiceInt{

	private AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	public void save(Appointment user) {
		appointmentRepository.save(user);

	}

	public Optional<Appointment> findById(long id) {

		return appointmentRepository.findById(id);
	}

	public Iterable<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	public void delete(Appointment user) {
		appointmentRepository.delete(user);
		
	}
}
