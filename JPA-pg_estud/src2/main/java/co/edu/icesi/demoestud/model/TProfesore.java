package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_profesores database table.
 * 
 */
@Entity
@Table(name="t_profesores")
@NamedQuery(name="TProfesore.findAll", query="SELECT t FROM TProfesore t")
public class TProfesore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cedula;

	private String apellidos;

	private String nombre;

	//bi-directional many-to-one association to THorarioCurso
	@OneToMany(mappedBy="TProfesore")
	private List<THorarioCurso> THorarioCursos;

	public TProfesore() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<THorarioCurso> getTHorarioCursos() {
		return this.THorarioCursos;
	}

	public void setTHorarioCursos(List<THorarioCurso> THorarioCursos) {
		this.THorarioCursos = THorarioCursos;
	}

	public THorarioCurso addTHorarioCurso(THorarioCurso THorarioCurso) {
		getTHorarioCursos().add(THorarioCurso);
		THorarioCurso.setTProfesore(this);

		return THorarioCurso;
	}

	public THorarioCurso removeTHorarioCurso(THorarioCurso THorarioCurso) {
		getTHorarioCursos().remove(THorarioCurso);
		THorarioCurso.setTProfesore(null);

		return THorarioCurso;
	}

}