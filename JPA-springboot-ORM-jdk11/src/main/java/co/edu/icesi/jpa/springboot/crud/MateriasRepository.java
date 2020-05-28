package co.edu.icesi.jpa.springboot.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.jpa.springboot.model.TMateria;

public interface MateriasRepository extends CrudRepository<TMateria, String> {


	 List<TMateria> findByNombre(String nombre);
}
