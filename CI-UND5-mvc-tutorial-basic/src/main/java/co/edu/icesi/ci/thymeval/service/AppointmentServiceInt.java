package co.edu.icesi.ci.thymeval.service;

import java.util.Optional;

import co.edu.icesi.ci.thymeval.model.Appointment;

public interface AppointmentServiceInt {
	public void save(Appointment user);

	public Optional<Appointment> findById(long id);

	public Iterable<Appointment> findAll();

	public void delete(Appointment user);
}
