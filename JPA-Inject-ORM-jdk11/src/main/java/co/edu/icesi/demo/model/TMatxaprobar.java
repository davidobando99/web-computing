package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_matxaprobar database table.
 * 
 */
@Entity
@Table(name="t_matxaprobar")
@NamedQuery(name="TMatxaprobar.findAll", query="SELECT t FROM TMatxaprobar t")
public class TMatxaprobar implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TMatxaprobarPK id;

	//bi-directional many-to-one association to TAlumno
	@ManyToOne
	@JoinColumn(name="alumno", insertable = false, updatable = false)
	private TAlumno TAlumno;

	//bi-directional many-to-one association to TMateria
	@ManyToOne
	@JoinColumn(name="materia", insertable = false, updatable = false)
	private TMateria TMateria;

	//bi-directional many-to-one association to TPrograma
	@ManyToOne
	@JoinColumn(name="programa", insertable = false, updatable = false)
	private TPrograma TPrograma;

	public TMatxaprobar() {
	}

	public TMatxaprobarPK getId() {
		return this.id;
	}

	public void setId(TMatxaprobarPK id) {
		this.id = id;
	}

	public TAlumno getTAlumno() {
		return this.TAlumno;
	}

	public void setTAlumno(TAlumno TAlumno) {
		this.TAlumno = TAlumno;
	}

	public TMateria getTMateria() {
		return this.TMateria;
	}

	public void setTMateria(TMateria TMateria) {
		this.TMateria = TMateria;
	}

	public TPrograma getTPrograma() {
		return this.TPrograma;
	}

	public void setTPrograma(TPrograma TPrograma) {
		this.TPrograma = TPrograma;
	}

}