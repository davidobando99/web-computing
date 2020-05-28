package co.edu.icesi.jpa.springboot.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.jpa.springboot.model.TAlumno;

public interface AlumnosRepository extends CrudRepository<TAlumno, Integer> {
	
	 List<TAlumno> findByApellidos(String apellidos);
	 List<TAlumno> findByNombre(String nombre);
}
